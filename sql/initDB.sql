DROP TABLE IF EXISTS movies;
DROP SEQUENCE IF EXISTS movie_seq;
DROP TYPE IF EXISTS resolution_type;

CREATE TYPE resolution_type AS ENUM ('HD', 'FullHD');

CREATE SEQUENCE movie_seq START 100000;

CREATE TABLE movies
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('movie_seq'),
    title       TEXT            NOT NULL,
    year        INTEGER         NOT NULL,
    rated       TEXT            NOT NULL,
    runtime     INTEGER         NOT NULL,
    genre       TEXT            NOT NULL,
    actors      TEXT            NOT NULL,
    language    TEXT            NOT NULL,
    awards      TEXT            NOT NULL,
    imdb_rating DECIMAL         NOT NULL,
    imdb_id     TEXT            NOT NULL,
    poster      TEXT            NOT NULL,
    imdb_votes  INTEGER         NOT NULL,
    type        TEXT            NOT NULL,
    resolution  resolution_type NOT NULL,
    size        DECIMAL         NOT NULL,
    location    TEXT            NOT NULL,
    description TEXT,
    frame_rate  INTEGER,
    updated     TIMESTAMP       NOT NULL
);

CREATE UNIQUE INDEX movies_unique_title_year_idx ON movies (title, year);