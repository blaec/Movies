package org.blaec.movies.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class WishListServlet extends HttpServlet {
//    private final WishListDao wishlistDao = DBIProvider.getDao(WishListDao.class);
//    private final MovieDao movieDao = DBIProvider.getDao(MovieDao.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        switch (action == null ? "all" : action) {
//            case "delete":
//                int id = Integer.parseInt(request.getParameter("id"));
//                String imdbId = request.getParameter("imdbId");
//                String location = request.getParameter("location");
//                try {
//                    dao.deleteMovie(id);
//                    log.info("deleted movie: imdbId={} from '{}'", imdbId, location);
//                } catch (Exception e) {
//                    log.error("failed to delete movie with id {}", id, e);
//                }
//                //TODO for some reason redirects and returns updated page but does not reloads it
//                //     fixed in gallery.js within event listener
//                response.sendRedirect("gallery");
//                break;
//            case "all":
//            default:
//                List<MovieDbObject> dbMovies = dao.getAll();
//                dbMovies.sort(Comparator
//                        .comparing((MovieDbObject m) -> m.getTitle().startsWith("The ")
//                                ? m.getTitle().replace("The ", "")
//                                : m.getTitle().startsWith("A ")
//                                    ? m.getTitle().replace("A ", "")
//                                    : m.getTitle())
//                        .thenComparing(MovieDbObject::getYear));
//                if (request.getParameterMap().size() > 0) {
//                    String inputTitle = request.getParameter("input-title");
//                    dbMovies = filterMovies(inputTitle, dbMovies, m -> m.getTitle().toLowerCase().contains(inputTitle.toLowerCase()));
//
//                    String selectedGenre = request.getParameter("selected-genre");
//                    dbMovies = filterMovies(selectedGenre, dbMovies, m -> m.getGenre().contains(selectedGenre));
//
//                    String selectedActor = request.getParameter("selected-actor");
//                    dbMovies = filterMovies(selectedActor, dbMovies, m -> m.getActors().contains(selectedActor));
//                    log.info("looking for movies where title contains '{}', has genre '{}' and actor '{}' - found {}",
//                            inputTitle, selectedGenre, selectedActor, dbMovies.size());
//                }
//                dbMovies.forEach(m -> m.setLocation(MovieConverter.getKeyLocation(m.getLocation())));
//                request.setAttribute("movies", dbMovies);
//                request.setAttribute("totalSize",
//                        dbMovies.stream().mapToDouble(MovieDbObject::getSize).sum());
//                request.setAttribute("totalRuntime",
//                        RuntimeUtils.format(dbMovies.stream().mapToInt(MovieDbObject::getRuntime).sum()));
//                request.getRequestDispatcher("/jsp/gallery.jsp").forward(request, response);
//                break;
//        }
        request.getRequestDispatcher("/jsp/wishlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO display upload results: both fail and success
        response.sendRedirect("upload");
    }
//
//    private List<MovieDbObject> filterMovies(String selectParam, List<MovieDbObject> dbMovies, Predicate<MovieDbObject> filter) {
//        if (selectParam != null && !selectParam.equals(NOT_SELECTED)) {
//            dbMovies = dbMovies.stream()
//                    .filter(filter)
//                    .collect(Collectors.toList());
//        }
//        return dbMovies;
//    }
}
