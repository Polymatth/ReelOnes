package interface_adapter.userprofile;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.goprofile.GoProfileOutputBoundary;
import use_case.goprofile.GoProfileOutputData;

public class UserProfilePresenter implements GoProfileOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private UserProfileViewModel userProfileViewModel;

    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setState(userProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }

    @Override
    public void switchToProfileView() {
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
