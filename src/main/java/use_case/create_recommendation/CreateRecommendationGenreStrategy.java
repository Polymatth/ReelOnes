package use_case.create_recommendation;

import data_access.MovieAPIAccess;
import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import entity.User;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.List;

public class CreateRecommendationGenreStrategy implements CreateRecommendationStrategy {
    SearchMovieDataAccessInterface searchMovieDataAccessInterface;
    @Override
    public void setAPIAccess(SearchMovieDataAccessInterface searchMovieDataAccessInterface) {
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
    }

    @Override
    public String getStrategy() {
        return "Genre Strategy";
    }

    @Override
    public Boolean movieUnknown(List<MovieList> movieLists, Movie movie, String favMovie) {
        for (MovieList movieList: movieLists) {
            if (movieList.containsMovie(movie) || movie.getTitle().equals(favMovie)) {
                return false;
            }
        }
        return true;
    }

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
