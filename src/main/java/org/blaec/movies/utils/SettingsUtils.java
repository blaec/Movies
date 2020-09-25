package org.blaec.movies.utils;

import org.blaec.movies.enums.CardSizeEnum;
import org.blaec.movies.enums.SettingsEnum;

import java.util.HashMap;
import java.util.Map;

import static org.blaec.movies.enums.CardSizeEnum.sm_card_wh;

public class SettingsUtils {
    private static final Map<SettingsEnum, CardSizeEnum> settings = new HashMap<>();
    private static final String DELIMITER = ",";

    // init default settings
    static {
        settings.put(SettingsEnum.CARD_SIZE, sm_card_wh);
    }

    public static String getVal(SettingsEnum key) {
        return settings.get(key).getValue();
    }

    public static CardSizeEnum get(SettingsEnum key) {
        return settings.get(key);
    }

    public static String getParam(SettingsEnum key, int position) {
        return getVal(key).split(DELIMITER)[position];
    }

    public static void update(SettingsEnum key, CardSizeEnum newSize) {
        settings.put(key, newSize);
    }
}
