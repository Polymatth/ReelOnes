package use_case.movie_list;

import entity.MovieList;

import java.util.List;

/**
 * Output Data for the MovieList Use Case.
 */
public class MovieListOutputData {
    private final List<MovieList> movieListList;

    public MovieListOutputData(List<MovieList> movieListList) {
        this.movieListList = movieListList;
    }

    public List<MovieList> getMovieList(){
        return this.movieListList;
    }

}
