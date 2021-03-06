<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frag" tagdir="/WEB-INF/tags" %>

<html>

    <frag:headTag/>
    <link rel="stylesheet" href="css/upload.css" type="text/css">

    <body>
        <frag:menu/>

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
                        <frag:formCheck isChecked="${cardSize == 'sm_card'}"
                                        id="smCard"
                                        value="sm_card"
                                        caption="small"/>
                        <frag:formCheck isChecked="${cardSize == 'md_card'}"
                                        id="mdCard"
                                        value="md_card"
                                        caption="medium"/>
                        <frag:formCheck isChecked="${cardSize == 'lg_card'}"
                                        id="lgCard"
                                        value="lg_card"
                                        caption="large"/>
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
                        <c:forEach var="location" items="${locations}" varStatus="locationLoopCounter">
                            <div class="form-check">
                                <input type="radio"
                                       id="loc-${locationLoopCounter.count}"
                                       name="selected-location"
                                       class="form-check-input"
                                       value="${location}">
                                <label class="form-check-label" for="loc-${locationLoopCounter.count}">${location}</label>
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
                        <c:forEach var="successItem" items="${success}">
                            <li class="list-group-item list-group-item-success text-break"
                                value="${successItem.movieJsonObjectTitle}">

                                <i class="fa fa-check-circle mr-1"></i>
                                ${successItem.movieFileObject}
                            </li>
                        </c:forEach>

                        <%-- failure results --%>
                        <jsp:useBean id="fail" scope="request" type="java.util.List<java.lang.String>"/>
                        <c:forEach var="failItem" items="${fail}">
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