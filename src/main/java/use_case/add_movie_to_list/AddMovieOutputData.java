package use_case.add_movie_to_list;


import entity.User;

/**
 * Data class containing the result of adding a movie to a user list.
 */
public class AddMovieOutputData {
    private final String listName;
    private final String movieTitle;
    private final User user;

    /**
     * Constructs an AddMovieToListOutputData object.
     * @param listName the name of the user list.
     * @param movieTitle the title of the movie added to the list.
     */
    public AddMovieOutputData(String listName, String movieTitle, User user) {
        this.listName = listName;
        this.movieTitle = movieTitle;
        this.user = user;
    }

    public String getListName() {
        return listName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public User getUser(){return user;}
}