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
    final private String paramId;
    final private String paramApikey;
    final private String valueApikey;
    final private Map<String, String> locations;


    private MovieConfig(Config conf) {
        endpoint = conf.getString("omdbapi.endpoint");
        paramTitle = conf.getString("omdbapi.paramName.title");
        paramYear = conf.getString("omdbapi.paramName.year");
        paramId = conf.getString("omdbapi.paramName.id");
        paramApikey = conf.getString("omdbapi.paramName.apikey");
        valueApikey = conf.getString("omdbapi.paramValue.apikey");
        locations = ImmutableMap.of(
                conf.getString("upload.name.movies"), conf.getString("upload.location.movies"),
                conf.getString("upload.name.cartoons"), conf.getString("upload.location.cartoons"),
                conf.getString("upload.name.videos"), conf.getString("upload.location.videos"),
                conf.getString("upload.name.serialMovies"), conf.getString("upload.location.serialMovies")
        );
    }

    /**
     * Create request api url from movie file object (name and year)
     * sample url: http://www.omdbapi.com/?t=As+Good+as+It+Gets&y=1997&apikey=33ca5cbc
     *
     * @param movieFileObject movie file object
     * @return url for api-request by title
     */
    public static String getApiRequestUrl(MovieFileObject movieFileObject) {
        String params = joinParams(ImmutableMap.of(
                INSTANCE.paramTitle, movieFileObject.getNameUrlStyled(),
                INSTANCE.paramYear, String.valueOf(movieFileObject.getYear()),
                INSTANCE.paramApikey, INSTANCE.valueApikey));
        return String.format("%s?%s", INSTANCE.endpoint, params);
    }

    /**
     * Create request api url imdb id
     * sample url: http://www.omdbapi.com/?i=tt0378194&apikey=33ca5cbc
     *
     * @param id imdb id
     * @return url for api-request by imdb id
     */
    public static String getApiRequestUrl(String id) {
        String params = joinParams(ImmutableMap.of(
                INSTANCE.paramId, id,
                INSTANCE.paramApikey, INSTANCE.valueApikey));
        return String.format("%s?%s", INSTANCE.endpoint, params);
    }

    /**
     * Get location with movie files
     *
     * @return map with key - for location description and value - for path to file location
     */
    public static Map<String, String> getFileLocations() {
        return INSTANCE.locations;
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
