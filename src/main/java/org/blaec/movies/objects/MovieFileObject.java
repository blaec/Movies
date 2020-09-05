package org.blaec.movies.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.blaec.movies.enums.FailType;
import org.blaec.movies.enums.Resolution;
import org.blaec.movies.utils.FailureAccumulator;
import org.blaec.movies.utils.FilesUtils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
@AllArgsConstructor
public class MovieFileObject {
    // mandatory properties
    private final String name;
    private final int year;
    private final Resolution resolution;
    private final double size;
    private final String location;

    // optional properties
    private final String description;
    private final int frameRate;

    private static final Pattern MOVIE = Pattern.compile(
            "(?<order>\\d{3}. )?" +
            "(?<name>[ ,-.\\w'&ampéÆ!;]+?) " +
            "(\\[(?<description>.*)] )?" +
            "\\((?<year>\\d{4})\\) " +
            "\\[(?<resolution>\\d+p)]" +
            "( \\[(?<frameRate>\\d+)fps])?");

    /**
     * Creates movie file object from movie file
     *
     * @param file movie file
     * @return MovieFileObject and may return null
     */
    public static MovieFileObject from(File file) {
        String fileName = file.getName();
        Matcher matcher = MOVIE.matcher(fileName);
        MovieFileObject movieFileObject = null;
        if (!matcher.find()) {
            String fullPath = String.format("%s%s%s", file.getParent(), File.separator, fileName);
            log.error("failed to parse movie {}", fullPath);
            FailureAccumulator.addToFailList(FailType.PARSE, fullPath);
        } else {
            int frameRate = parseInt(matcher, "frameRate");
            movieFileObject = new MovieFileObject(
                matcher.group("name"),
                parseInt(matcher, "year"),
                Resolution.getResolution(matcher.group("resolution")),
                FilesUtils.byteToGb(file.length()),
                file.getParent(),
                matcher.group("description"),
                frameRate == 0 ? 24 : frameRate
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
        return String.format("%s | %s%s (%d) [%s]%s | %.2f Gb",
                location,
                name,
                description == null ? "" : String.format(" [%s]", description),
                year,
                resolution == null ? "WRONG RESOLUTION" : resolution.getRes(),
                frameRate == 0 ? "" : String.format(" [%dfps]", frameRate),
                size);
    }
}
