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

public class CreateRecommendationCosimStrategy implements CreateRecommendationStrategy {

    double threshold;

    FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface;
    SearchMovieDataAccessInterface searchMovieDataAccessInterface;

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {return this.threshold;}

    @Override
    public void setAPIAccess(SearchMovieDataAccessInterface searchMovieDataAccessInterface) {
        this.searchMovieDataAccessInterface = searchMovieDataAccessInterface;
    }

    public void setFetchPopularMoviesDataAccessInterface (FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface) {
        this.fetchPopularMoviesDataAccessInterface = fetchPopularMoviesDataAccessInterface;
    }

    @Override
    public String getStrategy() {
        return "Cosine Similarity";
    }

    @Override
    public Boolean movieUnknown(List<MovieList> movieLists, Movie movie, String favMovie) {
        for (MovieList movieList: movieLists) {
            if (movieList.containsMovieByTitle(movie.getTitle()) || movie.getTitle().equals(favMovie)) {
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
        //result.addAll(movie.getStreaming());
        //result.addAll(searchMovieDataAccessInterface.getCast(movie.getID()));
        result.add(searchMovieDataAccessInterface.getDirector(movie.getID()));
        result.add(movie.getYear());
        for (Integer id: movie.getGenre_ids()) {
            result.add(String.valueOf(id));
        }
        return result;
    }

    public double similarity_score(Movie movieA, Movie movieB) {

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
        Movie movieCandidate = searchMovieDataAccessInterface.searchMoviesByQuery(favMovie).get(0);
        List<Movie> queryResults = new ArrayList<>();
        System.out.println(queryResults.size());
        queryResults.addAll(fetchPopularMoviesDataAccessInterface.getPopularMovies());
        System.out.println(queryResults.size());
        queryResults.addAll(searchMovieDataAccessInterface.searchMoviesByGenre(movieCandidate.getGenre_ids()));
        System.out.println(queryResults.size());
        queryResults.addAll(searchMovieDataAccessInterface.searchByDirector(searchMovieDataAccessInterface.getDirector(
                movieCandidate.getID())));
        System.out.println(queryResults.size());
        if (!favDirector.equals(searchMovieDataAccessInterface.getDirector(
                movieCandidate.getID()))) {
            queryResults.addAll(searchMovieDataAccessInterface.searchByDirector(favDirector));
        }

        System.out.println(queryResults.size());
        int i = 0;
        while (result.size() < size && i < queryResults.size()) {
            Movie movie = queryResults.get(i);
            System.out.println(movie.getTitle());
            System.out.println(similarity_score(movie,
                    movieCandidate));
            System.out.println(similarity_score(movie,
                    movieCandidate) >= threshold);
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
