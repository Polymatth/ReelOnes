package use_case.return_to_list_from_movie_detail;

/**
 * The return to list from movie detail interactor
 */
public class ReturnToListFromMovieInteractor implements ReturnToListFromMovieInputBoundary {

    private ReturnToListFromMovieOutputBoundary returnToListFromMoviePresenter;

    public ReturnToListFromMovieInteractor(ReturnToListFromMovieOutputBoundary returnToListFromMoviePresenter) {
        this.returnToListFromMoviePresenter = returnToListFromMoviePresenter;
    }

    public void execute(ReturnToListFromMovieInputData returnToListFromMovieInputData) {
        final ReturnToListFromMovieOutputData returnToListFromMovieOutputData = new
                ReturnToListFromMovieOutputData(returnToListFromMovieInputData.getOriginView());
        returnToListFromMoviePresenter.executeOriginView(returnToListFromMovieOutputData);
    }
}
