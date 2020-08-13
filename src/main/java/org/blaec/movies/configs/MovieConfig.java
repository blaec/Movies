package org.blaec.movies.configs;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import org.blaec.movies.objects.MovieFileObject;

import java.util.Map;
import java.util.stream.Collectors;

public class MovieConfig {
    private static final MovieConfig INSTANCE = new MovieConfig(Configs.getConfig("movie.conf", "omdbapi"));

    final private String endpoint;
    final private String paramTitle;
    final private String paramYear;
    final private String paramApikey;
    final private String valueApikey;

    private MovieConfig(Config conf) {
        endpoint = conf.getString("endpoint");
        paramTitle = conf.getString("paramName.title");
        paramYear = conf.getString("paramName.year");
        paramApikey = conf.getString("paramName.apikey");
        valueApikey = conf.getString("paramValue.apikey");
    }

    /**
     * Create request api url from movie file object (name and year)
     * sample url: http://www.omdbapi.com/?t=As+Good+as+It+Gets&y=1997&apikey=22ea6ede
     * TODO replace ' with %27, space with +
     * @param movieFileObject movie file object
     * @return url for api-request
     */
    public static String getApiRequestUrl(MovieFileObject movieFileObject) {
        String params = joinParams(ImmutableMap.of(
                INSTANCE.paramTitle, encodeTitle(movieFileObject.getName()),
                INSTANCE.paramYear, String.valueOf(movieFileObject.getYear()),
                INSTANCE.paramApikey, INSTANCE.valueApikey));
        return String.format("%s?%s", INSTANCE.endpoint, params);
    }

    private static String encodeTitle(String name) {
        return name.replace(" ", "+")
                   .replace("..", "%3A")
                   .replace("'", "%27")
                   .replace("é", "%C3%A9")
                   .replace("&", "%26");
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

    @Override
    public String toString() {
        return "MovieConfig{" +
                "endpoint='" + endpoint + '\'' +
                ", paramTitle='" + paramTitle + '\'' +
                ", paramYear='" + paramYear + '\'' +
                ", paramApikey='" + paramApikey + '\'' +
                ", valueApikey='" + valueApikey + '\'' +
                '}';
    }
}
