<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="flip-container">
    <div class="flipper">

        <%-- front movie card --%>
        <div class="front">
            <img class="movie-img" src="${param.poster}" loading="lazy" alt="NO IMAGE"/>
        </div>

        <%-- back movie card --%>
        <div class="back movie-details p-2">
            <p class="font-sm movie-rated-caption">rated</p>
            <p class="font-md movie-rated">${param.rated}</p>
            <p class="font-sm movie-rate-caption">rate</p>
            <p class="font-md movie-rate">${param.imdbRating}</p>
            <p class="font-sm movie-votes-caption">votes</p>
            <p class="font-md movie-votes">
                <fmt:formatNumber type="number" value="${param.imdbVotes}"/>
            </p>
            <p class="font-sm movie-runtime-caption">runtime</p>
            <p class="font-md movie-runtime">${param.runtime}min</p>
            <p class="font-sm movie-year-caption">year</p>
            <p class="font-md movie-year">${param.year}</p>
            <p class="font-sm movie-size-caption">size</p>
            <p class="font-md movie-size">${param.size}Gb</p>
            <p class="font-lg movie-title">${param.title}</p>
            <p class="font-md movie-genre">${param.genre}</p>
            <p class="font-md movie-location">
                <i class="fas fa-hdd mr-1"></i>
                ${param.location}
            </p>
            <i class="fas fa-sync-alt movie-sync"></i>

            <p class="font-sm movie-updated">
                <fmt:parseDate value="${param.updated}" var="parsedDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                ${param.updatedCaption}: <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </p>
            <i class="fas fa-trash-alt movie-delete"
               data-id="${param.id}"
               data-imdb-id="${param.imdbId}"
               data-location="${param.location}"
               data-title="${param.title}"
               data-toggle="modal"
               data-target="#removeMovie"></i>
        </div>
    </div>
</div>
