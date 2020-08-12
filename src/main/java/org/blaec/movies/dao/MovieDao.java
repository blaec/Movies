package org.blaec.movies.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.blaec.movies.objects.TestMovie;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MovieDao implements AbstractDao {

    @SqlUpdate("TRUNCATE movies CASCADE ")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM movies")
    public abstract List<TestMovie> getAll();

//    public Map<String, City> getAsMap() {
//        return StreamEx.of(getAll()).toMap(City::getRef, identity());
//    }
//
    @SqlUpdate("INSERT INTO movies (title) VALUES (:title)")
    @GetGeneratedKeys
    public abstract int insert(@BindBean TestMovie movies);
//    @SqlUpdate("INSERT INTO movies (title, year, rated, runtime, genre, actors, language, awards, imdb_rating, imdb_id, poster, imdb_votes, type) " +
//                    "VALUES (:Title, :Year, :Rated, :Runtime, :Genre, :Actors, :Language, :Awards, :imdbRating, :imdbID, :Poster, :imdbVotes, :Type)")
//    @GetGeneratedKeys
//    public abstract int insert(@BindBean MovieJsonObject movies);
//
//    @SqlBatch("INSERT INTO movies (ref, name) VALUES (:ref, :name)")
//    public abstract void insertBatch(@BindBean Collection<City> cities);
}