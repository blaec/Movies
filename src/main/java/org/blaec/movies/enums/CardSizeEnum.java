package org.blaec.movies.enums;

public enum CardSizeEnum {
    sm_card_wh("185px,280px"),
    md_card_wh("222px,336px"),
    lg_card_wh("277px,420px");

    private final String value;

    CardSizeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
