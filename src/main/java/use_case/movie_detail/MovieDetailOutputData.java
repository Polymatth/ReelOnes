package use_case.movie_detail;

import entity.Movie;

import java.util.List;

public class MovieDetailOutputData {

    private final Movie movie;
    private final String originView;

    public MovieDetailOutputData(Movie movie, String originView) {
        this.movie = movie;
        this.originView = originView;
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

    public String getStreamingServices() {
        return String.join(", ", this.movie.getStreaming());
    }

    public String getGenre() {
//        StringBuilder genres = new StringBuilder();
//        for (int i = 0; i < this.movie.getGenres().size() - 1; i++) {
//            genres.append(this.movie.getGenres().get(i) + ", ");
//        }
//        genres.append(this.movie.getGenres().get(this.movie.getGenres().size() - 1));
        return String.join(", ", this.movie.getGenres());
    }

    public List<String> getGenres() {
        return this.movie.getGenres();
    }

    public String getTitle() {
        return this.movie.getTitle();
    }

    public String getPosterImagePath() {
        return this.movie.getPosterPath();
    }

    public int getID() {
        return this.movie.getID();
    }

    public String getOriginView() {
        return this.originView;
    }
}
