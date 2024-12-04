package use_case.create_recommendation;

import app.AppConfig;
import data_access.DBMovieListDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.*;
import org.junit.Test;
import use_case.fetch_popularmovies.FetchPopularMoviesDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Test Coverage for the CreateRecommendation Use Case
 * - Matthew
 */


public class CreateRecommendationTest {

    @Test
    public void testCreateRecommendation() {
        DBMovieListDataAccessObject dbMovieListDataAccessObject = new DBMovieListDataAccessObject();
        SearchMovieDataAccessInterface searchMovieDataAccessInterface = new AppConfig().getMovieDataAccess();
        UserFactory userFactory = new CommonUserFactory();
        FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface = new AppConfig().getPopularMoviesDataAccess();
        User testUser = userFactory.create("test-user", "test-pass", "Cloverfield", "Christopher Nolan", new ArrayList<>());
        CreateRecommendationInteractor createRecommendationInteractor = new CreateRecommendationInteractor(dbMovieListDataAccessObject, searchMovieDataAccessInterface, userFactory);
        CreateRecommendationCosimStrategy strategy = new CreateRecommendationCosimStrategy();

        //Test Cosine Similarity is set as strategy
        assert "Cosine Similarity".equals(strategy.getStrategy());
        strategy.setFetchPopularMoviesDataAccessInterface(fetchPopularMoviesDataAccessInterface);
        createRecommendationInteractor.setStrategy(strategy);
        strategy.setAPIAccess(searchMovieDataAccessInterface);

        //Test Threshold is correctly set
        strategy.setThreshold(0.1);
        assert strategy.getThreshold() == 0.1;
        testUser.getMovieLists().add(strategy.generateList(testUser.getName(), testUser.getFavMovie(), testUser.getFavDirector(), testUser.getMovieLists(), 20));

        //Test that cosinPrep Creates a list of elements of movie data and the first item is the title of the film
        List<String> testElements = strategy.cosinPrep(searchMovieDataAccessInterface.searchMoviesByQuery("Cloverfield").get(0));
        assert !testElements.isEmpty();
        assert "Cloverfield".equals(testElements.get(0));

        /*Test the similarity score returns a similarity score that is suitably above a certain threshold to determine
        similarity.
         */
        double testScore = strategy.similarity_score(
                searchMovieDataAccessInterface.searchMoviesByQuery("Deadpool").get(0),
                searchMovieDataAccessInterface.searchMoviesByQuery("Deadpool 2").get(0));
        assert testScore > 0.1;
        System.out.println(testScore);

        //Test the new list has been generated and contains films
        assert !testUser.getMovieLists().isEmpty();
        assert "Recommended Movies".equals(testUser.getMovieLists().get(0).getListName());
        assert 0 != testUser.getMovieLists().get(0).size();

        //Test movieUnknown Method returns false
        assert !strategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Cloverfield").get(0),
                testUser.getFavMovie());

        //Test movieUnknown Method returns true
        assert strategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Primer").get(0),
                testUser.getFavMovie());

        //Test CreateRecommendationOutputData
        CreateRecommendationOutputData createRecommendationOutputData = new CreateRecommendationOutputData((RecommendedList) testUser.getMovieLists().get(0));
        assert createRecommendationOutputData.getName().equals("Recommended Movies");
        assert !createRecommendationOutputData.getMovies().isEmpty();
        assert !createRecommendationOutputData.getRecommendedList().isEditable();

        //Test CreateRecommendationInputData
        CreateRecommendationInputData createRecommendationInputData =
                new CreateRecommendationInputData(
                        "test-user", "test-pass", "Cloverfield", "Christopher Nolan",
                        new ArrayList<>());
        assert createRecommendationInputData.getUserId().equals("test-user");
        assert createRecommendationInputData.getPassword().equals("test-pass");
        assert createRecommendationInputData.getFavMovie().equals("Cloverfield");
        assert createRecommendationInputData.getFavDirector().equals("Christopher Nolan");
        assert createRecommendationInputData.getMovieLists().isEmpty();

        //Test CreateRecommendationGenreStrategy
        CreateRecommendationGenreStrategy genreStrategy = new CreateRecommendationGenreStrategy();
        genreStrategy.setAPIAccess(searchMovieDataAccessInterface);
        assert genreStrategy.getStrategy().equals("Genre Strategy");
        assert genreStrategy.generateList(testUser.getName(), testUser.getFavMovie(),
                testUser.getFavDirector(), testUser.getMovieLists(), 20).size() > 0;


        //Test movieUnknown Method returns false
        assert !genreStrategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Cloverfield").get(0),
                testUser.getFavMovie());

        //Test movieUnknown Method returns true
        assert genreStrategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Primer").get(0),
                testUser.getFavMovie());

        //Test CreateRecommendationDirectorStrategy
        CreateRecommendationDirectorStrategy directorStrategy = new CreateRecommendationDirectorStrategy();
        directorStrategy.setAPIAccess(searchMovieDataAccessInterface);
        assert directorStrategy.getStrategy().equals("Director Strategy");
        assert directorStrategy.generateList(testUser.getName(), testUser.getFavMovie(),
                testUser.getFavDirector(), testUser.getMovieLists(), 20).size() > 0;

        //Test movieUnknown Method returns false
        assert !directorStrategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Cloverfield").get(0),
                testUser.getFavMovie());

        //Test movieUnknown Method returns true
        assert directorStrategy.movieUnknown(testUser.getMovieLists(),
                searchMovieDataAccessInterface.searchMoviesByQuery("Primer").get(0),
                testUser.getFavMovie());

        //Test Interactor Execution
        createRecommendationInteractor.setStrategy(directorStrategy);
        DBUserDataAccessObject dbUserDataAccessObject = new DBUserDataAccessObject(userFactory, dbMovieListDataAccessObject);
        User realUser = dbUserDataAccessObject.get("gtest");
        createRecommendationInteractor.execute(new CreateRecommendationInputData(realUser.getName(), realUser.getPassword(), realUser.getFavMovie(), realUser.getFavDirector(), realUser.getMovieLists()
        ));

    }
}
