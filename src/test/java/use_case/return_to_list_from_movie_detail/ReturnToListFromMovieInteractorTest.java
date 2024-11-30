package use_case.return_to_list_from_movie_detail;

import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

public class ReturnToListFromMovieInteractorTest {

    @Test
    void ReturnToListFromMovieSuccess() {
        String originView = "movie search";
        ReturnToListFromMovieInputData returnToListFromMovieInputData = new ReturnToListFromMovieInputData(originView);

        //Create a test presenter that tests that the interactor works as we expect.
        ReturnToListFromMovieOutputBoundary returnToListFromMoviePresenter = new ReturnToListFromMovieOutputBoundary() {
            @Override
            public void executeOriginView(ReturnToListFromMovieOutputData returnToListFromMovieOutputData) {
                //Test that you get the right view name.
                assertEquals(originView, returnToListFromMovieOutputData.getOriginView());
            }
        };
        ReturnToListFromMovieInputBoundary interactor = new ReturnToListFromMovieInteractor(returnToListFromMoviePresenter);
        interactor.execute(returnToListFromMovieInputData);
    }
}
