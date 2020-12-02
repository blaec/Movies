<%-- delete movie modal --%>
<div class="modal fade" id="removeMovie" tabindex="-1" aria-labelledby="removeMovieLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header modal-danger">
                <h5 class="modal-title heading text-center" id="removeMovieLabel">Delete movie</h5>
            </div>
            <div class="modal-body">
                <input type="hidden" name="movie-id" id="movie-id"/>
                <input type="hidden" name="movie-imdb-id" id="movie-imdb-id"/>
                <input type="hidden" name="movie-location" id="movie-location"/>
                <div class="row">
                    <i class="col col-3 fas fa-times fa-4x text-danger rotate"></i>
                    <p class="col col-9 mt-2 mb-0">Do you really want to delete this movie?</p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn  btn-outline-danger" data-dismiss="modal">No</button>
                <button type="button" class="btn  btn-danger allow-delete">Yes</button>
            </div>
        </div>
    </div>
</div>
