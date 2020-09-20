<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/gallery.css" type="text/css">
<link rel="stylesheet" href="css/card.css" type="text/css">
<script type="text/javascript" src="js/gallery.js" async></script>

<body>
    <jsp:include page="fragments/menu.jsp"/>

    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.MovieDbObject>"/>
    <jsp:useBean id="totalRuntime" scope="request" type="java.lang.String"/>
    <jsp:useBean id="totalSize" scope="request" type="java.lang.Double"/>

    <!-- Back to top button -->
    <a id="back-to-top" class="far fa-arrow-alt-circle-up fa-3x"></a>

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
                    <jsp:include page="fragments/card.jsp">
                        <jsp:param name="poster" value="${movie.poster}" />
                        <jsp:param name="rated" value="${movie.rated}" />
                        <jsp:param name="imdbRating" value="${movie.imdbRating}" />
                        <jsp:param name="imdbVotes" value="${movie.imdbVotes}" />
                        <jsp:param name="runtime" value="${movie.runtime}" />
                        <jsp:param name="year" value="${movie.year}" />
                        <jsp:param name="size" value="${movie.size}Gb" />
                        <jsp:param name="title" value="${movie.title}" />
                        <jsp:param name="genre" value="${movie.genre}" />
                        <jsp:param name="location" value="${movie.location}" />
                        <jsp:param name="updatedCaption" value="Updated" />
                        <jsp:param name="updated" value="${movie.updated}" />
                        <jsp:param name="id" value="${movie.id}" />
                    </jsp:include>
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