package org.blaec.movies.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
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
}
