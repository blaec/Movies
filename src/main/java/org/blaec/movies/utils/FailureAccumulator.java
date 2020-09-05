package org.blaec.movies.utils;

import org.blaec.movies.objects.FailedMovieFileObject;

import java.util.ArrayList;
import java.util.List;

public class FailureAccumulator {
    private static List<FailedMovieFileObject> failUploadList;

    static {
        initFailList();
    }

    public static void addToFailList(FailedMovieFileObject failedMovieFileObject) {
        failUploadList.add(failedMovieFileObject);
    }

    public static List<FailedMovieFileObject> getFailUploadList() {
        return failUploadList;
    }

    public static void initFailList() {
        failUploadList = new ArrayList<>();
    }
}
