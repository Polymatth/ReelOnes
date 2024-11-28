package use_case.movie_list;

import entity.MovieList;


public class MovieListInputData {
    private final MovieList movieList;

    public MovieListInputData(MovieList movieList) {
        this.movieList = movieList;
    }

    MovieList getMovieList() {
        return this.movieList;
    }

}
