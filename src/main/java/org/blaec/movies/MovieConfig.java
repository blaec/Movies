package org.blaec.movies;

import com.typesafe.config.Config;
import org.blaec.movies.utils.Configs;

import java.nio.charset.StandardCharsets;

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

    public static MovieConfig getInstance() {
        return INSTANCE;
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
