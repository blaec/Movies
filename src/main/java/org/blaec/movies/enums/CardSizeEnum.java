package org.blaec.movies.enums;

public enum CardSizeEnum {
    //width,height,caption-text,value-text,title-text
    sm_card("185px,280px,0.6em,0.8em,1.3em"),
    md_card("222px,336px,0.72em,0.96em,1.56em"),
    lg_card("277px,420px,0.9em,1.2em,1.95em");

    private final String value;

    CardSizeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
