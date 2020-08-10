package org.blaec.movies;

import com.google.gson.Gson;
import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.utils.LoadMovies;
import org.blaec.movies.utils.ApiRequest;

import java.net.http.HttpResponse;

public class AppMain {
    private static final String VIDEOS = "C:/Users/blaec/Videos";
    private static final String MOVIES = "//LDLKONSTANTIN/Movies";
    private static final String CARTOONS = "//LDLKONSTANTIN/Cartoons";

    public static void main(String[] args) {
        String url = MovieConfig.getApiRequestUrl(LoadMovies.getMoviesFromFolder(MOVIES).get(33));
        HttpResponse<String> stringHttpResponse = ApiRequest.sendRequest(url);
        Gson g = new Gson();
        MovieJsonObject person = g.fromJson(stringHttpResponse.body(), MovieJsonObject.class);
        System.out.println(stringHttpResponse.body());
        System.out.println(person);

//        getMoviesFromFolder(VIDEOS).forEach(System.out::println);
//        getMoviesFromFolder(MOVIES).forEach(System.out::println);
//        getMoviesFromFolder(CARTOONS).forEach(System.out::println);
    }
}
