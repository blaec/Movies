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
        request.setAttribute("movies", dbMovies);
        request.getRequestDispatcher("/jsp/gallery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale());
//        engine.process("upload", webContext, response.getWriter());
    }
}
