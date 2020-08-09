package org.blaec.movies;

import org.blaec.movies.objects.Movie;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.TreeSet;

public class LoadMovies {
    private static final String VIDEOS = "//LDLKONSTANTIN/Movies";

    public static void main(String[] args) {
        Set<File> movies = new TreeSet<>();
        LoadMovies.getFilesFromFolder(VIDEOS, movies);
        movies.forEach(m -> System.out.println(Movie.from(m)));
    }

    /**
     * Extracts mkv-files from folder, including subfolders
     *
     * @param dirPath folder absolute path
     * @param movies  set of movie names
     */
    public static void getFilesFromFolder(String dirPath, Set<File> movies) {
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
