package use_case.saveuser;

public class SaveUserInputData {
    private final String name;
    private final String password;
    private final String favMovie;
    private final String favDirector;

    public SaveUserInputData(String name, String password, String favMovie, String favDirector) {
        this.name = name;
        this.password = password;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getFavMovie() { return favMovie; }
    public String getFavDirector() { return favDirector; }
}
