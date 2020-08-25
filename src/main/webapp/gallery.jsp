<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <title>Movies</title>

    <style>

        /* general grid movie-page */
        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, 200px);
        }
        body {
            background: #24282f !important;
            margin: 0 20rem !important;
        }

        /* general movie card */
        .card {
            border: none !important;
        }
        .movie-img {
            width: 200px;
            height: 300px;
        }

        /* flip movie card */
        .flip-container {
            background: #24282f !important;
            perspective: 1000px;
        }
        /* flip the pane when hovered */
        .flip-container:hover .flipper, .flip-container.hover .flipper {
            transform: rotateY(180deg);
        }
        .flip-container, .front, .back {
            width: 200px;
            height: 300px;
        }
        /* flip speed goes here */
        .flipper {
            transition: 0.6s;
            transform-style: preserve-3d;
            position: relative;
        }
        /* hide back of pane during swap */
        .front, .back {
            backface-visibility: hidden;
            position: absolute;
            top: 0;
            left: 0;
        }
        /* front pane, placed above back */
        .front {
            z-index: 2;
            /* for firefox 31 */
            transform: rotateY(0deg);
        }
        /* back, initially hidden pane */
        .back {
            transform: rotateY(180deg);
            display: grid;
            grid-template-columns: 33.3% 33.4% 33.3%;
            grid-template-rows: 5% 10% 5% 10% 10% 60%;
            grid-template-areas:
                "rated-capt rate-capt votes-capt"
                "rated rate votes"
                "runtime-capt year-capt size-capt"
                "runtime year size"
                "genre genre genre"
                "updated updated updated";
            justify-items: center;
            background: #1d2124;
            font-family: 'Kanit', sans-serif;
        }

        .movie-rated, .movie-rate, .movie-votes, .movie-runtime, .movie-year, .movie-size, .movie-genre {
            font-size: 0.8em;
            color: #adb5bd;
        }

        .movie-rated-caption, .movie-rate-caption, .movie-votes-caption, .movie-runtime-caption, .movie-year-caption, .movie-size-caption, .movie-updated {
            font-size: 0.6em;
            color: #adb5bd;
        }

        .movie-rated-caption {
            grid-area: rated-capt;
        }
        .movie-rated {
            grid-area: rated;
        }

        .movie-rate-caption {
            grid-area: rate-capt;
        }
        .movie-rate {
            grid-area: rate;
        }

        .movie-votes-caption {
            grid-area: votes-capt;
        }
        .movie-votes {
            grid-area: votes;
        }

        .movie-runtime-caption {
            grid-area: runtime-capt;
        }
        .movie-runtime {
            grid-area: runtime;
        }

        .movie-year-caption {
            grid-area: year-capt;
        }
        .movie-year {
            grid-area: year;
        }

        .movie-size-caption {
            grid-area: size-capt;
        }
        .movie-size {
            grid-area: size;
        }

        .movie-genre {
            grid-area: genre;
            justify-self: center;
            text-align: center;
        }

        .movie-updated {
            grid-area: updated;
            justify-self: center;
            align-self: end;
        }

    </style>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Kanit:wght@200&display=swap" rel="stylesheet">
</head>

<body>
<div class="grid">
    <c:forEach items="${movies}" var="movie">
        <jsp:useBean id="movie" type="org.blaec.movies.objects.MovieDbObject"/>
        <input id="${movie.imdbId}" value="${movie.id}" type="hidden">
        <div class="flip-container">
            <div class="flipper">
                <div class="front">
                    <img class="movie-img" src="${movie.poster}" alt="https://via.placeholder.com/200x300.png?text=No%20Image">
                </div>
                <div class="back p-2">
                    <p class="m-0 movie-rated-caption">rated</p>
                    <p class="m-0 movie-rated">${movie.rated}</p>
                    <p class="m-0 movie-rate-caption">rate</p>
                    <p class="m-0 movie-rate">${movie.imdbRating}</p>
                    <p class="m-0 movie-votes-caption">votes</p>
                    <p class="m-0 movie-votes">
                        <fmt:formatNumber type = "number" value = "${movie.imdbVotes}" />
                    </p>
                    <p class="m-0 movie-runtime-caption">runtime</p>
                    <p class="m-0 movie-runtime">${movie.runtime}min</p>
                    <p class="m-0 movie-year-caption">year</p>
                    <p class="m-0 movie-year">${movie.year}</p>
                    <p class="m-0 movie-size-caption">size</p>
                    <p class="m-0 movie-size">${movie.size}Gb</p>
                    <p class="m-0 movie-genre">${movie.genre}</p>
                    <p class="m-0 movie-updated">
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