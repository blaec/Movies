DELETE FROM movies;
ALTER SEQUENCE movie_seq RESTART WITH 100000;

INSERT INTO movies (title,
                    year,
                    rated,
                    runtime,
                    genre,
                    actors,
                    language,
                    awards,
                    imdb_rating,
                    imdb_id,
                    poster,
                    imdb_votes,
                    type,
                    resolution,
                    size,
                    location,
                    description,
                    frame_rate,
                    updated)
VALUES ('War', 2007, 'R', 103, 'Action, Thriller', 'Jet Li, Jason Statham, John Lone, Devon Aoki',
        'English, Mandarin, Japanese, Cantonese', 'N/A', 6.2, 'tt0499556',
        'https://m.media-amazon.com/images/M/MV5BNTIwMjE2Mjc1MF5BMl5BanBnXkFtZTYwNzI0OTI3._V1_SX300.jpg', 83781,
        'movie', 'HD', 8.02, '\\LDLKONSTANTIN\Movies', '', 24, '2020-08-15 22:58:40.628'),
       ('War Horse', 2011, 'PG-13', 146, 'Action, Adventure, Drama, History, War',
        'Jeremy Irvine, Peter Mullan, Emily Watson, Niels Arestrup', 'English, German',
        'Nominated for 6 Oscars. Another 15 wins & 71 nominations.', 7.2, 'tt1568911',
        'https://m.media-amazon.com/images/M/MV5BMjExNzkxOTYyNl5BMl5BanBnXkFtZTcwODA0MjU4Ng@@._V1_SX300.jpg', 143515,
        'movie', 'HD', 12.33, '\\LDLKONSTANTIN\Movies', '', 24, '2020-08-15 22:58:40.956');

