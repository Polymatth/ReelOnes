package use_case.change_favorites;

public class ChangeFavoritesInputData {
    private final String password;
    private final String username;
    private final String favMovie;
    private final String favDirector;

    public ChangeFavoritesInputData(String username, String password, String favMovie, String favDirector) {
        this.password = password;
        this.username = username;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getFavMovie() {return favMovie;}

    String getFavDirector() {return favDirector;}
}
