package use_case.create_recommendation;

import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.List;
/**
 * Genre Strategy for CreateRecommendation Strategy
 * - Matthew
 */
public class CreateRecommendationGenreStrategy implements CreateRecommendationStrategy {
    SearchMovieDataAccessInterface searchMovieDataAccessInterface;
    @Override
    public void setAPIAccess(SearchMovieDataAccessInterface searchMovieDataAccessInterface) {
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
    }
    /**
     * Returns strategy name
     * @return the name of the strategy currently in use
     */
    @Override
    public String getStrategy() {
        return "Genre Strategy";
    }
    /**
     * Determines if a movie is unknown to a given user
     * @param movieLists The user's movie lists
     * @param movie The movie we are searching for
     * @param favMovie Title of user's favMovie
     * @return Boolean representing if the user is aware of a given film
     */
    @Override
    public Boolean movieUnknown(List<MovieList> movieLists, Movie movie, String favMovie) {
        for (MovieList movieList: movieLists) {
            if (movieList.containsMovie(movie) || movie.getTitle().equals(favMovie)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Generates a recommended movie list
     * @param userId username of the user
     * @param favMovie favMovie of the user
     * @param favDirector favDirector of the user
     * @param movieLists user's movie lists
     * @param size maximum size of the desired recommendations list
     * @return a recommended list of movies based on the users preferences
     */
    @Override
    public RecommendedList generateList(String userId, String favMovie, String favDirector, List<MovieList> movieLists, Integer size) {
        RecommendedList result = new RecommendedList(userId);
        List<Movie> queryResults = searchMovieDataAccessInterface.searchMoviesByGenre(
                searchMovieDataAccessInterface.searchMoviesByQuery(favMovie).get(0).getGenre_ids());
        int i = 0;
        while (result.size() <= queryResults.size() && result.size() < size) {
            Movie movie = queryResults.get(i);
            if (!result.containsMovie(movie) && movieUnknown(movieLists, movie, favMovie)) {
                result.addMovie(movie);
            }
            i += 1;
        }

        return result;
    }
}
