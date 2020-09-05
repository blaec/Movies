let loc = "upload";
activateMenu(loc);

$(document).ready(() => {
        $(".list-group-item-success").on('click', (event) => {
            let movieIds = $(event.currentTarget).attr("value");
            window.location.href = `gallery?input-title=${movieIds}&selected-genre=Choose+any+value&selected-actor=Choose+any+value`;
        })
    }
);
