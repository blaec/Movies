<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%--<header class="menu my-3">--%>
<%--    <h3 class="caption mb-0">Cover</h3>--%>
<%--    <nav class="nav nav-menu-items">--%>
<%--        <a id="home" class="nav-link" href="index.jsp">Home</a>--%>
<%--        <a id="search" class="nav-link" href="search">Search</a>--%>
<%--        <a id="gallery" class="nav-link" href="gallery">Gallery</a>--%>
<%--        <a id="upload" class="nav-link" href="upload">Upload</a>--%>
<%--    </nav>--%>
<%--</header>--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="search">Search</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="gallery">Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="upload">Upload</a>
            </li>
<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                    Dropdown--%>
<%--                </a>--%>
<%--                <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                    <a class="dropdown-item" href="#">Action</a>--%>
<%--                    <a class="dropdown-item" href="#">Another action</a>--%>
<%--                    <div class="dropdown-divider"></div>--%>
<%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link disabled" href="#">Disabled</a>--%>
<%--            </li>--%>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>