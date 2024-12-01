package interface_adapter.userprofile;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.loggedin.LoggedInViewModel;
import use_case.goprofile.GoProfileOutputBoundary;

public class UserProfilePresenter implements GoProfileOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private UserProfileViewModel userProfileViewModel;
    private LoggedInViewModel loggedInViewModel;

    private ChangePasswordViewModel changePasswordViewModel;

    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel, LoggedInViewModel loggedInViewModel,ChangePasswordViewModel changePasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(userProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void switchToProfileView() {
        viewManagerModel.setState(userProfileViewModel.getViewName());
        userProfileViewModel.loadCurrentUser();
        viewManagerModel.firePropertyChanged();
        System.out.println(userProfileViewModel.getState().getFavDirector());
    }


    @Override
    public void switchToMainView() {
        System.out.println("Switching to main view...");
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged("state");
    }

    @Override
    public void switchToChangePasswordView() {
        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged("state");
    }

}
