package org.blaec.movies;

import com.google.gson.Gson;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.utils.ApiUtils;
import org.blaec.movies.utils.FilesUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

public class AppMain {
    private static final String VIDEOS = "C:/Users/blaec/Videos";
    private static final String MOVIES = "//LDLKONSTANTIN/Movies";
    private static final String CARTOONS = "//LDLKONSTANTIN/Cartoons";

    public static void main(String[] args) throws IOException, InterruptedException {
        FilesUtils.getMoviesFromFolder(MOVIES).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(VIDEOS).forEach(System.out::println);
//        LoadMovies.getMoviesFromFolder(CARTOONS).forEach(System.out::println);

        String url = MovieConfig.getApiRequestUrl(FilesUtils.getMoviesFromFolder(MOVIES).get(33));
        HttpResponse<String> stringHttpResponse = ApiUtils.sendRequest(url);
        Gson g = new Gson();
        MovieJsonObject person = g.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
        System.out.println(stringHttpResponse.body());
        System.out.println(person);
    }
}
