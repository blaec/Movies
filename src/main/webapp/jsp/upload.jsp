<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="css/upload.css" type="text/css">

    <body>
        <jsp:include page="fragments/menu.jsp"/>

        <header>
<%--            <div class="container">--%>
<%--                <div class="row">--%>
<%--                    <div class="col">--%>
<%--                        <p class="stat-font">--%>
<%--                            Choose any movie location and press Upload button.--%>
<%--                        </p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </header>
        <main role="main" class="container">

            <%-- SITE SETTINGS --%>
            <div class="row mt-3">
                <div class="col">
                    <p class="stat-font">
                        Select movie card size
                    </p>
                </div>
            </div>
            <form method="post" action="upload" class="settings">
                <input type='hidden' name='action' value='settings'/>
                <jsp:useBean id="cardSize" scope="request" type="org.blaec.movies.enums.CardSizeEnum"/>
                <div class="row">
                    <div class="col-12">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="card-size"
                                   <c:if test="${cardSize == 'sm_card'}">checked</c:if>
                                   id="smCard"
                                   value="sm_card">
                            <label class="form-check-label" for="smCard">small</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="card-size"
                                   <c:if test="${cardSize == 'md_card'}">checked</c:if>
                                   id="mdCard"
                                   value="md_card">
                            <label class="form-check-label" for="mdCard">medium</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="card-size"
                                   <c:if test="${cardSize == 'lg_card'}">checked</c:if>
                                   id="lgCard"
                                   value="lg_card">
                            <label class="form-check-label" for="lgCard">large</label>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <button id="btn-settings" type="submit" class="btn btn-outline-success float-right col-4 col-md-2">
                            <span class="spinner-border spinner-border-sm loader loader-hidden"></span>
                            Save
                        </button>
                    </div>
                </div>
            </form>

            <%-- MOVIE UPLOAD --%>
            <div class="row mt-3">
                <div class="col">
                    <p class="stat-font">
                        Choose any movie location and press Upload button
                    </p>
                </div>
            </div>
            <form method="post" action="upload" class="upload">
                <input type='hidden' name='action' value='gallery' />
                <div class="row">

                    <%-- movie locations list --%>
                    <div class="col-12 col-lg-3 movie-location-list">
                        <jsp:useBean id="locations" scope="request" type="java.util.Set<java.lang.String>"/>
                        <c:set var="count" value="0" scope="page"/>
                        <c:forEach items="${locations}" var="location">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <div class="form-check">
                                <input type="radio"
                                       id="loc-${count}"
                                       name="selected-location"
                                       class="form-check-input"
                                       value="${location}">
                                <label class="form-check-label" for="loc-${count}">${location}</label>
                            </div>
                        </c:forEach>
                    </div>

                    <%-- manual import block --%>
                    <div class="col-12 col-lg-9 mt-1">
                        <div class="row">
                            <div class="col">
                                <p class="stat-font">
                                    (Optional) Manual import by imdb id for single movie from selected location
                                </p>
                            </div>
                        </div>

                        <%-- imdb id input --%>
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

                        <%-- movie title input --%>
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
                        <button id="btn-upload" type="submit" class="btn btn-outline-primary float-right col-4 col-md-2">
                            <span class="spinner-border spinner-border-sm loader loader-hidden"></span>
                            Upload
                        </button>
                    </div>
                </div>
            </form>

            <%-- MOVIE WISHLIST --%>
            <div class="row mt-3">
                <div class="col">
                    <p class="stat-font">
                        Add movie to wishlist by imdb-id
                    </p>
                </div>
            </div>
            <form method="post" action="upload" class="wishlist">
                <input type='hidden' name='action' value='wishlist' />
                <div class="col-12 mt-1">
                    <div class="row mt-1">
                        <div class="col">
                            <div class="input-group">
                                <div class="input-group-prepend d-none d-md-block">
                                    <span class="alert-primary input-group-text">Imdb Id</span>
                                </div>
                                <input type="text"
                                       name="wishlist-imdb-id"
                                       class="form-control"
                                       aria-label="Default"
                                       aria-describedby="imdb-id"
                                       placeholder="Type imdb id, like tt0378194">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <button id="btn-wishlist" type="submit" class="btn btn-outline-primary float-right col-4 col-md-2">
                            <span class="spinner-border spinner-border-sm loader loader-hidden"></span>
                            Add to Wishlist
                        </button>
                    </div>
                </div>
            </form>

            <%-- upload results --%>
            <div class="row">
                <div class="col mt-3">
                    <jsp:useBean id="uploadMessage" scope="request" type="java.lang.String"/>
                    <p class="stat-font">${uploadMessage}</p>
                    <ul class="list-group">

                        <%-- success results --%>
                        <jsp:useBean id="success"
                                     scope="request"
                                     type="java.util.List<org.blaec.movies.objects.SuccessMovieFileObject>"/>
                        <c:forEach items="${success}" var="successItem">
                            <li class="list-group-item list-group-item-success text-break"
                                value="${successItem.movieJsonObjectTitle}">

                                <i class="fa fa-check-circle mr-1"></i>
                                ${successItem.movieFileObject}
                            </li>
                        </c:forEach>

                        <%-- failure results --%>
                        <jsp:useBean id="fail" scope="request" type="java.util.List<java.lang.String>"/>
                        <c:forEach items="${fail}" var="failItem">
                            <li class="list-group-item list-group-item-danger text-break">
                                <i class="fa fa-times-circle mr-1"></i>
                                ${failItem}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
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