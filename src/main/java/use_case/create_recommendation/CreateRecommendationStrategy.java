package use_case.create_recommendation;

import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.List;
/**
 * The Strategy interface for the Create Recommendation Use Case.
 */
public interface CreateRecommendationStrategy {

    void setAPIAccess(SearchMovieDataAccessInterface searchMovieDataAccessInterface);

    String getStrategy();

    Boolean movieUnknown(List<MovieList> movieLists, Movie movie, String favMovie);

    RecommendedList generateList(String userId, String favMovie, String favDirector, List<MovieList> movieLists, Integer size);


}
