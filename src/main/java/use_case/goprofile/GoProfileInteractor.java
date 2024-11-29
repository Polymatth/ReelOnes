package use_case.goprofile;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutOutputBoundary;

/**
 * The GoProfile Interactor.
 */

public class GoProfileInteractor implements GoProfileInputBoundary {


    private final GoProfileOutputBoundary userProfilePresenter;

    public GoProfileInteractor(GoProfileOutputBoundary userProfilePresenter) {
        this.userProfilePresenter = userProfilePresenter;
    }
    @Override
    public void execute(GoProfileInputData profileInputData) {
       userProfilePresenter.prepareSuccessView();
    }

    @Override
    public void switchToProfileView() {
        userProfilePresenter.switchToProfileView();
    }

    @Override
    public void switchToMainView() {userProfilePresenter.switchToMainView();}

    @Override
    public void switchToChangePasswordView() {
        userProfilePresenter.switchToChangePasswordView();
    }

}
