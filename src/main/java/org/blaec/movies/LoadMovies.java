package org.blaec.movies;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.regex.Pattern;

public class LoadMovies {
    private static final String VIDEOS = "//LDLKONSTANTIN/Movies";

    public static void main(String[] args) {
        Set<String> movies = new TreeSet<>();
        LoadMovies.getFilesFromFolder(VIDEOS, movies);
        movies.forEach(System.out::println);
//        Movie from = Movie.from("Dumb & Dumber [Unrated] (1994) [720p] [60fps].mkv");
//        System.out.println(from);
    }

    /**
     * Extracts mkv-files from folder, including subfolders
     *
     * @param dirPath folder absolute path
     * @param movies  set of movie names
     */
    public static void getFilesFromFolder(String dirPath, Set<String> movies) {
        File[] files;
        FileFilter filter = pathname -> pathname.isDirectory() || pathname.getName().endsWith("mkv");
        if ((files = (new File(dirPath)).listFiles(filter)) != null) {
            for (File file : files) {
                if (file.isFile()) {
                    movies.add(file.getName());
                } else if (file.isDirectory()) {
                    getFilesFromFolder(file.getAbsolutePath(), movies);
                }
            }
        }
    }
}
