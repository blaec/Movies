package org.blaec.movies.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.blaec.movies.enums.Resolution;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public class MovieFileObject {
    // mandatory properties
    private final String name;
    private final int year;
    private final Resolution resolution;
    private final String size;
    private final String location;

    // optional properties
    private final String description;
    private final int frameRate;

    private static final Pattern MOVIE = Pattern.compile(
            "(?<name>[ ,-.\\w'&ampéÆ;]+?) " +
            "(\\[(?<description>.*)] )?" +
            "\\((?<year>\\d{4})\\) " +
            "\\[(?<resolution>\\d+p)]" +
            "( \\[(?<frameRate>\\d+)fps])?");

    public static MovieFileObject from(File file) {
        String fileName = file.getName();
        Matcher matcher = MOVIE.matcher(fileName);
        MovieFileObject movieFileObject = null;
        if (!matcher.find()) {
            // TODO log error
            System.out.println(fileName);
        } else {
            movieFileObject = new MovieFileObject(
                matcher.group("name"),
                parseInt(matcher, "year"),
                Resolution.getResolution(matcher.group("resolution")),
                FileUtils.byteCountToDisplaySize(file.length()),
                file.getParent(),
                matcher.group("description"),
                parseInt(matcher, "frameRate")
            );
        }
        return movieFileObject;
    }

    private static int parseInt(Matcher matcher, String group) {
        String value = matcher.group(group);
        return value == null ? 0 : Integer.parseInt(value);
    }

    /**
     * Replace some symbols in file name to new symbols as they are saved in db
     *
     * @return file movie name converted for comparing with db movie name
     */
    public String getNameDbStyled() {
        return name.replace("..", ":");
    }

    /**
     * Replace some symbols in file name to new symbols allowed in url link
     *
     * @return file movie name converted to url file name
     */
    public String getNameUrlStyled() {
        return name.replace(" ", "+")
                   .replace("..", "%3A")
                   .replace("'", "%27")
                   .replace("é", "%C3%A9")
                   .replace("&", "%26");
    }

    @Override
    public String toString() {
        return String.format("%s | %s%s (%d) [%s]%s | %s",
                location,
                name,
                description == null ? "" : String.format(" [%s]", description),
                year,
                resolution == null ? "WRONG RESOLUTION" : resolution.getRes(),
                frameRate == 0 ? "" : String.format(" [%dfps]", frameRate),
                size);
    }
}
