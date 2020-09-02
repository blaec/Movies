let loc = "gallery";
activateMenu(loc);

$(document).ready(() => {

        // ======================== listeners ========================
        // delete movie
        $(".bi-trash-fill").on('click', (event)=> {
            let id = $(event.currentTarget).attr('value');
            if (confirm('Are you sure?')) {
                $.ajax({
                    url: "gallery?action=delete&id=" + id,
                    type: "GET"
                }).done(function () {
                    location.reload();
                });
            }
        })
    }
);

