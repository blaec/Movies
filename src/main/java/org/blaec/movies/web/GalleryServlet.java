package org.blaec.movies.web;

import lombok.extern.slf4j.Slf4j;
import org.blaec.movies.dao.MovieDao;
import org.blaec.movies.enums.SettingsEnum;
import org.blaec.movies.objects.MovieDbObject;
import org.blaec.movies.persist.DBIProvider;
import org.blaec.movies.utils.MovieConverter;
import org.blaec.movies.utils.RuntimeUtils;
import org.blaec.movies.utils.SettingsUtils;

import javax.servlet.ServletContext;
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

@Slf4j
public class GalleryServlet extends HttpServlet {
    private final MovieDao dao = DBIProvider.getDao(MovieDao.class);
    private static String encoding;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        encoding = context.getInitParameter("PARAMETER_ENCODING");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (encoding != null) {
            request.setCharacterEncoding(encoding);
        }
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete": //TODO delete should be handled by post
                int id = Integer.parseInt(request.getParameter("id"));
                String imdbId = request.getParameter("imdbId");
                String location = request.getParameter("location");
                try {
                    dao.deleteMovie(id);
                    log.info("deleted movie: imdbId={} from '{}'", imdbId, location);
                } catch (Exception e) {
                    log.error("failed to delete movie with id {}", id, e);
                }
                //TODO for some reason redirects and returns updated page but does not reloads it
                //     fixed in gallery.js within event listener
                // try to resend it to doGet
                response.sendRedirect("gallery");
                break;
            case "all":
            default:
                List<MovieDbObject> dbMovies = dao.getAll();
                dbMovies.sort(Comparator
                        .comparing((MovieDbObject m) -> m.getTitle().startsWith("The ")
                                ? m.getTitle().replace("The ", "")
                                : m.getTitle().startsWith("A ")
                                    ? m.getTitle().replace("A ", "")
                                    : m.getTitle())
                        .thenComparing(MovieDbObject::getYear));
                if (request.getParameterMap().size() > 0) {
                    String inputTitle = request.getParameter("input-title");
                    dbMovies = filterMovies(inputTitle, dbMovies, m -> m.getTitle().toLowerCase().contains(inputTitle.toLowerCase()));

                    String selectedGenre = request.getParameter("selected-genre");
                    dbMovies = filterMovies(selectedGenre, dbMovies, m -> m.getGenre().contains(selectedGenre));

                    String selectedActor = request.getParameter("selected-actor");
                    dbMovies = filterMovies(selectedActor, dbMovies, m -> m.getActors().contains(selectedActor));
                    log.info("looking for movies where title contains '{}', has genre '{}' and actor '{}' - found {}",
                            inputTitle, selectedGenre, selectedActor, dbMovies.size());
                }
                dbMovies.forEach(m -> m.setLocation(MovieConverter.getKeyLocation(m.getLocation())));
                request.setAttribute("movies", dbMovies);
                request.setAttribute("totalSize",
                        dbMovies.stream().mapToDouble(MovieDbObject::getSize).sum());
                request.setAttribute("totalRuntime",
                        RuntimeUtils.format(dbMovies.stream().mapToInt(MovieDbObject::getRuntime).sum()));
                request.setAttribute("cardSize", SettingsUtils.getVal(SettingsEnum.CARD_SIZE));
                request.getRequestDispatcher("/jsp/gallery.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private List<MovieDbObject> filterMovies(String selectParam, List<MovieDbObject> dbMovies, Predicate<MovieDbObject> filter) {
        if (selectParam != null && !selectParam.equals(NOT_SELECTED)) {
            dbMovies = dbMovies.stream()
                    .filter(filter)
                    .collect(Collectors.toList());
        }
        return dbMovies;
    }
}
