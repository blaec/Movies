<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/gallery.css" type="text/css">
<script type="text/javascript" src="js/gallery.js" async></script>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.MovieDbObject>"/>
    <jsp:useBean id="totalRuntime" scope="request" type="java.lang.String"/>
    <jsp:useBean id="totalSize" scope="request" type="java.lang.Double"/>
    <p class="stat-font">
        movies found: <b>${fn:length(movies)}</b>  |
        total runtime: <b>${totalRuntime}</b>  |
        total size: <b><fmt:formatNumber type = "number" value = "${totalSize}"/>Gb</b>
    </p>
    <main class="gallery">
        <c:forEach items="${movies}" var="movie">
            <jsp:useBean id="movie" type="org.blaec.movies.objects.MovieDbObject"/>
            <div class="flip-container">
                <input id="${movie.imdbId}" class="movie-imdb-id" value="${movie.id}" type="hidden">
                <div class="flipper">
                    <div class="front">
                        <img class="movie-img"  src="${movie.poster}" loading="lazy" alt="NO IMAGE"/>
                    </div>
                    <div class="back movie-details p-2">
                        <p class="font-6 movie-rated-caption">rated</p>
                        <p class="font-8 movie-rated">${movie.rated}</p>
                        <p class="font-6 movie-rate-caption">rate</p>
                        <p class="font-8 movie-rate">${movie.imdbRating}</p>
                        <p class="font-6 movie-votes-caption">votes</p>
                        <p class="font-8 movie-votes">
                            <fmt:formatNumber type = "number" value = "${movie.imdbVotes}" />
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
                        <i class="fas fa-trash-alt movie-delete" data-toggle="modal" data-target="#removeMovie"></i>
                    </div>
                </div>
            </div>
        </c:forEach>
    </main>

    <div class="modal fade" id="removeMovie" tabindex="-1" aria-labelledby="removeMovieLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="removeMovieLabel">Delete movie</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Do you really want to delete movie ${movie.title}?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary allow-delete">Delete</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>