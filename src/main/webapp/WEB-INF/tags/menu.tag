<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <%-- Navbar --%>
    <a class="navbar-brand" href="#">
        <span class="navbar-brand-bold">MOVIE</span><span class="navbar-brand-thin">LIBRARY</span>
    </a>

    <%-- menu button for small resolutions --%>
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

        <%-- Menu --%>
        <ul class="navbar-nav mr-auto">
            <li id="home" class="nav-item">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li id="search" class="nav-item">
                <a class="nav-link" href="search">Search</a>
            </li>
            <li id="gallery"  class="nav-item">
                <a class="nav-link" href="gallery">Gallery</a>
            </li>
            <li id="wishlist"  class="nav-item">
                <a class="nav-link" href="wishlist">Wishlist</a>
            </li>
            <li id="upload" class="nav-item">
                <a class="nav-link" href="upload">Upload</a>
            </li>
        </ul>

        <%-- Search --%>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>