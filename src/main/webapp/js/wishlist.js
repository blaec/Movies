let loc = "wishlist";
activateMenu(loc);

$(document).ready(() => {
    let movieDelete = $(".movie-delete");
    let movieSync = $(".movie-sync");

    movieDelete.on('click', (event) => {
        alert("delete not implemented yet.")
    });
    movieSync.on('click', (event) => {
        alert("sync not implemented yet.")
    });

    // show hidden elements after initializing events
    movieDelete.show();
    movieSync.show();
});