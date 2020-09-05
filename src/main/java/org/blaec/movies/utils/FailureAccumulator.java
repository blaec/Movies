package org.blaec.movies.utils;

import org.blaec.movies.enums.FailType;
import org.blaec.movies.objects.FailedMovieFileObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FailureAccumulator {
    private static List<FailedMovieFileObject> failUploadList;

    static {
        initFailList();
    }

    public static void addToFailList(FailType failType, String description) {
        FailedMovieFileObject failedMovieFileObject = new FailedMovieFileObject(failType, description);
        failUploadList.add(failedMovieFileObject);
    }

    public static List<String> getFailUploadList() {
        return failUploadList.stream()
                .map(FailedMovieFileObject::toString)
                .collect(Collectors.toList());
    }

    public static void initFailList() {
        failUploadList = new ArrayList<>();
    }
}
