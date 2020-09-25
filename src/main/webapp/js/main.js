let activateMenu = (loc) => {
    $(".active").removeClass("active");
    $("#" + loc).addClass("active");
};

let overrideCardSize = () => {
    let cardHeight = $("#cardHeight").val();
    let cardWidth = $("#cardWidth").val();
    let root = document.documentElement;
    root.style.setProperty('--movie-card-width', cardWidth);
    root.style.setProperty('--movie-card-height', cardHeight);

    let multiplier = cardWidth.slice(0, -2) / 185;
    root.style.setProperty('--font-sm', multiplier * 0.6 + 'em');
    root.style.setProperty('--font-md', multiplier * 0.8 + 'em');
    root.style.setProperty('--font-lg', multiplier * 1.3 + 'em');
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
