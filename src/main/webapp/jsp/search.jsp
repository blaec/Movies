<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <form method="get" action="gallery">
        <div class="form-group">
            <jsp:useBean id="genres" scope="request" type="java.util.Set<java.lang.String>"/>
            <label for="movie-genre" style="color: white">Movie genre [${fn:length(genres)}]</label>
            <select id="movie-genre" class="form-control" name="selected-genre">
                <option selected>${notSelected}</option>
                <c:forEach items="${genres}" var="genre">
                    <jsp:useBean id="genre" type="java.lang.String"/>
                    <option>${genre}</option>
                </c:forEach>
            </select>

            <jsp:useBean id="actors" scope="request" type="java.util.Set<java.lang.String>"/>
            <label for="movie-actor" style="color: white">Movie actor [${fn:length(actors)}]</label>
            <select id="movie-actor" class="form-control" name="selected-actor">
                <option selected>${notSelected}</option>
                <c:forEach items="${actors}" var="actor">
                    <jsp:useBean id="actor" type="java.lang.String"/>
                    <option>${actor}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>

</body>
<script type="text/javascript" src="js/search.js" defer></script>
</html>
