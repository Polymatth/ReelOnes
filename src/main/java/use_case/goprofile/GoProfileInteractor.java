package use_case.goprofile;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutOutputBoundary;

/**
 * The GoProfile Interactor.
 */

public class GoProfileInteractor implements GoProfileInputBoundary {


    private GoProfileOutputBoundary userProfilePresenter;
    @Override
    public void execute(GoProfileInputData profileInputData) {
       userProfilePresenter.prepareSuccessView();
    }
}
