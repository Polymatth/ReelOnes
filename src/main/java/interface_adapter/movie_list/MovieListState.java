package interface_adapter.movie_list;

import entity.Movie;

import java.util.List;

public class MovieListState {
    private String name = "";
    private List<Movie> movies;
    private String movieListError;

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Movie> getMovies(){
        return this.movies;
    }

    public String getMovieListError() {
        return movieListError;
    }

    public void setMovieListError(String movieListError) {
        this.movieListError = movieListError;
    }

}
