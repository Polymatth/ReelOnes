package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private String favMovie;
    private String favDirector;


    public SignupInputData(String username, String password, String repeatPassword, String favMovie, String favDirector) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.favMovie = favMovie;
        this.favDirector = favDirector;

    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getFavMovie() { return favMovie; }

    public String getFavDirector() { return favDirector; }

}
