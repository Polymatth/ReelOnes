package use_case.get_currentuser;

import entity.MovieList;

import java.util.List;

/**
 * Output Data for the Get Current User Use Case.
 */
public class GetCurrentUserOutputData {
    private final String username;
    private final String password;
    private final String favMovie;
    private final String favDirector;
    private List<MovieList> movieLists;

    public GetCurrentUserOutputData(String username, String password, String favMovie, String favDirector,  List<MovieList> movieLists) {
        this.username = username;
        this.password = password;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.movieLists = movieLists;
    }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public String getFavMovie() {return favMovie;}

    public String getFavDirector() {return favDirector;}

    public List<MovieList> getMovieLists() {
        return movieLists;
    }

}
