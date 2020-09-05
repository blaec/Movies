package org.blaec.movies.objects;

import lombok.AllArgsConstructor;
import org.blaec.movies.enums.FailType;

@AllArgsConstructor
public class FailedMovieFileObject {
    private final FailType failType;
    private final String description;

    @Override
    public String toString() {
        return String.format("%s | %s", failType.getDescription(), description);
    }
}
