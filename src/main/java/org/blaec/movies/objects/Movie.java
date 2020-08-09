package org.blaec.movies.objects;

import org.blaec.movies.enums.Resolution;

import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {
    // mandotary properties
    private final String name;
    private final int year;
    private final Resolution resolution;
    private final String size;

    // optional properties
    private final String description;
    private final int frameRate;

    public Movie(String name, String description, int year, Resolution resolution, int frameRate, String size) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.resolution = resolution;
        this.frameRate = frameRate;
        this.size = size;
    }

    private static final Pattern MOVIE = Pattern.compile(
            "(?<name>[ ,-.\\w'&amp;]+?) " +
            "(\\[(?<description>.*)\\] )?" +
            "\\((?<year>\\d{4})\\) " +
            "\\[(?<resolution>\\d+p)\\]" +
            "( \\[(?<frameRate>\\d+)fps\\])?");

    public static Movie from(File file) {
        String fileName = file.getName();
        Matcher matcher = MOVIE.matcher(fileName);
        Movie movie = null;
        if (!matcher.find()) {
            // TODO log error
            System.out.println(fileName);
        } else {
            movie = new Movie(matcher.group("name"),
                              matcher.group("description"),
                              parseInt(matcher, "year"),
                              Resolution.getResolution(matcher.group("resolution")),
                              parseInt(matcher, "frameRate"),
                              getSize(file));
        }
        return movie;
    }

    private static int parseInt(Matcher matcher, String group) {
        String value = matcher.group(group);
        return value == null ? 0 : Integer.parseInt(value);
    }

    private static String getSize(File file) {
        long bytes = file.length();
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }

    @Override
    public String toString() {
        return String.format("%s%s (%d) [%s]%s | %s",
                name,
                description == null ? "" : String.format(" [%s]", description),
                year,
                resolution == null ? "WRONG RESOLUTION" : resolution.getRes(),
                frameRate == 0 ? "" : String.format(" [%dfps]", frameRate),
                size);
    }
}
