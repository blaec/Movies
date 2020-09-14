let loc = "upload";
activateMenu(loc);

$(document).ready(() => {

    // ======================== listeners ========================
    // display successfully loaded movie
    $(".list-group-item-success").on('click', (event) => {
        let movieIds = $(event.currentTarget).attr("value");
        window.location.href = `gallery?input-title=${movieIds}`;
    });

    // display spinner on upload and disable upload button
    $( "form" ).on('submit', (form) => {
        $(".loader").removeClass("loader-hidden");
        $("#btn-upload").attr("disabled", true);
        return true;
    })
});