package org.blaec.movies.web;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.dao.WishListDao;
import org.blaec.movies.enums.FailType;
import org.blaec.movies.objects.*;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.utils.ApiUtils;
import org.blaec.movies.utils.FailureAccumulator;
import org.blaec.movies.utils.FilesUtils;
import org.blaec.movies.utils.MovieConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.blaec.movies.definitions.Definitions.NOT_SELECTED;

@Slf4j
public class UploadServlet extends HttpServlet {
    private Map<String, String> locationsMap;
    private Set<String> locations;
    private List<SuccessMovieFileObject> successUpload;

    @Override
    public void init() throws ServletException {
        super.init();
        locationsMap = MovieConfig.getFileLocations();
        locations = locationsMap.keySet();
        successUpload = new ArrayList<>();
        FailureAccumulator.initFailList();
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        MovieDao movieDao = DBIProvider.getDao(MovieDao.class);

        switch (action == null ? "default" : action) {
            case "gallery":
                String selectedLocation = request.getParameter("selected-location");
                String imdbId = request.getParameter("imdb-id");
                if (selectedLocation != null) {
                    List<MovieDbObject> dbMovies = movieDao.getAll();
                    String uploadLocation = locationsMap.get(selectedLocation);
                    List<MovieFileObject> uploadMovies = FilesUtils.getMoviesFromFolder(uploadLocation);
                    List<MovieFileObject> newUploadMovies = uploadMovies.stream()
                            .filter(um -> dbMovies.stream()
                                    .noneMatch(dbm -> StringUtils.equalsIgnoreCase(dbm.getTitle(), um.getNameDbStyled())
                                            && dbm.getYear() == um.getYear()))
                            .collect(Collectors.toList());
                    Gson gson = new Gson();
                    successUpload = new ArrayList<>();
                    String manualImportTitle = request.getParameter("manual-import-title");
                    for (MovieFileObject movieFile : newUploadMovies) {
                        String url = imdbId == null || !movieFile.getName().equals(manualImportTitle)
                                ? MovieConfig.getApiRequestUrl(movieFile)
                                : MovieConfig.getApiRequestUrl(imdbId);
                        HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                        try {
                            MovieJsonObject movieJson = gson.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                            movieDao.insert(MovieConverter.combine(movieJson, movieFile));
                            successUpload.add(SuccessMovieFileObject.from(movieFile.toString(), movieJson.getTitle()));
                            log.info("added new movie {} ({}) {}Gb | imdbId={}",
                                    movieJson.getTitle(), movieJson.getYear(), movieFile.getSize(), movieJson.getImdbID());
                        } catch (Exception e) {
                            log.error("failed to save movie {} into db", movieFile, e);
                            FailureAccumulator.addToFailList(FailType.DB_SAVE, movieFile.toString());
                        }
                    }
                }
                break;
            case "wishlist":
                WishListDao wishlistDao = DBIProvider.getDao(WishListDao.class);
                String wishlistImdbId = request.getParameter("wishlist-imdb-id");
                MovieDbObject galleryMovie = movieDao.getByImdbId(wishlistImdbId);
                WishListDbObject wishlistMovie = wishlistDao.getByImdbId(wishlistImdbId);
                if (galleryMovie == null && wishlistMovie == null) {
                    Gson gson = new Gson();
                    String url = MovieConfig.getApiRequestUrl(wishlistImdbId);
                    try {
                        HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                        MovieJsonObject movieJson = gson.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                        wishlistDao.insert(MovieConverter.toWishList(movieJson));
                        successUpload.add(SuccessMovieFileObject.from(movieJson.getTitle()));
                        log.info("added to wishlist {} ({}) | imdbId={}",
                                movieJson.getTitle(), movieJson.getYear(), movieJson.getImdbID());
                    } catch (Exception e) {
                        log.error("failed to save movie {} into db", wishlistDao, e);
                        FailureAccumulator.addToFailList(FailType.DB_SAVE, wishlistImdbId);
                    }
                } else if (galleryMovie != null) {
                    FailureAccumulator.addToFailList(FailType.IN_GALLERY, galleryMovie.getTitle());
                } else if (wishlistMovie != null) {
                    FailureAccumulator.addToFailList(FailType.IN_WISHLIST, wishlistMovie.getTitle());
                }
                break;
            case "default":
            default:
                break;
        }
        response.sendRedirect("upload");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("notSelected", NOT_SELECTED);
        request.setAttribute("success", successUpload);
        List<String> failUpload = FailureAccumulator.getFailUploadList();
        request.setAttribute("fail", failUpload);
        request.setAttribute("locations", locations);
        String uploadMessage = Stream
                .of(getMessage(successUpload.size(), "Successful"), getMessage(failUpload.size(), "Failed"))
                .filter(Objects::nonNull)
                .collect(Collectors.joining("  |  "));
        request.setAttribute("uploadMessage", uploadMessage);
        request.getRequestDispatcher("/jsp/upload.jsp").forward(request, response);
    }

    private String getMessage(int size, String msg) {
        return size == 0 ? null : String.format("%s uploads: %d", msg, size);
    }
}
