package org.blaec.movies.utils;

import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;

public class MovieConverter {
    public static MovieDbObject combine(MovieJsonObject movieJsonObject, MovieFileObject movieFileObject) {
        MovieDbObject movieDbObject = new MovieDbObject();

        // add movie json object
        movieDbObject.setTitle(movieJsonObject.getTitle());
        movieDbObject.setYear(movieJsonObject.getYear());
        movieDbObject.setRated(movieJsonObject.getRated());
        movieDbObject.setRuntime(movieJsonObject.getRuntime());
        movieDbObject.setGenre(movieJsonObject.getGenre());
        movieDbObject.setActors(movieJsonObject.getActors());
        movieDbObject.setLanguage(movieJsonObject.getLanguage());
        movieDbObject.setAwards(movieJsonObject.getAwards());
        movieDbObject.setImdbRating(movieJsonObject.getImdbRating());
        movieDbObject.setImdbId(movieJsonObject.getImdbID());
        movieDbObject.setPoster(movieJsonObject.getPoster());
        movieDbObject.setImdbVotes(movieJsonObject.getImdbVotes());
        movieDbObject.setType(movieJsonObject.getType());

        // add movie file object
        movieDbObject.setResolution(movieFileObject.getResolution());
        movieDbObject.setSize(movieFileObject.getSize());
        movieDbObject.setLocation(movieFileObject.getLocation());
        movieDbObject.setFrameRate(movieFileObject.getFrameRate());
        return movieDbObject;
    }
}
