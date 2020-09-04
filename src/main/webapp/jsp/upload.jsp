<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/upload.css" type="text/css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
    <div class="row">
        <div class="col col-12">
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
                            <option selected>${notSelected}</option>
                            <c:forEach items="${locations}" var="location">
                                <jsp:useBean id="location" type="java.lang.String"/>
                                <option>${location}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-outline-primary">Upload</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="col col-12 mt-3">
            <ul class="list-group">
                <jsp:useBean id="success" scope="request" type="java.util.List<java.lang.String>"/>
                <c:forEach items="${success}" var="successItem">
                    <li class="list-group-item list-group-item-success">
                        <i class="fa fa-check-circle mr-1"></i>
                        ${successItem}
                    </li>
                </c:forEach>
                <jsp:useBean id="fail" scope="request" type="java.util.List<java.lang.String>"/>
                <c:forEach items="${fail}" var="failItem">
                    <li class="list-group-item list-group-item-danger">
                        <i class="fa fa-times-circle mr-1"></i>
                        ${failItem}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
<script type="text/javascript" src="js/upload.js" defer></script>
</html>