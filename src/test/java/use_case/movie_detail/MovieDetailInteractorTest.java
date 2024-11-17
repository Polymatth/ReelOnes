package use_case.movie_detail;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieDetailInteractorTest {

    @Test
    void MovieDetailSuccess() {
        AppConfig config = new AppConfig();
        MovieDetailDataAccessInterface dataAccessInterface = config.getMovieDetailDataAccess();
        //Create the Movie whose details are being requested.
        String path = "/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg";
        String overview = "Jack Reacher must uncover the truth behind a major government conspiracy in order to clear " +
                "his name. On the run as a fugitive from the law, Reacher uncovers a potential secret from his " +
                "past that could change his life forever.";
        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(53);
        genreIds.add(28);
        genreIds.add(80);
        genreIds.add(18);
        genreIds.add(9648);
        String backDropPath = "/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg";
        int id = 343611;
        Movie testMovie = new Movie(path, false, overview, "2016-10-19", genreIds, id,
                "en", "Jack Reacher: Never Go Back", backDropPath, (float)26.818468,
                201, false, (float)4.19);
        MovieDetailInputData movieDetailInputData = new MovieDetailInputData(testMovie);

        //Create a test presenter that tests that the interactor works as we expect.
        MovieDetailOutputBoundary movieDetailPresenter = new MovieDetailOutputBoundary() {
            @Override
            public void prepareSuccessView(MovieDetailOutputData movieDetailOutputData) {
                //Test that you get the right movie by checking IDs.
                assertEquals(343611, movieDetailOutputData.getID());
                //Test that you get the correct director.
                assertEquals("Edward Zwick", movieDetailOutputData.getDirector());
                //Test that you get the correct poster path.
                assertEquals("/IfB9hy4JH1eH6HEfIgIGORXi5h.jpg", movieDetailOutputData.getPosterImagePath());
                //Test that you get the correct list of genres names.
                List<String> genreNames = new ArrayList<>();
                genreNames.add("Thriller");
                genreNames.add("Action");
                genreNames.add("Crime");
                genreNames.add("Drama");
                genreNames.add("Mystery");
                assertEquals(String.join(", ", genreNames), movieDetailOutputData.getGenre());
            }
        };
        MovieDetailInputBoundary interactor = new MovieDetailInteractor(movieDetailPresenter, dataAccessInterface);
        interactor.execute(movieDetailInputData);
    }
}
