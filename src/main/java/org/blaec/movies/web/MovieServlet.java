package org.blaec.movies.web;

import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.persist.DBIProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MovieServlet extends HttpServlet {
    private final MovieDao dao = DBIProvider.getDao(MovieDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieDbObject> dbMovies = dao.getAll();
        req.setAttribute("movies", dbMovies);
        req.getRequestDispatcher("/gallery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
//        engine.process("upload", webContext, resp.getWriter());
    }
}
