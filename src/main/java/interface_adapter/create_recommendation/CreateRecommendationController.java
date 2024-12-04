package interface_adapter.create_recommendation;

import entity.MovieList;
import use_case.create_recommendation.CreateRecommendationInputBoundary;
import use_case.create_recommendation.CreateRecommendationInputData;

import java.util.List;

/**
 * Controller for the CreateRecommendation Use Case.
 */
public class CreateRecommendationController {

    private final CreateRecommendationInputBoundary createRecommendationInteractor;

    public CreateRecommendationController (CreateRecommendationInputBoundary createRecommendationInteractor) {
        this.createRecommendationInteractor = createRecommendationInteractor;
    }
    public void execute(String userId, String password, String favMovie, String favDirector, List<MovieList> movieLists) {
        final CreateRecommendationInputData createRecommendationInputData =
                new CreateRecommendationInputData(userId, password, favMovie, favDirector, movieLists);
        createRecommendationInteractor.execute(createRecommendationInputData);
    }
}

