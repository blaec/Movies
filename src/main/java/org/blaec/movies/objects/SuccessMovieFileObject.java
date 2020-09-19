package org.blaec.movies.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessMovieFileObject {
    private final String movieFileObject;
    private final String movieJsonObjectTitle;

    public static SuccessMovieFileObject from(String movieFileObject, String movieJsonObjectTitle) {
        return new SuccessMovieFileObject(movieFileObject, movieJsonObjectTitle);
    }
}
