package org.blaec.movies.web;

import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.persist.DBIProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.blaec.movies.definitions.Definitions.NOT_SELECTED;

public class SearchServlet extends HttpServlet {
    private final MovieDao dao = DBIProvider.getDao(MovieDao.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MovieDbObject> movies = dao.getAll();
        Set<String> genres = movies.stream()
                .map(MovieDbObject::getGenre)
                .flatMap(g -> Arrays.stream(g.split(", ")))
                .collect(Collectors.toCollection(TreeSet::new));
        Set<String> actors = movies.stream()
                .map(MovieDbObject::getActors)
                .flatMap(g -> Arrays.stream(g.split(", ")))
                .collect(Collectors.toCollection(TreeSet::new));
        request.setAttribute("genres", genres);
        request.setAttribute("actors", actors);
        request.setAttribute("notSelected", NOT_SELECTED);
        request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
    }
}
