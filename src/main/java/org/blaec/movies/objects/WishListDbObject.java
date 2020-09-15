package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WishListDbObject extends BaseEntity {
    @Column("title") private @NonNull String title;
    @Column("year") private @NonNull int year;
    @Column("rated") private @NonNull String rated;
    @Column("runtime") private @NonNull int runtime;
    @Column("genre") private @NonNull String genre;
    @Column("imdb_rating") private @NonNull Double imdbRating;
    @Column("imdb_id") private @NonNull String imdbId;
    @Column("poster") private @NonNull String poster;
    @Column("imdb_votes") private @NonNull int imdbVotes;
    @Column("added") private Timestamp added;

    public WishListDbObject(Integer id,
                            String title,
                            int year,
                            String rated,
                            int runtime,
                            String genre,
                            Double imdbRating,
                            String imdbId,
                            String poster,
                            int imdbVotes,
                            Timestamp added) {
        this(title,
             year,
             rated,
             runtime,
             genre,
             imdbRating,
             imdbId,
             poster,
             imdbVotes,
             added);
        this.id = id;
    }
}