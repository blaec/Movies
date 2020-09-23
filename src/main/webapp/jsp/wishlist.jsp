<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<%-- Set width-height card properties before running main.js --%>
<jsp:useBean id="cardHeight" scope="request" type="java.lang.String"/>
<jsp:useBean id="cardWidth" scope="request" type="java.lang.String"/>
<input type='hidden' id='cardHeight' value=${cardHeight} />
<input type='hidden' id='cardWidth' value=${cardWidth} />

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/wishlist.css" type="text/css">
<link rel="stylesheet" href="css/card.css" type="text/css">
<link rel="stylesheet" href="css/modals.css" type="text/css">
<script type="text/javascript" src="js/wishlist.js" async></script>

<body>
    <jsp:include page="fragments/menu.jsp"/>

    <jsp:useBean id="movies" scope="request" type="java.util.List<org.blaec.movies.objects.WishListDbObject>"/>
    <jsp:useBean id="totalRuntime" scope="request" type="java.lang.String"/>

    <!-- Back to top button -->
    <a id="back-to-top" class="far fa-arrow-alt-circle-up fa-3x"></a>

    <header>
        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="stat-font mt-0">
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
                <c:forEach items="${movies}" var="movie">
                    <jsp:useBean id="movie" type="org.blaec.movies.objects.WishListDbObject"/>
                    <jsp:include page="fragments/card.jsp">
                        <jsp:param name="poster" value="${movie.poster}" />
                        <jsp:param name="rated" value="${movie.rated}" />
                        <jsp:param name="imdbRating" value="${movie.imdbRating}" />
                        <jsp:param name="imdbVotes" value="${movie.imdbVotes}" />
                        <jsp:param name="runtime" value="${movie.runtime}" />
                        <jsp:param name="year" value="${movie.year}" />
                        <jsp:param name="size" value="---" />
                        <jsp:param name="title" value="${movie.title}" />
                        <jsp:param name="genre" value="${movie.genre}" />
                        <jsp:param name="location" value="---" />
                        <jsp:param name="updatedCaption" value="Added" />
                        <jsp:param name="updated" value="${movie.added}" />
                        <jsp:param name="id" value="${movie.id}" />
                        <jsp:param name="imdbId" value="${movie.imdbId}" />
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

    <jsp:include page="fragments/modals.jsp" />
</body>
</html>