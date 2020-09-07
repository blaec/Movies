<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            <jsp:useBean id="notSelected" scope="request" type="java.lang.String"/>
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

                    <p class="stat-font">
                        Manual import by imdb id for single movie from selected location (Optional)
                    </p>
                    <div class="row">
                        <div class="col col-5 input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="alert-primary input-group-text" id="imdb id">
                                    Imdb Id
                                </span>
                            </div>
                            <input type="text"
                                   name="imdb-id"
                                   class="form-control"
                                   aria-label="Default"
                                   aria-describedby="imdb id"
                                   placeholder="Type imdb id, like tt0378194">
                        </div>
                        <div class=" col col-7 input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="alert-primary input-group-text" id="manual-title">
                                    Manual Title
                                </span>
                            </div>
                            <input type="text"
                                   name="manual-import-title"
                                   class="form-control"
                                   aria-label="Default"
                                   aria-describedby="manual-title"
                                   placeholder="Type in exact file movie title for manual import">
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="col col-12 mt-3">
            <jsp:useBean id="uploadMessage" scope="request" type="java.lang.String"/>
            <p class="stat-font">${uploadMessage}</p>
            <ul class="list-group">
                <jsp:useBean id="success" scope="request" type="java.util.List<org.blaec.movies.objects.SuccessMovieFileObject>"/>
                <c:forEach items="${success}" var="successItem">
                    <li class="list-group-item list-group-item-success" value="${successItem.movieJsonObjectTitle}">
                        <i class="fa fa-check-circle mr-1"></i>
                        ${successItem.movieFileObject}
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
<script type="text/javascript" src="js/upload.js" async></script>
</html>