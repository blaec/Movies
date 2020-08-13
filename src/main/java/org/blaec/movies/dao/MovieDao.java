package org.blaec.movies.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.blaec.movies.objects.MovieJsonObject;
import org.blaec.movies.objects.MovieDbObject;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MovieDao implements AbstractDao {

    @SqlUpdate("TRUNCATE movies CASCADE ")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM movies")
    public abstract List<MovieDbObject> getAll();

//    @SqlQuery("SELECT * FROM movies WHERE title like :title")
//    @SqlQuery("SELECT m FROM movies m WHERE m.title=:title")
//    public abstract MovieDataObject getByTitle(@Bind("title") String title);

    @SqlUpdate("INSERT INTO movies (title, year, rated, runtime, genre, actors, language, awards, imdb_rating, imdb_id, poster, imdb_votes, type) " +
                    "VALUES (:title, :year, :rated, :runtime, :genre, :actors, :language, :awards, :imdbRating, :imdbID, :poster, :imdbVotes, :type)")
    @GetGeneratedKeys
    public abstract int insert(@BindBean MovieJsonObject movies);
//
//    @SqlBatch("INSERT INTO movies (ref, name) VALUES (:ref, :name)")
//    public abstract void insertBatch(@BindBean Collection<City> cities);
}