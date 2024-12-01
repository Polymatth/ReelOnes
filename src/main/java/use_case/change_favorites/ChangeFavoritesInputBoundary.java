package use_case.change_favorites;

import use_case.change_password.ChangePasswordInputData;

/**
 * The Change Favorites Use Case.
 */
public interface ChangeFavoritesInputBoundary {

    /**
     * Execute the Change Favorites Use Case.
     * @param  changeFavoritesInputData  input data for this use case
     */
    void execute(ChangeFavoritesInputData changeFavoritesInputData);


}
