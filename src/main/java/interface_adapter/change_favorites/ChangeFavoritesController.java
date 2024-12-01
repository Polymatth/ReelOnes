package interface_adapter.change_favorites;

import use_case.change_favorites.ChangeFavoritesInputBoundary;
import use_case.change_favorites.ChangeFavoritesInputData;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Favorites Use Case.
 */

public class ChangeFavoritesController {
    private final ChangeFavoritesInputBoundary userChangeFavoritesUseCaseInteractor;

    public ChangeFavoritesController(ChangeFavoritesInputBoundary userChangeFavoritesUseCaseInteractor) {
       this.userChangeFavoritesUseCaseInteractor = userChangeFavoritesUseCaseInteractor;
    }

    /**
     * Executes the Change Favorites Use Case.
     * @param favMovie the new favorite movie
     * @param favDirector the new favorite director
     * @param password the password
     * @param username the user whose favorites to change
     */
    public void execute(String username, String password,String favMovie, String favDirector) {
        final ChangeFavoritesInputData changeFavoritesInputData = new ChangeFavoritesInputData(username, password, favMovie, favDirector);
        userChangeFavoritesUseCaseInteractor.execute(changeFavoritesInputData);
    }
}
