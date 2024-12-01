package use_case.movie_detail;

import entity.Movie;

/**
 * The input data for the Movie Detail Selection Use Case.
 */
public class MovieDetailInputData {

    private final Movie movie;
    private final String originView;

    public MovieDetailInputData(Movie movie, String originView) {
        this.movie = movie;
        this.originView = originView;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getOriginView() {
        return this.originView;
    }
}
