<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <form method="get" action="gallery">
        <div class="form-group">
            <label for="movie-genre" style="color: white">Movie genre</label>
            <select id="movie-genre" class="form-control" name="selected-genre">
                <option selected>${notSelected}</option>
                <jsp:useBean id="genres" scope="request" type="java.util.Set<java.lang.String>"/>
                <c:forEach items="${genres}" var="genre">
                    <jsp:useBean id="genre" type="java.lang.String"/>
                    <option>${genre}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>

</body>
<script type="text/javascript" src="js/search.js" defer></script>
</html>
