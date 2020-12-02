<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>

<html>
    <frag:headTag/>
    <link rel="stylesheet" href="css/wishlist.css" type="text/css">
    <link rel="stylesheet" href="css/card.css" type="text/css">
    <link rel="stylesheet" href="css/modals.css" type="text/css">

    <jsp:useBean id="cardSize" scope="request" type="java.lang.String"/>
    <input type='hidden' id='cardSize' value=${cardSize} />
    <script type="text/javascript" src="js/wishlist.js" async></script>

    <body>
        <frag:menu/>

        <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.WishListDbObject>"/>
        <jsp:useBean id="totalRuntime" scope="request" type="java.lang.String"/>


        <!-- Back to top button -->
        <a id="back-to-top" class="far fa-arrow-alt-circle-up fa-3x"></a>

        <header>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <p class="stat-font">
                            movies found: <b>${fn:length(movies)}</b> |
                            total runtime: <b>${totalRuntime}</b>
                        </p>
                    </div>
                </div>
            </div>
        </header>
        <main role="main" class="container">
            <div class="row">
                <div class="col col-12 gallery">
                    <c:forEach var="movie" items="${movies}">
                        <jsp:useBean id="movie" type="org.blaec.movies.objects.WishListDbObject"/>
                        <frag:card poster="${movie.poster}"
                                   rated="${movie.rated}"
                                   imdbRating="${movie.imdbRating}"
                                   imdbVotes="${movie.imdbVotes}"
                                   runtime="${movie.runtime}"
                                   year="${movie.year}"
                                   size="---"
                                   title="${movie.title}"
                                   genre="${movie.genre}"
                                   location="---"
                                   updated="${movie.added}"
                                   updatedCaption="Added"
                                   id="${movie.id}"
                                   imdbId="${movie.imdbId}"/>
                    </c:forEach>
                </div>
            </div>
        </main>
        <footer class="footer">
            <div class="container">
    <%--            <span class="text-muted">Place sticky footer content here.</span>--%>
            </div>
        </footer>

        <frag:modals/>
    </body>
</html>