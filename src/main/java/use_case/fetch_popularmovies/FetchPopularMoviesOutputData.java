package use_case.fetch_popularmovies;

import entity.Movie;

import java.util.List;

public class FetchPopularMoviesOutputData {

    private final List<Movie> movies;


    public FetchPopularMoviesOutputData(List<Movie> movies) {this.movies = movies;}

    public List<Movie> getMovies() {return movies;}
}
