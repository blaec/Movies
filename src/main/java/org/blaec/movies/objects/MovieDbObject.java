package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;
import org.blaec.movies.enums.Resolution;

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
    @Column("imdb_rating") private @NonNull String imdbRating;
    @Column("imdb_id") private @NonNull String imdbId;
    @Column("poster") private @NonNull String poster;
    @Column("imdb_votes") private @NonNull String imdbVotes;
    @Column("type") private @NonNull String type;
    @Column("resolution") private @NonNull Resolution resolution;
    @Column("size") private @NonNull String size;
    @Column("location") private @NonNull String location;
    @Column("description") private String description;
    @Column("frame_rate") private int frameRate;

    public MovieDbObject(Integer id,
                         String title,
                         String year,
                         String rated,
                         String runtime,
                         String genre,
                         String actors,
                         String language,
                         String awards,
                         String imdbRating,
                         String imdbId,
                         String poster,
                         String imdbVotes,
                         String type,
                         Resolution resolution,
                         String size,
                         String location,
                         String description,
                         int frameRate) {
        this(title,
             year,
             rated,
             runtime,
             genre,
             actors,
             language,
             awards,
             imdbRating,
             imdbId,
             poster,
             imdbVotes,
             type,
             resolution,
             size,
             location,
             description,
             frameRate);
        this.id = id;
    }
}