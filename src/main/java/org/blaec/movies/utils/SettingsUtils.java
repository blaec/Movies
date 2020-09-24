package org.blaec.movies.utils;

import org.blaec.movies.enums.SettingsEnum;

import java.util.HashMap;
import java.util.Map;

import static org.blaec.movies.enums.CardSizeEnum.sm_card_wh;

public class SettingsUtils {
    private static final Map<SettingsEnum, String> settings = new HashMap<>();
    private static final String DELIMITER = ",";

    // init default settings
    static {
        settings.put(SettingsEnum.CARD_SIZE, sm_card_wh.getValue());
    }

    public static String get(SettingsEnum key) {
        return settings.get(key);
    }

    public static String getParam(SettingsEnum key, int position) {
        return get(key).split(DELIMITER)[position];
    }

    public static void update(SettingsEnum key, String newValue) {
        settings.put(key, newValue);
    }
}
