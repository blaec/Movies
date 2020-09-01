package org.blaec.movies.utils;

import org.blaec.movies.objects.MovieFileObject;

import java.io.File;
import java.io.FileFilter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FilesUtils {
    public static final long ONE_KB = 1024;
    public static final long ONE_MB = ONE_KB * ONE_KB;
    public static final long ONE_GB = ONE_KB * ONE_MB;
    public static final long ONE_TB = ONE_KB * ONE_GB;

    public static final BigDecimal ONE_KB_BD = BigDecimal.valueOf(ONE_KB);
    public static final BigDecimal ONE_MB_BD = BigDecimal.valueOf(ONE_MB);
    public static final BigDecimal ONE_GB_BD = BigDecimal.valueOf(ONE_GB);
    public static final BigDecimal ONE_TB_BD = BigDecimal.valueOf(ONE_TB);

    private static final FileFilter filter = pathname -> pathname.isDirectory()
                                                      || pathname.getName().endsWith("mkv")
                                                      || pathname.getName().endsWith("avi");
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

    /**
     * Calculate file size in gb
     *
     * @param size file size in bytes
     * @return file size rounded to 2 digits after the decimal point
     */
    public static double byteToGb(long size) {
        return BigDecimal.valueOf(size)
                .divide(ONE_GB_BD, 2, RoundingMode.DOWN)
                .doubleValue();
    }
}
