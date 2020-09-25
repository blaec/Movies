let activateMenu = (loc) => {
    $(".active").removeClass("active");
    $("#" + loc).addClass("active");
};

let overrideCardSize = () => {
    let cardSize = $("#cardSize").val().split(",");
    let root = document.documentElement;
    root.style.setProperty('--movie-card-width', cardSize[0]);
    root.style.setProperty('--movie-card-height', cardSize[1]);
    root.style.setProperty('--font-sm', cardSize[2]);
    root.style.setProperty('--font-md', cardSize[3]);
    root.style.setProperty('--font-lg', cardSize[4]);
};

let btn = $('#back-to-top');

$(window).scroll(function() {
    if ($(window).scrollTop() > 300) {
        btn.addClass('show');
    } else {
        btn.removeClass('show');
    }
});

btn.on('click', function(e) {
    e.preventDefault();
    $('html, body').animate({scrollTop:0}, '300');
});
