package use_case.create_recommendation;

import data_access.MovieAPIAccess;
import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import entity.User;

import java.util.List;

public class CreateRecommendationGenreStrategy implements CreateRecommendationStrategy {
    MovieAPIAccess movieAPIAccess;
    @Override
    public void setAPIAccess(MovieAPIAccess movieAPIAccess) {
        this.movieAPIAccess = movieAPIAccess;
    }

    @Override
    public String getStrategy() {
        return "Genre Strategy";
    }

    @Override
    public Boolean movieUnknown(List<MovieList> movieLists, Movie movie) {
        for (MovieList movieList: movieLists) {
            if (movieList.containsMovie(movie)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public RecommendedList generateList(String userId, String favMovie, String favDirector, List<MovieList> movieLists, Integer size) {
        RecommendedList result = new RecommendedList(userId);
        List<Movie> queryResults = movieAPIAccess.searchMoviesByGenre(
                movieAPIAccess.searchMoviesByQuery(favMovie).get(0).getGenre_ids());
        int i = 0;
        while (result.size() <= queryResults.size() && result.size() <= size) {
            Movie movie = queryResults.get(i);
            if (!result.containsMovie(movie) && movieUnknown(movieLists, movie)) {
                result.addMovie(movie);
            }
            i += 1;
        }

        return result;
    }
}
