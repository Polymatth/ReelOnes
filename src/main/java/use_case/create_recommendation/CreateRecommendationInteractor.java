package use_case.create_recommendation;

import data_access.DBMovieListDataAccessObject;
import data_access.MovieAPIAccess;
import entity.MovieList;
import entity.RecommendedList;
import entity.UserFactory;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateRecommendationInteractor implements CreateRecommendationInputBoundary {

    private static final int MAX_RECOMMENDATION_SIZE = 20;

    private DBMovieListDataAccessObject dbMovieListDataAccessObject;
    private SearchMovieDataAccessInterface searchMovieDataAccessInterface;
    private CreateRecommendationStrategy strategy;

    private final UserFactory userFactory;

    public CreateRecommendationInteractor(DBMovieListDataAccessObject dbMovieListDataAccessObject,
                                          SearchMovieDataAccessInterface searchMovieDataAccessInterface, UserFactory userFactory) {
        this.dbMovieListDataAccessObject = dbMovieListDataAccessObject;
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
        this.userFactory = userFactory;
    }

    public void setStrategy(CreateRecommendationStrategy recommendationStrategy){
        this.strategy = recommendationStrategy;
    }
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
