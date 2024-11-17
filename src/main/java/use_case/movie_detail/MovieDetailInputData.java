package use_case.movie_detail;

import entity.Movie;

/**
 * The input data for the Movie Detail Selection Use Case.
 */
public class MovieDetailInputData {

    private final Movie movie;

    public MovieDetailInputData(Movie movie) {
        this.movie = movie;
    }

    Movie getMovie() {
        return this.movie;
    }

}
