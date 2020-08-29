package org.blaec.movies.web;

import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.persist.DBIProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.blaec.movies.definitions.Definitions.NOT_SELECTED;


public class MovieServlet extends HttpServlet {
    private final MovieDao dao = DBIProvider.getDao(MovieDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MovieDbObject> dbMovies = dao.getAll();
        dbMovies.sort(Comparator
                .comparing((MovieDbObject m) -> m.getTitle().startsWith("The ")
                        ? m.getTitle().replace("The ", "")
                        : m.getTitle())
                .thenComparing(MovieDbObject::getYear));
        if (request.getParameterMap().size() > 0) {
            String selectedGenre = request.getParameter("selected-genre");
            dbMovies = filterMovies(selectedGenre, dbMovies, m -> m.getGenre().contains(selectedGenre));
        }
        request.setAttribute("movies", dbMovies);
        request.getRequestDispatcher("/jsp/gallery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale());
//        engine.process("upload", webContext, response.getWriter());
    }

    private List<MovieDbObject> filterMovies(String selectParam, List<MovieDbObject> dbMovies, Predicate<MovieDbObject> filter) {
        if (!selectParam.equals(NOT_SELECTED)) {
            dbMovies = dbMovies.stream()
                    .filter(filter)
                    .collect(Collectors.toList());
        }
        return dbMovies;
    }
}
