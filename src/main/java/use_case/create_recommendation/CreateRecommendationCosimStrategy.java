package use_case.create_recommendation;

import data_access.MovieAPIAccess;
import entity.Movie;
import entity.MovieList;
import entity.RecommendedList;
import entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CreateRecommendationCosimStrategy implements CreateRecommendationStrategy {

    float threshold;
    MovieAPIAccess movieAPIAccess;
    @Override
    public void setAPIAccess(MovieAPIAccess movieAPIAccess) {
        this.movieAPIAccess = movieAPIAccess;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }
    @Override
    public String getStrategy() {
        return "Cosine Similarity";
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

    public List cosinPrep (Movie movie) {
        List<String> result = new ArrayList<>();
        result.addAll(List.of(movie.getTitle().split("\\W")));
        result.add(movie.getOriginal_language());
        result.addAll(List.of(movie.getOverview().split("\\W")));
        result.addAll(movie.getStreaming());
        result.add(movie.getYear());
        for (Integer id: movie.getGenre_ids()) {
            result.add(String.valueOf(id));
        }
        return result;
    }

    public double similarity_score(Movie movieA, Movie movieB) {

        float result = 0;

        float numerator = 0;
        float denominatorA = 0;
        float denominatorB = 0;

        List<String> elementsA = cosinPrep(movieA);
        List<String> elementsB = cosinPrep(movieB);
        List<String> elementsAll = new ArrayList<>();
        elementsAll.addAll(elementsA);
        elementsAll.addAll(elementsB);
        HashSet elementsSet = new HashSet<String>(elementsAll);

        for (Object s: elementsSet) {
            Integer A = Collections.frequency(elementsA, s);
            Integer B = Collections.frequency(elementsB, s);
            numerator += A*B;
            denominatorA += A^2;
            denominatorB += B^2;
        }
        return numerator/(Math.sqrt(denominatorA)*Math.sqrt(denominatorB));
    }

    @Override
    public RecommendedList generateList(String userId, String favMovie, String favDirector, List<MovieList> movieLists, Integer size) {
        RecommendedList result = new RecommendedList(userId);
        List<Movie> queryResults = movieAPIAccess.getPopularMovies();
        int i = 0;
        while (result.size() <= queryResults.size() && result.size() <= size) {
            Movie movie = queryResults.get(i);
            if (!result.containsMovie(movie) && movieUnknown(movieLists, movie) && similarity_score(movie,
                    movieAPIAccess.searchMoviesByQuery(favMovie).get(0)) >= threshold) {
                result.addMovie(movie);
            }
            i += 1;
        }

        return result;
    }
}
