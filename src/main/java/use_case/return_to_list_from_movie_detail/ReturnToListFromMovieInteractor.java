package use_case.return_to_list_from_movie_detail;

import use_case.movie_detail.MovieDetailOutputBoundary;

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
