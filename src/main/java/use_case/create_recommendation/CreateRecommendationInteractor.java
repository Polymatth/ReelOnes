package use_case.create_recommendation;

import data_access.DBMovieListDataAccessObject;
import data_access.InMemoryMovieListDataAccessObject;
import data_access.MovieAPIAccess;
import entity.RecommendedList;

public class CreateRecommendationInteractor implements CreateRecommendationInputBoundary {

    private static final int MAX_RECOMMENDATION_SIZE = 20;

    private DBMovieListDataAccessObject dbMovieListDataAccessObject;
    private MovieAPIAccess movieAPIAccess;
    private CreateRecommendationStrategy strategy;

    public CreateRecommendationInteractor(DBMovieListDataAccessObject dbMovieListDataAccessObject,
                                          MovieAPIAccess movieAPIAccess) {
        this.dbMovieListDataAccessObject = dbMovieListDataAccessObject;
        this.movieAPIAccess = movieAPIAccess;
    }

    public void setStrategy(CreateRecommendationStrategy recommendationStrategy){
        this.strategy = recommendationStrategy;
    }
    @Override
    public void execute(CreateRecommendationInputData createRecommendationInputData) {
        strategy.setAPIAccess(movieAPIAccess);
        RecommendedList result = strategy.generateList(createRecommendationInputData.getUserId(),
                createRecommendationInputData.getFavMovie(),
                createRecommendationInputData.getFavDirector(),
                createRecommendationInputData.getMovieLists(),
                MAX_RECOMMENDATION_SIZE);
       // dbMovieListDataAccessObject.saveMovieList(createRecommendationInputData.getUserId(), result);
    }
}
