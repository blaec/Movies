package org.blaec.movies;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.persist.DBITestProvider;
import org.blaec.movies.utils.ApiUtils;
import org.blaec.movies.utils.FilesUtils;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public class AppMain {
    private static final String VIDEOS = "C:/Users/blaec/Videos";
    private static final String MOVIES = "//LDLKONSTANTIN/Movies";
    private static final String CARTOONS = "//LDLKONSTANTIN/Cartoons";

    static {
        DBITestProvider.initDBI();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        FilesUtils.getMoviesFromFolder(MOVIES).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(VIDEOS).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(CARTOONS).forEach(System.out::println);

        List<MovieFileObject> folderMovies = FilesUtils.getMoviesFromFolder(MOVIES);
        MovieDao dao = DBIProvider.getDao(MovieDao.class);
        List<MovieDbObject> dbMovies = dao.getAll();

        for (MovieFileObject movieFile : folderMovies) {
            boolean movieNotExistInDb = dbMovies.stream()
                    .noneMatch(m -> StringUtils.containsIgnoreCase(
                            m.getTitle(),
                            movieFile.getName().replace("..", ":")));
            if (movieNotExistInDb) {
                String url = MovieConfig.getApiRequestUrl(movieFile);
                HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
                Gson g = new Gson();
                try {
                    System.out.println("not found " + movieFile.getName());
                    MovieJsonObject movie = g.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
                    dao.insert(movie);
                } catch (Exception e) {
                    System.out.println(url);
                }
            }
        }
    }
}
