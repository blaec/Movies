<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/movieGallery.css" type="text/css">

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.MovieDbObject>"/>
    <p class="movie-count m-1">${fn:length(movies)} movies found</p>
    <main class="gallery">
        <c:forEach items="${movies}" var="movie">
            <jsp:useBean id="movie" type="org.blaec.movies.objects.MovieDbObject"/>
            <div class="flip-container">
<%--                <input id="${movie.imdbId}" class="movie-imdb-id" value="${movie.id}" type="hidden">--%>
                <div class="flipper">
                    <div class="front">
                        <img class="movie-img" src="${movie.poster}" alt="NO IMAGE">
                    </div>
                    <div class="back movie-details p-2">
                        <p class="m-0 font-6 movie-rated-caption">rated</p>
                        <p class="m-0 font-8 movie-rated">${movie.rated}</p>
                        <p class="m-0 font-6 movie-rate-caption">rate</p>
                        <p class="m-0 font-8 movie-rate">${movie.imdbRating}</p>
                        <p class="m-0 font-6 movie-votes-caption">votes</p>
                        <p class="m-0 font-8 movie-votes">
                            <fmt:formatNumber type = "number" value = "${movie.imdbVotes}" />
                        </p>
                        <p class="m-0 font-6 movie-runtime-caption">runtime</p>
                        <p class="m-0 font-8 movie-runtime">${movie.runtime}min</p>
                        <p class="m-0 font-6 movie-year-caption">year</p>
                        <p class="m-0 font-8 movie-year">${movie.year}</p>
                        <p class="m-0 font-6 movie-size-caption">size</p>
                        <p class="m-0 font-8 movie-size">${movie.size}Gb</p>
                        <p class="m-0 font-8 movie-genre">${movie.genre}</p>
                        <p class="m-0 font-6 movie-updated">
                            Last updated <fmt:formatDate value="${movie.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </p>
                        <svg value="${movie.id}"
                             width="1em"
                             height="1em"
                             viewBox="0 0 16 16"
                             class="bi bi-trash-fill"
                             fill="currentColor"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                        </svg>
                    </div>
                </div>
            </div>
        </c:forEach>
    </main>c
</body>
<script type="text/javascript" src="js/gallery.js" defer></script>
</html>