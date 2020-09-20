--liquibase formatted sql

--changeset blaec:1
CREATE TABLE wish_list
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('movie_seq'),
    title       TEXT            NOT NULL,
    year        INTEGER         NOT NULL,
    rated       TEXT            NOT NULL,
    runtime     INTEGER         NOT NULL,
    genre       TEXT            NOT NULL,
    imdb_rating DECIMAL         NOT NULL,
    imdb_id     TEXT            NOT NULL,
    poster      TEXT            NOT NULL,
    imdb_votes  INTEGER         NOT NULL,
    added       TIMESTAMP       NOT NULL
);

CREATE UNIQUE INDEX wish_list_unique_title_year_idx ON wish_list (title, year);
--rollback drop table wish_list;