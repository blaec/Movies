package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MovieJsonObject {
    private final @Column("title") String Title;
    private final @Column("year") String Year;
    private final @Column("rated") String Rated;
    private final @Column("runtime") String Runtime;
    private final @Column("genre") String Genre;
    private final @Column("actors") String Actors;
    private final @Column("language") String Language;
    private final @Column("awards") String Awards;
    private final @Column("imdb_rating") String imdbRating;
    private final @Column("imdb_id") String imdbID;
    private final @Column("poster") String Poster;
    private final @Column("imdb_votes") String imdbVotes;
    private final @Column("type") String Type;
}
