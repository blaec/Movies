package org.blaec.movies.web;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.utils.ApiUtils;
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

@Slf4j
public class UploadServlet extends HttpServlet {
    private Map<String, String> locationsMap;
    private Set<String> locations;
    private List<String> successUpload;
    private List<String> failUpload;

    @Override
    public void init() throws ServletException {
        super.init();
        locationsMap = MovieConfig.getFileLocations();
        locations = locationsMap.keySet();
        successUpload = new ArrayList<>();
        failUpload = new ArrayList<>();
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().size() > 0) {
            MovieDao dao = DBIProvider.getDao(MovieDao.class);
            List<MovieDbObject> dbMovies = dao.getAll();
            String uploadLocation = locationsMap.get(request.getParameter("selected-location"));
            List<MovieFileObject> uploadMovies = FilesUtils.getMoviesFromFolder(uploadLocation);
            List<MovieFileObject> newUploadMovies = uploadMovies.stream()
                    .filter(um -> dbMovies.stream()
                            .noneMatch(dbm -> StringUtils.containsIgnoreCase(dbm.getTitle(), um.getNameDbStyled())
                                            && dbm.getYear() == um.getYear()))
                    .collect(Collectors.toList());
            Gson gson = new Gson();
            successUpload = new ArrayList<>();
            failUpload = new ArrayList<>();
            for (MovieFileObject movieFile : newUploadMovies) {
                String url = MovieConfig.getApiRequestUrl(movieFile);
                HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                try {
                    MovieJsonObject movieJson = gson.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                    dao.insert(MovieConverter.combine(movieJson, movieFile));
                    successUpload.add(movieFile.toString());
                    log.info("added new movie {} ({}) {}Gb | imdbId={}",
                            movieJson.getTitle(), movieJson.getYear(), movieFile.getSize(), movieJson.getImdbID());
                } catch (Exception e) {
                    log.error("failed to save movie {} into db", movieFile, e);
                    failUpload.add(movieFile.toString());
                }
            }
        }
        response.sendRedirect("upload");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("success", successUpload);
        request.setAttribute("fail", failUpload);
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("/jsp/upload.jsp").forward(request, response);
    }
}
