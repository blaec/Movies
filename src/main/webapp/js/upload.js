let loc = "upload";
activateMenu(loc);

$(document).ready(() => {
    $(".list-group-item-success").on('click', (event) => {
        let movieIds = $(event.currentTarget).attr("value");
        window.location.href = `gallery?input-title=${movieIds}`;
    });
});

setLoader = (form) => {
    $(".loader").removeClass("loader-hidden");
    form.btnUpload.disabled = true;
    return true;
}
