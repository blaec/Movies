package org.blaec.movies.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import org.blaec.movies.objects.WishListDbObject;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class WishListDao implements AbstractDao {

    @SqlUpdate("TRUNCATE wish_list CASCADE")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM wish_list")
    public abstract List<WishListDbObject> getAll();

    @SqlQuery("SELECT * FROM wish_list WHERE imdb_id LIKE :it")
    public abstract WishListDbObject getByImdbId(@Bind String imdbId);

    @SqlUpdate("DELETE FROM wish_list WHERE id=:it")
    public abstract void deleteMovie(@Bind int id);

    @SqlUpdate("INSERT INTO wish_list " +
                           "(title, " +
                            "year, " +
                            "rated, " +
                            "runtime, " +
                            "genre, " +
                            "imdb_rating, " +
                            "imdb_id, " +
                            "poster, " +
                            "imdb_votes, " +
                            "added) " +
               "VALUES      (:title, " +
                            ":year, " +
                            ":rated, " +
                            ":runtime, " +
                            ":genre, " +
                            ":imdbRating, " +
                            ":imdbId, " +
                            ":poster, " +
                            ":imdbVotes, " +
                            ":added)")
    @GetGeneratedKeys
    public abstract int insert(@BindBean WishListDbObject movies);
}