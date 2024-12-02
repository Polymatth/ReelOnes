package use_case.add_movie_to_list;

/**
 * Data class containing information to add a movie to a user list.
 */
public class AddMovieInputData {
    private final String listName;
    private final String movieTitle;

    public AddMovieInputData(String listName, String movieTitle) {
        this.listName = listName;
        this.movieTitle = movieTitle;
    }

    public String getListName() {
        return listName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}