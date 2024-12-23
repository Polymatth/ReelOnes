package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MovieList entity.
 */
public abstract class MovieList {
    private final String userId;
    private String listName;
    private boolean isPublic;
    private List<Movie> movies;

    public MovieList(String userId, String listName, Boolean isPublic, List<Movie> movies) {
        this.userId = userId;
        this.listName = listName;
        this.isPublic = isPublic;
        this.movies = movies;
    }

    public MovieList(String userId, String listName, boolean isPublic) {
            this.userId = userId;
            this.listName = listName;
            this.isPublic = isPublic;
            this.movies = new ArrayList<>();
        }

    public String getUserId() {
        return userId;
    }

    public String getListName() {
        return listName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    // Setters
    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean getPublic() {
         return isPublic;
    }


    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public int size() {return movies.size();}

    public boolean containsMovie(Movie movie) {
        return movies.contains(movie);
    }

    public boolean containsMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean isEditable();

}