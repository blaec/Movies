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
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class UploadServlet extends HttpServlet {
    private Map<String, String> locationsMap;
    private Set<String> locations;

    @Override
    public void init() throws ServletException {
        super.init();
        locationsMap = MovieConfig.getFileLocations();
        locations = locationsMap.keySet();
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().size() > 0) {
            String uploadLocation = locationsMap.get(request.getParameter("selected-location"));
            List<MovieFileObject> folderMovies = FilesUtils.getMoviesFromFolder(uploadLocation);
            MovieDao dao = DBIProvider.getDao(MovieDao.class);
            List<MovieDbObject> dbMovies = dao.getAll();
            Gson gson = new Gson();
            for (MovieFileObject movieFile : folderMovies) {
                boolean movieNotExistInDb = dbMovies.stream()
                        .noneMatch(m -> StringUtils.containsIgnoreCase(m.getTitle(), movieFile.getNameDbStyled()) && m.getYear() == movieFile.getYear());
                if (movieNotExistInDb) {
                    String url = MovieConfig.getApiRequestUrl(movieFile);
                    HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                    try {
                        MovieJsonObject movieJson = gson.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                        dao.insert(MovieConverter.combine(movieJson, movieFile));
                        log.info("added new movie {} ({}) {}Gb | imdbId={}", movieJson.getTitle(), movieJson.getYear(), movieFile.getSize(), movieJson.getImdbID());
                    } catch (Exception e) {
                        log.error("failed to save movie {} into db", movieFile, e);
                    }
                }
            }
        }
        response.sendRedirect("upload");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("/jsp/upload.jsp").forward(request, response);
    }
}
