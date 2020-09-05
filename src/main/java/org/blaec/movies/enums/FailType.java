package org.blaec.movies.enums;

public enum FailType {
    PARSE("parsing error"),
    DB_SAVE("db save error");

    private final String description;

    FailType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
