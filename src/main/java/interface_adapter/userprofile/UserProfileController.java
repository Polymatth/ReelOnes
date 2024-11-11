package interface_adapter.userprofile;

import use_case.goprofile.GoProfileInputBoundary;
import use_case.goprofile.GoProfileInputData;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

public class UserProfileController {

    private GoProfileInputBoundary goProfileUseCaseInteractor;

    public UserProfileController(GoProfileInputBoundary goProfileUseCaseInteractor) {
            this.goProfileUseCaseInteractor = goProfileUseCaseInteractor;
    }

    /**
     * Executes the Go Profile Use Case.
     * @param username the username of the user going to their profile
     */
    public void executeGoProfile(String username) {
        // 1. Instantiate the GoProfileInputData, containing the username.
        GoProfileInputData goProfileInputData = new GoProfileInputData(username);

        // 2. Tell the interactor to execute the Go Profile use case.
        goProfileUseCaseInteractor.execute(goProfileInputData);
    }
}

