<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

    <jsp:include page="fragments/headTag.jsp"/>
    <link rel="stylesheet" href="css/search.css" type="text/css">

    <body>
        <jsp:include page="fragments/menu.jsp"/>

        <header>
            <div class="container">
            </div>
        </header>
        <main role="main" class="container">
            <form method="get" action="gallery">
                <div class="form-group search">

                    <%-- Search by title input --%>
                    <div class="row">
                        <div class="col col-12">
                            <div class="movie-name input-group mb-3">
                                <div class="input-group-prepend d-none d-md-block">
                                    <span class="alert-primary input-group-text"
                                          id="inputGroup-sizing-default">Movie title</span>
                                </div>
                                <input type="text"
                                       name="input-title"
                                       class="form-control"
                                       aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default"
                                       placeholder="Type in any part of movie title">
                            </div>
                        </div>
                    </div>
                    <div class="row">

                        <%-- Search by genres select --%>
                        <jsp:useBean id="genres" scope="request" type="java.util.Set<java.lang.String>"/>
                        <div class="col-12 col-lg-5">
                            <div class="movie-genre input-group mb-3">
                                <div class="input-group-prepend d-none d-md-block">
                                    <label class="alert-primary input-group-text genre-label" for="movie-genre">
                                        Movie genre [${fn:length(genres)}]
                                    </label>
                                </div>
                                <select id="movie-genre"
                                        class="form-control selectpicker"
                                        name="selected-genre">
                                    <jsp:useBean id="notSelected" scope="request" type="java.lang.String"/>
                                    <option selected>${notSelected}</option>
                                    <c:forEach items="${genres}" var="genre">
                                        <jsp:useBean id="genre" type="java.lang.String"/>
                                        <option>${genre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%-- Search by actor searchable-select --%>
                        <div class="col-12 col-lg-7">
                            <jsp:useBean id="actors" scope="request" type="java.util.Set<java.lang.String>"/>
                            <div class="movie-actor input-group mb-3">
                                <div class="input-group-prepend d-none d-md-block">
                                    <label class="alert-primary input-group-text actor-label" for="movie-actor">
                                        Movie actor [${fn:length(actors)}]
                                    </label>
                                </div>
                                <select id="movie-actor"
                                        class="form-control selectpicker"
                                        name="selected-actor"
                                        data-live-search="true">
                                    <option selected>${notSelected}</option>
                                    <c:forEach items="${actors}" var="actor">
                                        <jsp:useBean id="actor" type="java.lang.String"/>
                                        <option>${actor}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-primary float-right col-4 col-md-2">Search</button>
            </form>
        </main>
        <footer class="footer">
            <div class="container">
    <%--            <span class="text-muted">Place sticky footer content here.</span>--%>
            </div>
        </footer>
    </body>
    <script type="text/javascript" src="js/search.js" async></script>
</html>
