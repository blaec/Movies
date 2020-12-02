<%@ attribute name="poster" required="true" rtexprvalue="true" %>
<%@ attribute name="rated" required="true" rtexprvalue="true" %>
<%@ attribute name="imdbRating" required="true" rtexprvalue="true" %>
<%@ attribute name="imdbVotes" required="true" rtexprvalue="true" %>
<%@ attribute name="runtime" required="true" rtexprvalue="true" %>
<%@ attribute name="year" required="true" rtexprvalue="true" %>
<%@ attribute name="size" required="true" rtexprvalue="true" %>
<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="genre" required="true" rtexprvalue="true" %>
<%@ attribute name="location" required="true" rtexprvalue="true" %>
<%@ attribute name="updated" required="true" rtexprvalue="true" %>
<%@ attribute name="updatedCaption" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="imdbId" required="true" rtexprvalue="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="flip-container">
    <div class="flipper">

        <%-- front movie card --%>
        <div class="front">
            <img class="movie-img" src="${poster}" loading="lazy" alt="NO IMAGE"/>
        </div>

        <%-- back movie card --%>
        <div class="back movie-details p-2">
            <p class="font-sm movie-rated-caption">rated</p>
            <p class="font-md movie-rated">${rated}</p>
            <p class="font-sm movie-rate-caption">rate</p>
            <p class="font-md movie-rate">${imdbRating}</p>
            <p class="font-sm movie-votes-caption">votes</p>
            <p class="font-md movie-votes">
                <fmt:formatNumber type="number" value="${imdbVotes}"/>
            </p>
            <p class="font-sm movie-runtime-caption">runtime</p>
            <p class="font-md movie-runtime">${runtime}min</p>
            <p class="font-sm movie-year-caption">year</p>
            <p class="font-md movie-year">${year}</p>
            <p class="font-sm movie-size-caption">size</p>
            <p class="font-md movie-size">${size}Gb</p>
            <p class="font-lg movie-title">${title}</p>
            <p class="font-md movie-genre">${genre}</p>
            <p class="font-md movie-location">
                <i class="fas fa-hdd mr-1"></i>
                ${location}
            </p>
            <i class="fas fa-sync-alt movie-sync"></i>

            <p class="font-sm movie-updated">
                <fmt:parseDate value="${updated}" var="parsedDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                ${updatedCaption}: <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </p>
            <i class="fas fa-trash-alt movie-delete"
               data-id="${id}"
               data-imdb-id="${imdbId}"
               data-location="${location}"
               data-title="${title}"
               data-toggle="modal"
               data-target="#removeMovie"></i>
        </div>
    </div>
</div>
