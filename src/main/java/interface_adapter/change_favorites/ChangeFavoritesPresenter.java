package interface_adapter.change_favorites;

import interface_adapter.ViewManagerModel;
import interface_adapter.userprofile.UserProfileViewModel;
import use_case.change_favorites.ChangeFavoritesOutputBoundary;
import use_case.change_favorites.ChangeFavoritesOutputData;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Favorites Use Case.
 */

public class ChangeFavoritesPresenter implements ChangeFavoritesOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ChangeFavoritesViewModel changeFavoritesViewModel;
    private final UserProfileViewModel userProfileViewModel;

    public ChangeFavoritesPresenter(ViewManagerModel viewManagerModel,ChangeFavoritesViewModel changeFavoritesViewModel,UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changeFavoritesViewModel = changeFavoritesViewModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeFavoritesOutputData outputData) {

        changeFavoritesViewModel.firePropertyChanged("favs");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }
}
