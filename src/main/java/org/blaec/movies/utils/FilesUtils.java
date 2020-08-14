package org.blaec.movies.utils;

import org.blaec.movies.objects.MovieFileObject;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FilesUtils {
    private static final FileFilter filter = pathname -> pathname.isDirectory() || pathname.getName().endsWith("mkv");
    private static final Set<File> movies = new TreeSet<>();

    /**
     * Returns sorted list of movies from folder
     *
     * @param dirPath absolute path to folder with video-files
     * @return sorted list of movie objects or empty list
     */
    public static List<MovieFileObject> getMoviesFromFolder(String dirPath) {
        getFilesFromFolder(dirPath);
        return movies.stream()
                .map(MovieFileObject::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Extracts recursively mkv-files from folder, including sub-folders
     *
     * @param dirPath absolute path to folder with video-files
     */
    private static void getFilesFromFolder(String dirPath) {
        File[] files = (new File(dirPath)).listFiles(filter);
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    movies.add(file);
                } else if (file.isDirectory()) {
                    getFilesFromFolder(file.getAbsolutePath());
                }
            }
        }
    }
}
