package use_case.change_favorites;
/**
 * Output Data for the Change Favorites Use Case.
 */

public class ChangeFavoritesOutputData {
    private final String username;

    private final boolean useCaseFailed;

    public ChangeFavoritesOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
