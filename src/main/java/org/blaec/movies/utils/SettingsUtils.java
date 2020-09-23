package org.blaec.movies.utils;

import org.blaec.movies.enums.SettingsEnum;

import java.util.HashMap;
import java.util.Map;

import static org.blaec.movies.enums.CardSizeEnum.sm_card_wh;

public class SettingsUtils {
    public static Map<SettingsEnum, String> settings = new HashMap<>();

    // default settings
    static {
        settings.put(SettingsEnum.CARD_SIZE, sm_card_wh.getValue());
    }
}
