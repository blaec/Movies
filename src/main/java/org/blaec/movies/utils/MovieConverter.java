package org.blaec.movies.utils;

import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

public class MovieConverter {
    public static MovieDbObject combine(MovieJsonObject movieJsonObject, MovieFileObject movieFileObject) throws ParseException {
        MovieDbObject movieDbObject = new MovieDbObject();

        // add movie json object
        movieDbObject.setTitle(movieJsonObject.getTitle());
        movieDbObject.setYear(Integer.parseInt(movieJsonObject.getYear()));
        movieDbObject.setRated(movieJsonObject.getRated());
        movieDbObject.setRuntime(Integer.parseInt(movieJsonObject.getRuntime().replace("min","").trim()));
        movieDbObject.setGenre(movieJsonObject.getGenre());
        movieDbObject.setActors(movieJsonObject.getActors());
        movieDbObject.setLanguage(movieJsonObject.getLanguage());
        movieDbObject.setAwards(movieJsonObject.getAwards());
        movieDbObject.setImdbRating(Double.parseDouble(movieJsonObject.getImdbRating()));
        movieDbObject.setImdbId(movieJsonObject.getImdbID());
        movieDbObject.setPoster(movieJsonObject.getPoster().equals("N/A")
                ? String.format("https://via.placeholder.com/200x300.png?text=%s", movieJsonObject.getTitle().replace(" ", "%20"))
                : movieJsonObject.getPoster());
        movieDbObject.setImdbVotes(NumberFormat.getNumberInstance(Locale.US).parse(movieJsonObject.getImdbVotes()).intValue());
        movieDbObject.setType(movieJsonObject.getType());

        // add movie file object
        movieDbObject.setResolution(movieFileObject.getResolution());
        movieDbObject.setSize(movieFileObject.getSize());
        movieDbObject.setLocation(movieFileObject.getLocation());
        movieDbObject.setFrameRate(movieFileObject.getFrameRate());

        // additional fields
        movieDbObject.setUpdated(new Timestamp(System.currentTimeMillis()));
        return movieDbObject;
    }

    public static String getKeyLocation(String fullLocation) {
        return MovieConfig.getFileLocations().entrySet().stream()
                .filter(e -> fullLocation.contains(e.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().orElse(fullLocation);
    }
}
