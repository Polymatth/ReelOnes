package use_case.movie_detail;

import entity.Movie;

public class MovieDetailOutputData {

    private Movie movie;

    public MovieDetailOutputData(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie(){
        return this.movie;
    }

    public String getDirector() {
        return this.movie.getDirector();
    }

    public String getYear() {
        return this.movie.getYear();
    }

//    public String getStreamingServices() {
 //       return this.movie.getStreamingServices();
//    }

    public String getGenre() {
        StringBuilder genres = new StringBuilder();
        for (int i = 0; i < this.movie.getGenres().size() - 1; i++) {
            genres.append(this.movie.getGenres().get(i) + ", ");
        }
        genres.append(this.movie.getGenres().get(this.movie.getGenres().size() - 1));
        return genres.toString();
    }

    public String getTitle() {
        return this.movie.getTitle();
    }

    public String getPosterImagePath() {
        return this.movie.getPosterPath();
    }
}
