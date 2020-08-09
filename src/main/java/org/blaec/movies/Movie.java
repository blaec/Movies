package org.blaec.movies;

import org.blaec.movies.enums.Resolution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {
    private final String name;
    private final int year;
    private final String description;
    private final Resolution resolution;
    private final int frameRate;

    public Movie(String name, int year, String description, Resolution resolution, int frameRate) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.resolution = resolution;
        this.frameRate = frameRate;
    }

    private static final Pattern MOVIE = Pattern.compile(
            "(?<name>[ ,-.\\w'&amp;]+?) " +
            "(\\[(?<description>.*)\\] )?" +
            "\\((?<year>\\d{4})\\) " +
            "\\[(?<resolution>\\d+p)\\]" +
            "( \\[(?<frameRate>\\d+)fps\\])?");

    public static Movie from(String fileName) {
        Matcher matcher = MOVIE.matcher(fileName);
        return matcher.find()
                ? new Movie(
                    matcher.group("name"),
                    Integer.parseInt(matcher.group("year")),
                    matcher.group("description"),
                    Resolution.getResolution(matcher.group("resolution")),
                    Integer.parseInt(matcher.group("frameRate")))
                : null;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", resolution=" + resolution +
                ", frameRate=" + frameRate +
                '}';
    }
}
