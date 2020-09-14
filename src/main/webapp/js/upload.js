let loc = "upload";
activateMenu(loc);

$(document).ready(() => {
    $(".list-group-item-success").on('click', (event) => {
        let movieIds = $(event.currentTarget).attr("value");
        window.location.href = `gallery?input-title=${movieIds}`;
    });

    $( "form" ).on('submit', (form) => {
        $(".loader").removeClass("loader-hidden");
        $("#btn-upload").attr("disabled", true);
        return true;
    })
});