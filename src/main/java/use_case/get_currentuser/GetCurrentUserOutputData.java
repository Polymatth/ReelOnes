package use_case.get_currentuser;

/**
 * Output Data for the Get Current User Use Case.
 */
public class GetCurrentUserOutputData {
    private final String username;
    private final String password;
    private final String favMovie;
    private final String favDirector;

    public GetCurrentUserOutputData(String username, String password, String favMovie, String favDirector) {
        this.username = username;
        this.password = password;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
    }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public String getFavMovie() {return favMovie;}

    public String getFavDirector() {return favDirector;}

}
