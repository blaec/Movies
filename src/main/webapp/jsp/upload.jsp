<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/upload.css" type="text/css">

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>

    <form method="post" action="upload">
        <div class="form-group search">
            <jsp:useBean id="locations" scope="request" type="java.util.Set<java.lang.String>"/>
            <div class="movie-location input-group mb-3">
                <div class="input-group-prepend">
                    <label class="alert-primary input-group-text location-label" for="movie-location">
                        Upload locations
                    </label>
                </div>
                <select id="movie-location"
                        class="form-control selectpicker"
                        name="selected-location">
                    <c:forEach items="${locations}" var="location">
                        <jsp:useBean id="location" type="java.lang.String"/>
                        <option>${location}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-outline-primary float-right col-2">Upload</button>
    </form>
</body>
<script type="text/javascript" src="js/upload.js" defer></script>
</html>