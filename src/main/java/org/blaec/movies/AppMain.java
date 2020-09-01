package org.blaec.movies;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.persist.DBILocalProvider;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.utils.ApiUtils;
import org.blaec.movies.utils.FilesUtils;
import org.blaec.movies.utils.MovieConverter;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
public class AppMain {
    private static final String VIDEOS = "C:/Users/blaec/Videos";
    private static final String MOVIES = "//LDLKONSTANTIN/Movies";
    private static final String CARTOONS = "//LDLKONSTANTIN/Cartoons";
    private static final String SERIAL_MOVIES = "//LDLKONSTANTIN/Serial Movies";
    public static final Gson gson = new Gson();

    static {
        DBILocalProvider.initDBI();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        FilesUtils.getMoviesFromFolder(MOVIES).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(VIDEOS).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(CARTOONS).forEach(System.out::println);

        List<MovieFileObject> folderMovies = FilesUtils.getMoviesFromFolder(SERIAL_MOVIES);
        MovieDao dao = DBIProvider.getDao(MovieDao.class);
        List<MovieDbObject> dbMovies = dao.getAll();
        for (MovieFileObject movieFile : folderMovies) {
            boolean movieNotExistInDb = dbMovies.stream()
                    .noneMatch(m -> StringUtils.containsIgnoreCase(m.getTitle(), movieFile.getNameDbStyled()) && m.getYear() == movieFile.getYear());
            if (movieNotExistInDb) {
                String url = MovieConfig.getApiRequestUrl(movieFile);
                HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                try {
                    MovieJsonObject movieJson = gson.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                    dao.insert(MovieConverter.combine(movieJson, movieFile));
                    log.info("new movie added {} ({}) {}Gb", movieJson.getTitle(), movieJson.getYear(), movieFile.getSize());
                } catch (Exception e) {
                    log.error("failed to save movie {} into db", movieFile, e);
                }
            }
        }
    }
}
