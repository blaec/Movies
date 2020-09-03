package org.blaec.movies.configs;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import org.blaec.movies.objects.MovieFileObject;

import java.util.Map;
import java.util.stream.Collectors;

public class MovieConfig {
    private static final MovieConfig INSTANCE = new MovieConfig(Configs.getConfig("movie.conf"));

    final private String endpoint;
    final private String paramTitle;
    final private String paramYear;
    final private String paramApikey;
    final private String valueApikey;
    final private String linkMovies;
    final private String linkCartoons;
    final private String linkVideos;
    final private String linkSerialMovies;


    private MovieConfig(Config conf) {
        endpoint = conf.getString("omdbapi.endpoint");
        paramTitle = conf.getString("omdbapi.paramName.title");
        paramYear = conf.getString("omdbapi.paramName.year");
        paramApikey = conf.getString("omdbapi.paramName.apikey");
        valueApikey = conf.getString("omdbapi.paramValue.apikey");
        linkMovies = conf.getString("upload.movies");
        linkCartoons = conf.getString("upload.cartoons");
        linkVideos = conf.getString("upload.videos");
        linkSerialMovies = conf.getString("upload.serialMovies");
    }

    /**
     * Create request api url from movie file object (name and year)
     * sample url: http://www.omdbapi.com/?t=As+Good+as+It+Gets&y=1997&apikey=33ca5cbc
     *
     * @param movieFileObject movie file object
     * @return url for api-request
     */
    public static String getApiRequestUrl(MovieFileObject movieFileObject) {
        String params = joinParams(ImmutableMap.of(
                INSTANCE.paramTitle, movieFileObject.getNameUrlStyled(),
                INSTANCE.paramYear, String.valueOf(movieFileObject.getYear()),
                INSTANCE.paramApikey, INSTANCE.valueApikey));
        return String.format("%s?%s", INSTANCE.endpoint, params);
    }

    /**
     * Get location with movie files
     *
     * @return map with key - for location description and value - for path to file location
     */
    public static Map<String, String> getFileLocations() {
        return ImmutableMap.of(
                "Movies", INSTANCE.linkMovies,
                "Cartoons", INSTANCE.linkCartoons,
                "Videos", INSTANCE.linkVideos,
                "Serial Movies", INSTANCE.linkSerialMovies
        );
    }

    /**
     * Convert map with key-value parameters into string like key1=value1&key2=value2...
     *
     * @param params map with key-value parameters
     * @return parameters string
     */
    private static String joinParams(Map<String, String> params) {
        return params.keySet().stream()
                .map(k -> String.format("%s=%s", k, params.get(k)))
                .collect(Collectors.joining("&"));
    }

}
