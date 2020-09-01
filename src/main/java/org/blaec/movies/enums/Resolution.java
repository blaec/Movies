package org.blaec.movies.enums;

import java.util.Arrays;

public enum Resolution {
    VGA("480p"),
    HD("720p"),
    FullHD("1080p");

    private final String res;

    Resolution(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public static Resolution getResolution(String res) {
        return Arrays.stream(Resolution.values())
                .filter(r -> r.getRes().equals(res))
                .findFirst()
                .orElse(null);
    }
}
