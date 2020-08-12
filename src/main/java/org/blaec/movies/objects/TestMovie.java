package org.blaec.movies.objects;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestMovie extends BaseEntity {
    @Column("title")
    private @NonNull String title;

    public TestMovie(Integer id, String title) {
        this(title);
        this.id=id;
    }
}
