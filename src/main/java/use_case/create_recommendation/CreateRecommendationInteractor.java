package use_case.create_recommendation;

import data_access.DBMovieListDataAccessObject;
import entity.MovieList;
import entity.RecommendedList;
import entity.UserFactory;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
/**
 * The interactor for the CreateRecommendation use case.
 * - Matthew
 */
public class CreateRecommendationInteractor implements CreateRecommendationInputBoundary {

    private static final int MAX_RECOMMENDATION_SIZE = 20;

    private final DBMovieListDataAccessObject dbMovieListDataAccessObject;
    private final SearchMovieDataAccessInterface searchMovieDataAccessInterface;
    private CreateRecommendationStrategy strategy;

    private final UserFactory userFactory;

    /**
     * Constructor for the CreateRecommendationInteractor
     * @param dbMovieListDataAccessObject used to access and save generated movie lists
     * @param searchMovieDataAccessInterface used to search for movie info
     * @param userFactory used to create and save users
     */
    public CreateRecommendationInteractor(DBMovieListDataAccessObject dbMovieListDataAccessObject,
                                          SearchMovieDataAccessInterface searchMovieDataAccessInterface, UserFactory userFactory) {
        this.dbMovieListDataAccessObject = dbMovieListDataAccessObject;
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
        this.userFactory = userFactory;
    }

    /**
     * Set the desired strategy for the CreateRecommendation Use Case
     * @param recommendationStrategy recommendation strategy used when generating a new list
     */
    public void setStrategy(CreateRecommendationStrategy recommendationStrategy){
        this.strategy = recommendationStrategy;
    }

    /**
     * Executes the CreateRecommendation Use Case, collecting existing lists from the user,
     * generating a new list based on their tastes,
     * and then saving all lists back to the user database
     * @param createRecommendationInputData the input data for this use case
     */
    @Override
    public void execute(CreateRecommendationInputData createRecommendationInputData) {
        strategy.setAPIAccess(searchMovieDataAccessInterface);
        List<MovieList> movieLists = createRecommendationInputData.getMovieLists();
        List<MovieList> result = new ArrayList<>();
        for (MovieList movielist: movieLists) {
            if (!movielist.getListName().equals("Recommended Movies")) {
                result.add(movielist);
            }
        }
        RecommendedList newList = strategy.generateList(createRecommendationInputData.getUserId(),
                createRecommendationInputData.getFavMovie(),
                createRecommendationInputData.getFavDirector(),
                createRecommendationInputData.getMovieLists(),
                MAX_RECOMMENDATION_SIZE);
        result.add(newList);
        System.out.println(newList.size());
       dbMovieListDataAccessObject.saveMovieList(userFactory.create(createRecommendationInputData.getUserId(),
               createRecommendationInputData.getPassword(), createRecommendationInputData.getFavMovie(),
               createRecommendationInputData.getFavDirector(), result));
    }
}
