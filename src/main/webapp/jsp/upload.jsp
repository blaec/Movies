<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="css/upload.css" type="text/css">

<body>
    <jsp:include page="fragments/menu.jsp"/>

    <header>
        <div class="container">
            <div class="row">
                <div class="col">
                    <p class="stat-font mt-0">
                        Choose any movie location and press Upload button.
                    </p>
                </div>
            </div>
        </div>
    </header>
    <main role="main" class="container">
        <form method="post" action="upload">
            <div class="row">

                <%-- movie locations list --%>
                <div class="col-12 col-lg-3 movie-location-list">
                    <jsp:useBean id="locations" scope="request" type="java.util.Set<java.lang.String>"/>
                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach items="${locations}" var="location">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="loc-${count}" name="selected-location" class="custom-control-input"
                                   value="${location}">
                            <label class="custom-control-label" for="loc-${count}">${location}</label>
                        </div>
                    </c:forEach>
                </div>

                <%-- movie locations list --%>
                <div class="col-12 col-lg-9 mt-1">
                    <div class="row">
                        <div class="col">
                            <p class="stat-font">
                                (Optional) Manual import by imdb id for single movie from selected location
                            </p>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col">
                            <div class="input-group">
                            <div class="input-group-prepend d-none d-md-block">
                                <span class="alert-primary input-group-text">Imdb Id</span>
                            </div>
                            <input type="text"
                                   name="imdb-id"
                                   class="form-control"
                                   aria-label="Default"
                                   aria-describedby="imdb-id"
                                   placeholder="Type imdb id, like tt0378194">
                            </div>
                        </div>
                    </div>
                    <div class="row mt-1">
                        <div class="col">
                            <div class="input-group">
                                <div class="input-group-prepend d-none d-md-block">
                                    <span class="alert-primary input-group-text">Manual Title</span>
                                </div>
                                <input type="text"
                                       name="manual-import-title"
                                       class="form-control"
                                       aria-label="Default"
                                       aria-describedby="manual-title"
                                       placeholder="Type in exact file movie title">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <button type="submit" class="btn btn-outline-primary float-right col-4 col-md-2">Upload</button>
                </div>
            </div>
        </form>

        <div class="col col-12 mt-3">
            <jsp:useBean id="uploadMessage" scope="request" type="java.lang.String"/>
            <p class="stat-font">${uploadMessage}</p>
            <ul class="list-group">
                <jsp:useBean id="success" scope="request"
                             type="java.util.List<org.blaec.movies.objects.SuccessMovieFileObject>"/>
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
    </main>
    <footer class="footer">
        <div class="container">
<%--            <span class="text-muted">Place sticky footer content here.</span>--%>
        </div>
    </footer>
</body>
<script type="text/javascript" src="js/upload.js" async></script>
</html>