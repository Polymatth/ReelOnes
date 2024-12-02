package use_case.create_recommendation;

import data_access.InMemoryMovieListDataAccessObject;
import data_access.MovieAPIAccess;
import entity.RecommendedList;

public class CreateRecommendationInteractor implements CreateRecommendationInputBoundary {

    private static final int MAX_RECOMMENDATION_SIZE = 20;

    private InMemoryMovieListDataAccessObject inMemoryMovieListDataAccessObject;
    private MovieAPIAccess movieAPIAccess;
    private CreateRecommendationStrategy strategy;

    public CreateRecommendationInteractor(InMemoryMovieListDataAccessObject inMemoryMovieListDataAccessObject,
                                          MovieAPIAccess movieAPIAccess) {
        this.inMemoryMovieListDataAccessObject = inMemoryMovieListDataAccessObject;
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
        inMemoryMovieListDataAccessObject.saveMovieList(result);
    }
}
