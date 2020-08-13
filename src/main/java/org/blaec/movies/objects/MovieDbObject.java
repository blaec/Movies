package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovieDbObject extends BaseEntity {
    @Column("title") private @NonNull String title;
    @Column("year") private @NonNull String year;
    @Column("rated") private @NonNull String rated;
    @Column("runtime") private @NonNull String runtime;
    @Column("genre") private @NonNull String genre;
    @Column("actors") private @NonNull String actors;
    @Column("language") private @NonNull String language;
    @Column("awards") private @NonNull String awards;
    @Column("imdb_rating") private @NonNull String imdbrating;
    @Column("imdb_id") private @NonNull String imdbId;
    @Column("poster") private @NonNull String poster;
    @Column("imdb_votes") private @NonNull String imdbVotes;
    @Column("type") private @NonNull String type;

    public MovieDbObject(Integer id,
                         String title,
                         String year,
                         String rated,
                         String runtime,
                         String genre,
                         String actors,
                         String language,
                         String awards,
                         String imdbrating,
                         String imdbId,
                         String poster,
                         String imdbVotes,
                         String type) {
        this(title,
             year,
             rated,
             runtime,
             genre,
             actors,
             language,
             awards,
             imdbrating,
             imdbId,
             poster,
             imdbVotes,
             type);
        this.id = id;
    }
}
