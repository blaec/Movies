let activateMenu = (loc) => {
    $(".active").removeClass("active");
    $("#" + loc).addClass("active");
}