let loc = "upload";
activateMenu(loc);

$(document).ready(() => {
        $(".list-group-item-success").on('click', (event) => {
            let movieIds = event.currentTarget.textContent.split(" <---> ")[1].trim();
            window.location.href = `gallery?input-title=${movieIds}&selected-genre=Choose+any+value&selected-actor=Choose+any+value`;
        })
    }
);
