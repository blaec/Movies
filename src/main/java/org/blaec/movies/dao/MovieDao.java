package org.blaec.movies.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.blaec.movies.objects.MovieDbObject;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MovieDao implements AbstractDao {

    @SqlUpdate("TRUNCATE movies CASCADE")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM movies")
    public abstract List<MovieDbObject> getAll();

    @SqlUpdate("DELETE FROM movies WHERE id=:it")
    public abstract void deleteMovie(@Bind int id);

    @SqlUpdate("INSERT INTO movies " +
                           "(title, " +
                            "year, " +
                            "rated, " +
                            "runtime, " +
                            "genre, " +
                            "actors, " +
                            "language, " +
                            "awards, " +
                            "imdb_rating, " +
                            "imdb_id, " +
                            "poster, " +
                            "imdb_votes, " +
                            "type, " +
                            "resolution, " +
                            "size, " +
                            "location, " +
                            "description, " +
                            "frame_rate, " +
                            "updated) " +
               "VALUES      (:title, " +
                            ":year, " +
                            ":rated, " +
                            ":runtime, " +
                            ":genre, " +
                            ":actors, " +
                            ":language, " +
                            ":awards, " +
                            ":imdbRating, " +
                            ":imdbId, " +
                            ":poster, " +
                            ":imdbVotes, " +
                            ":type, " +
                            "Cast(:resolution AS RESOLUTION_TYPE)," +
                            ":size, " +
                            ":location, " +
                            ":description," +
                            ":frameRate, " +
                            ":updated)")
    @GetGeneratedKeys
    public abstract int insert(@BindBean MovieDbObject movies);
}