package use_case.create_recommendation;

import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import use_case.fetch_popularmovies.FetchPopularMoviesDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Cosine Similarity Strategy for CreateRecommendation Strategy
 * - Matthew
 */
public class CreateRecommendationCosimStrategy implements CreateRecommendationStrategy {

    double threshold;

    FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface;
    SearchMovieDataAccessInterface searchMovieDataAccessInterface;

    /**
     * Sets threshold for the cosine similarity use case
     * @param threshold a value representing the minimum similarity score to be included
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Returns threshold for the cosine similarity use case
     * @return threshold - a value representing the minimum similarity score to be included
     */
    public double getThreshold() {return this.threshold;}

    /**
     * Sets searchMovieDataAccessInterface
     * @param searchMovieDataAccessInterface Movie API Access needed to make calls to collect movie info
     */
    @Override
    public void setAPIAccess(SearchMovieDataAccessInterface searchMovieDataAccessInterface) {
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
    }
    /**
     * Sets fetchPopularMoviesDataAccessInterface
     * @param fetchPopularMoviesDataAccessInterface Movie API Access needed to make calls to collect popular movies
     */
    public void setFetchPopularMoviesDataAccessInterface (FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface) {
        this.fetchPopularMoviesDataAccessInterface = fetchPopularMoviesDataAccessInterface;
    }

    /**
     * Returns strategy name
     * @return the name of the strategy currently in use
     */
    @Override
    public String getStrategy() {
        return "Cosine Similarity";
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
            if (movieList.containsMovieByTitle(movie.getTitle()) || movie.getTitle().equals(favMovie)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Preps a film for cosine similarity
     * @param movie The movie we are prepping for comparison
     * @return a list of strings that represent each individual piece of data in the movie object that will
     * subsequently get compared in similarity_score
     */
    public List<String> cosinPrep (Movie movie) {
        List<String> result = new ArrayList<>(List.of(movie.getTitle().split("\\W")));
        result.add(movie.getOriginal_language());
        result.addAll(List.of(movie.getOverview().split("\\W")));
        //Following parameters are optional:
        //result.addAll(movie.getStreaming());
        //result.addAll(searchMovieDataAccessInterface.getCast(movie.getID()));
        result.add(searchMovieDataAccessInterface.getDirector(movie.getID()));
        result.add(movie.getYear());
        for (Integer id: movie.getGenre_ids()) {
            result.add(String.valueOf(id));
        }
        return result;
    }
    /**
     * Determines the similarity of two films
     * @param movieA The first movie candidate
     * @param movieB The second movie candidate
     * @return similarity score of the two candidate films based on cosine similarity of list elements
     */
    public double similarity_score(Movie movieA, Movie movieB) {

        float numerator = 0;
        float denominatorA = 0;
        float denominatorB = 0;

        List<String> elementsA = cosinPrep(movieA);
        List<String> elementsB = cosinPrep(movieB);
        List<String> elementsAll = new ArrayList<>();
        elementsAll.addAll(elementsA);
        elementsAll.addAll(elementsB);
        HashSet<String> elementsSet = new HashSet<>(elementsAll);

        for (Object s: elementsSet) {
            int A = Collections.frequency(elementsA, s);
            int B = Collections.frequency(elementsB, s);
            numerator += A*B;
            denominatorA += A^2;
            denominatorB += B^2;
        }
        return numerator/(Math.sqrt(denominatorA)*Math.sqrt(denominatorB));
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
        Movie movieCandidate = searchMovieDataAccessInterface.searchMoviesByQuery(favMovie).get(0);
        List<Movie> queryResults = new ArrayList<>();
        queryResults.addAll(fetchPopularMoviesDataAccessInterface.getPopularMovies());
        queryResults.addAll(searchMovieDataAccessInterface.searchMoviesByGenre(movieCandidate.getGenre_ids()));
        queryResults.addAll(searchMovieDataAccessInterface.searchByDirector(searchMovieDataAccessInterface.getDirector(
                movieCandidate.getID())));
        if (!favDirector.equals(searchMovieDataAccessInterface.getDirector(
                movieCandidate.getID()))) {
            queryResults.addAll(searchMovieDataAccessInterface.searchByDirector(favDirector));
        }

        int i = 0;
        while (result.size() < size && i < queryResults.size()) {
            Movie movie = queryResults.get(i);
            if (!result.containsMovieByTitle(movie.getTitle()) &&
                    movieUnknown(movieLists, movie, favMovie) &&
                    similarity_score(movie,
                    movieCandidate) >= threshold) {
                result.addMovie(movie);
            }
            i += 1;
        }

        return result;
    }
}
