package interface_adapter.userprofile;

import use_case.goprofile.GoProfileInputBoundary;


public class UserProfileController {

    private final GoProfileInputBoundary goProfileUseCaseInteractor;

    public UserProfileController(GoProfileInputBoundary goProfileUseCaseInteractor) {
            this.goProfileUseCaseInteractor = goProfileUseCaseInteractor;
    }

    /**
     * Executes the "switch to UserProfileView" Use Case.
     */
    public void switchToProfileView() {
        goProfileUseCaseInteractor.switchToProfileView();
    }

    /**
     * Executes the "switch to MainView" Use Case.
     */
    public void switchToMainView() {
        goProfileUseCaseInteractor.switchToMainView();
    }


    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     */
    public void switchToChangePasswordView() { goProfileUseCaseInteractor.switchToChangePasswordView(); }

}

