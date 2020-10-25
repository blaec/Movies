package org.blaec.movies.utils;

import org.blaec.movies.configs.MovieConfig;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.objects.MovieFileObject;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.objects.WishListDbObject;

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
        movieDbObject.setPoster(getPoster(movieJsonObject));
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

    public static WishListDbObject toWishList(MovieJsonObject movieJsonObject) throws ParseException {
        WishListDbObject wishlistMovie = new WishListDbObject();
        wishlistMovie.setTitle(movieJsonObject.getTitle());
        wishlistMovie.setYear(Integer.parseInt(movieJsonObject.getYear()));
        wishlistMovie.setRated(movieJsonObject.getRated());
        wishlistMovie.setRuntime(parseRuntime(movieJsonObject.getRuntime()));
        wishlistMovie.setGenre(movieJsonObject.getGenre());
        wishlistMovie.setImdbRating(parseImdbRating(movieJsonObject.getImdbRating()));
        wishlistMovie.setImdbId(movieJsonObject.getImdbID());
        wishlistMovie.setPoster(getPoster(movieJsonObject));
        wishlistMovie.setImdbVotes(parseImdbVotes(movieJsonObject.getImdbVotes()));

        // additional fields
        wishlistMovie.setAdded(new Timestamp(System.currentTimeMillis()));
        return wishlistMovie;
    }

    private static String getPoster(MovieJsonObject movieJsonObject) {
        return movieJsonObject.getPoster().equals("N/A")
                ? String.format("https://via.placeholder.com/200x300.png?text=%s", movieJsonObject.getTitle().replace(" ", "%20"))
                : movieJsonObject.getPoster();
    }

    private static Double parseImdbRating(String imdbRating) {
        return "N/A".equals(imdbRating) ? 0 : Double.parseDouble(imdbRating);
    }

    private static int parseImdbVotes(String imdbVotes) throws ParseException {
        return "N/A".equals(imdbVotes) ? 0 : NumberFormat.getNumberInstance(Locale.US).parse(imdbVotes).intValue();
    }

    private static int parseRuntime(String runtime) {
        return "N/A".equals(runtime) ? 0 : Integer.parseInt(runtime.replace("min","").trim());
    }
}
