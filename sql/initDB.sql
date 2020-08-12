DROP TABLE IF EXISTS movies;
DROP SEQUENCE IF EXISTS movie_seq;

CREATE SEQUENCE movie_seq START 100000;

CREATE TABLE movies
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('movie_seq'),
    title       TEXT NOT NULL
--     year        TEXT NOT NULL,
--     rated       TEXT NOT NULL,
--     runtime     TEXT NOT NULL,
--     genre       TEXT NOT NULL,
--     actors      TEXT NOT NULL,
--     language    TEXT NOT NULL,
--     awards      TEXT NOT NULL,
--     imdb_rating TEXT NOT NULL,
--     imdb_id     TEXT NOT NULL,
--     poster      TEXT NOT NULL,
--     imdb_votes  TEXT NOT NULL,
--     type        TEXT NOT NULL
);

CREATE UNIQUE INDEX title_idx ON movies (title);