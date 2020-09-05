let loc = "gallery";
activateMenu(loc);

$(document).ready(() => {

        // ======================== listeners ========================
        // delete movie
        $(".movie-delete").on('click', (event)=> {
            let movieIds = $(event.currentTarget).parents(".flip-container").children("input");
            let id = movieIds.attr("value");
            let imdbId = movieIds.attr("id");
            if (confirm('Are you sure?')) {
                $.ajax({
                    url: `gallery?action=delete&id=${id}&imdbId=${imdbId}`,
                    type: "GET"
                }).done(function () {
                    location.reload();
                });
            }
        })
    }
);

