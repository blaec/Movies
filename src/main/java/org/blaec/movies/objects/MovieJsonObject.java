package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MovieJsonObject {
    private final @Column("id") String Title;
    private final @Column("title") String Year;
    private final @Column("year") String Rated;
    private final @Column("rated") String Runtime;
    private final @Column("runtime") String Genre;
    private final @Column("genre") String Actors;
    private final @Column("actors") String Language;
    private final @Column("language") String Awards;
    private final @Column("awards") String imdbRating;
    private final @Column("imdb_rating") String imdbID;
    private final @Column("imdb_id") String Poster;
    private final @Column("poster") String imdbVotes;
    private final @Column("imdb_votes") String Type;
}
