<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/search.css" type="text/css">

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <form method="get" action="gallery">
        <div class="form-group search">
            <jsp:useBean id="genres" scope="request" type="java.util.Set<java.lang.String>"/>
            <label class="genre-label mr-2" for="movie-genre">Movie genre [${fn:length(genres)}]</label>
            <select id="movie-genre"
                    class="form-control selectpicker"
                    name="selected-genre">
                <option selected>${notSelected}</option>
                <c:forEach items="${genres}" var="genre">
                    <jsp:useBean id="genre" type="java.lang.String"/>
                    <option>${genre}</option>
                </c:forEach>
            </select>

            <jsp:useBean id="actors" scope="request" type="java.util.Set<java.lang.String>"/>
            <label class="actor-label mr-2" for="movie-actor">Movie actor [${fn:length(actors)}]</label>
            <select id="movie-actor"
                    class="form-control movie-actor selectpicker"
                    name="selected-actor"
                    data-live-search="true">
                <option selected>${notSelected}</option>
                <c:forEach items="${actors}" var="actor">
                    <jsp:useBean id="actor" type="java.lang.String"/>
                    <option>${actor}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-outline-secondary float-right col-2">Search</button>
    </form>

</body>
<script type="text/javascript" src="js/search.js" defer></script>
</html>
