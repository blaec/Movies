package org.blaec.movies.utils;

import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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
        movieDbObject.setPoster(movieJsonObject.getPoster());
        movieDbObject.setImdbVotes(NumberFormat.getNumberInstance(Locale.US).parse(movieJsonObject.getImdbVotes()).intValue());
        movieDbObject.setType(movieJsonObject.getType());

        // add movie file object
        movieDbObject.setResolution(movieFileObject.getResolution());
        movieDbObject.setSize(movieFileObject.getSize());
        movieDbObject.setLocation(movieFileObject.getLocation());
        movieDbObject.setFrameRate(movieFileObject.getFrameRate());
        return movieDbObject;
    }
}
