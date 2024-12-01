package use_case.fetch_nowplayingmovies;

import entity.Movie;

import java.util.List;

/**
 * Output data for Fetch Now Playing Movies Use Case.
 */
public class FetchNowPlayingMoviesOutputData {
     private final List<Movie> movies;


     public FetchNowPlayingMoviesOutputData(List<Movie> movies) {this.movies = movies;

     }

     public List<Movie> getMovies() {
          return movies;
     }



}
