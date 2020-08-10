package org.blaec.movies.utils;

import org.blaec.movies.objects.MovieFileObject;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LoadMovies {
    /**
     * Returns sorted list of movies from folder
     *
     * @param dirPath absolute path to folder with video-files
     * @return sorted list of movie objects
     */
    public static List<MovieFileObject> getMoviesFromFolder(String dirPath) {
        Set<File> movies = new TreeSet<>();
        LoadMovies.getFilesFromFolder(dirPath, movies);
        return movies.stream()
                .map(MovieFileObject::from)
                .collect(Collectors.toList());
    }

    /**
     * Extracts recursively mkv-files from folder, including sub-folders
     *
     * @param dirPath absolute path to folder with video-files
     * @param movies  set of movie names
     */
    private static void getFilesFromFolder(String dirPath, Set<File> movies) {
        File[] files;
        FileFilter filter = pathname -> pathname.isDirectory() || pathname.getName().endsWith("mkv");
        if ((files = (new File(dirPath)).listFiles(filter)) != null) {
            for (File file : files) {
                if (file.isFile()) {
                    movies.add(file);
                } else if (file.isDirectory()) {
                    getFilesFromFolder(file.getAbsolutePath(), movies);
                }
            }
        }
    }
}
