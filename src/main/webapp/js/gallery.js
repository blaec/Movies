let loc = "gallery";
activateMenu(loc);
overrideCardSize();

$(document).ready(() => {
    let movieDelete = $(".movie-delete");
    let movieSync = $(".movie-sync");

    // ======================== listeners ========================
    // init delete movie modal
    movieDelete.on('click', (event) => {
        $(".modal-body #movie-id").val($(event.currentTarget).data("id"));
        $(".modal-body #movie-imdb-id").val($(event.currentTarget).data("imdb-id"));
        $(".modal-body #movie-location").val($(event.currentTarget).data("location"));
        $("#removeMovieLabel").html(`${$(event.currentTarget).data("title")}`);
    });
    // delete movie
    $('.allow-delete').on('click', () => {
        $("#removeMovie").modal('hide');
        let id = $("#movie-id").val();
        let imdbId = $("#movie-imdb-id").val();
        let loc = $("#movie-location").val();
        $.ajax({
            url: `gallery?action=delete&id=${id}&imdbId=${imdbId}&location=${loc}`,
            type: "GET"
        }).done(function () {
            location.reload();
        });
    });
    movieSync.on('click', (event) => {
        alert("sync not implemented yet.")
    });

    // show hidden elements after initializing events
    movieDelete.show();
    movieSync.show();
});

