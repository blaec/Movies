package org.blaec.movies.web;

import lombok.extern.slf4j.Slf4j;
import org.blaec.movies.dao.WishListDao;
import org.blaec.movies.objects.WishListDbObject;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.utils.RuntimeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class WishListServlet extends HttpServlet {
    private final WishListDao wishlistDao = DBIProvider.getDao(WishListDao.class);
//    private final MovieDao movieDao = DBIProvider.getDao(MovieDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<WishListDbObject> wishlist = wishlistDao.getAll();
        wishlist.sort(Comparator.comparing(WishListDbObject::getAdded));
        request.setAttribute("movies", wishlist);
        request.setAttribute("totalRuntime",
                RuntimeUtils.format(wishlist.stream().mapToInt(WishListDbObject::getRuntime).sum()));
        request.getRequestDispatcher("/jsp/wishlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO display upload results: both fail and success
        response.sendRedirect("upload");
    }
}
