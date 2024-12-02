package use_case.movie_list;

import entity.Movie;
import entity.MovieList;

import java.util.List;

public class MovieListOutputData {
    private final MovieList movieList;

    public MovieListOutputData(MovieList movieList) {
        this.movieList = movieList;
    }

    public String getName() {
        return this.movieList.getListName();
    }


    public List<Movie> getMovies(){
        return this.movieList.getMovies();
    }

    public MovieList getMovieList(){
        return this.movieList;
    }

}
