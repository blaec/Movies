let loc = "gallery";
activateMenu(loc);

$(document).ready(() => {
    let movieDelete = $(".movie-delete");
    let movieSync = $(".movie-sync");

    // ======================== listeners ========================
    // delete movie
    movieDelete.on('click', (event) => {
        let movieIds = $(event.currentTarget).parents(".flip-container").children("input");
        let id = movieIds.attr("value");
        let imdbId = movieIds.attr("id");
        let title = $(event.currentTarget).parents(".movie-details").children(".movie-title").text();
        if (confirm(`Do you really want to delete movie '${title}'?`)) {
            $.ajax({
                url: `gallery?action=delete&id=${id}&imdbId=${imdbId}`,
                type: "GET"
            }).done(function () {
                location.reload();
            });
        }
    });
    movieSync.on('click', (event) => {
        alert("sync not implemented yet.")
    });

    // show hidden elements after initializing events
    movieDelete.show();
    movieSync.show();
});

