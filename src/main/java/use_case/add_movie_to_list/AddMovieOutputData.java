package use_case.add_movie_to_list;


/**
 * Data class containing the result of adding a movie to a user list.
 */
public class AddMovieOutputData {
    private final String listName;
    private final String movieTitle;

    /**
     * Constructs an AddMovieToListOutputData object.
     * @param listName the name of the user list.
     * @param movieTitle the title of the movie added to the list.
     */
    public AddMovieOutputData(String listName, String movieTitle) {
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