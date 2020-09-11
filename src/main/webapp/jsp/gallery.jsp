<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/gallery.css" type="text/css">
<script type="text/javascript" src="js/gallery.js" async></script>

<body>
    <jsp:include page="fragments/menu.jsp"/>

    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.MovieDbObject>"/>
    <jsp:useBean id="totalRuntime" scope="request" type="java.lang.String"/>
    <jsp:useBean id="totalSize" scope="request" type="java.lang.Double"/>

    <header>
        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="stat-font mt-0">
                        movies found: <b>${fn:length(movies)}</b> |
                        total runtime: <b>${totalRuntime}</b> |
                        total size: <b><fmt:formatNumber type="number" value="${totalSize}"/>Gb</b>
                    </p>
                </div>
            </div>
        </div>
    </header>
    <main role="main" class="container">
        <div class="row">
            <div class="col col-12 gallery">
                <c:forEach items="${movies}" var="movie">
                    <jsp:useBean id="movie" type="org.blaec.movies.objects.MovieDbObject"/>
                    <div class="flip-container">
                        <div class="flipper">
                            <div class="front">
                                <img class="movie-img" src="${movie.poster}" loading="lazy" alt="NO IMAGE"/>
                            </div>
                            <div class="back movie-details p-2">
                                <p class="font-6 movie-rated-caption">rated</p>
                                <p class="font-8 movie-rated">${movie.rated}</p>
                                <p class="font-6 movie-rate-caption">rate</p>
                                <p class="font-8 movie-rate">${movie.imdbRating}</p>
                                <p class="font-6 movie-votes-caption">votes</p>
                                <p class="font-8 movie-votes">
                                    <fmt:formatNumber type="number" value="${movie.imdbVotes}"/>
                                </p>
                                <p class="font-6 movie-runtime-caption">runtime</p>
                                <p class="font-8 movie-runtime">${movie.runtime}min</p>
                                <p class="font-6 movie-year-caption">year</p>
                                <p class="font-8 movie-year">${movie.year}</p>
                                <p class="font-6 movie-size-caption">size</p>
                                <p class="font-8 movie-size">${movie.size}Gb</p>
                                <p class="font-8 movie-title">${movie.title}</p>
                                <p class="font-8 movie-genre">${movie.genre}</p>
                                <p class="font-8 movie-location">
                                    <i class="fas fa-hdd mr-1"></i>
                                        ${movie.location}
                                </p>
                                <i class="fas fa-sync-alt movie-sync"></i>
                                <p class="font-6 movie-updated">
                                    Updated: <fmt:formatDate value="${movie.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </p>
                                <i class="fas fa-trash-alt movie-delete"
                                   data-id="${movie.id}"
                                   data-imdb-id="${movie.imdbId}"
                                   data-location="${movie.location}"
                                   data-title="${movie.title}"
                                   data-toggle="modal"
                                   data-target="#removeMovie"></i>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>
    <footer class="footer">
        <div class="container">
<%--            <span class="text-muted">Place sticky footer content here.</span>--%>
        </div>
    </footer>


    <%-- delete movie modal --%>
    <div class="modal fade" id="removeMovie" tabindex="-1" aria-labelledby="removeMovieLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header modal-danger">
                    <h5 class="modal-title heading text-center" id="removeMovieLabel">Delete movie</h5>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="movie-id" id="movie-id"/>
                    <input type="hidden" name="movie-imdb-id" id="movie-imdb-id"/>
                    <input type="hidden" name="movie-location" id="movie-location"/>
                    <div class="row">
                        <i class="col col-3 fas fa-times fa-4x text-danger rotate"></i>
                        <p class="col col-9 mt-2 mb-0">Do you really want to delete this movie?</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn  btn-outline-danger" data-dismiss="modal">No</button>
                    <button type="button" class="btn  btn-danger allow-delete">Yes</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>