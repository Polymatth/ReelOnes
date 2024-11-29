package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.userprofile.UserProfileViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final UserProfileViewModel userProfileViewModel;


    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,ChangePasswordViewModel changePasswordViewModel, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        changePasswordViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    public void switchToUserView(){
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
