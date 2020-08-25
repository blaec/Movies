<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movies</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Kanit:wght@200&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/movieGallery.css" type="text/css">
</head>

<body>
<div class="grid">
    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.MovieDbObject>"/>
    <c:forEach items="${movies}" var="movie">
        <jsp:useBean id="movie" type="org.blaec.movies.objects.MovieDbObject"/>
        <input id="${movie.imdbId}" value="${movie.id}" type="hidden">
        <div class="flip-container">
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
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>

</body>
</html>