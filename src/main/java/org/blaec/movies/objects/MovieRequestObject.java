package org.blaec.movies.objects;

public class MovieRequestObject {
    private final String Title;
    private final String Year;
    private final String Rated;
    private final String Runtime;
    private final String Genre;
    private final String Actors;
    private final String Language;
    private final String Awards;
    private final String imdbRating;
    private final String imdbID;
    private final String Poster;
    private final String imdbVotes;
    private final String Type;

    public MovieRequestObject(String title, String year, String rated, String runtime, String genre, String actors, String language, String awards, String imdbRating, String imdbID, String poster, String imdbVotes, String type) {
        Title = title;
        Year = year;
        Rated = rated;
        Runtime = runtime;
        Genre = genre;
        Actors = actors;
        Language = language;
        Awards = awards;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
        Poster = poster;
        this.imdbVotes = imdbVotes;
        Type = type;
    }
}
