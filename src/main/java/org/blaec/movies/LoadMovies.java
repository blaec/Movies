package org.blaec.movies;

import org.blaec.movies.objects.Movie;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LoadMovies {
    private static final String MOVIES = "//LDLKONSTANTIN/Movies";
    private static final String CARTOONS = "//LDLKONSTANTIN/Cartoons";

    public static void main(String[] args) {
        getMoviesFromFolder(MOVIES).forEach(System.out::println);
        getMoviesFromFolder(CARTOONS).forEach(System.out::println);
    }

    /**
     * Returns sorted list of movies from folder
     *
     * @param dirPath absolute path to folder with video-files
     * @return sorted list of movie objects
     */
    public static List<Movie> getMoviesFromFolder(String dirPath) {
        Set<File> movies = new TreeSet<>();
        LoadMovies.getFilesFromFolder(dirPath, movies);
        return movies.stream()
                .map(Movie::from)
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
