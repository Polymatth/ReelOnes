package interface_adapter.get_currentuser;

import use_case.get_currentuser.GetCurrentUserInputBoundary;
import use_case.get_currentuser.GetCurrentUserInputData;
import use_case.get_currentuser.GetCurrentUserOutputData;

public class GetCurrentUserController {

    private final GetCurrentUserInputBoundary getCurrentUserInteractor;

    public GetCurrentUserController(GetCurrentUserInputBoundary getCurrentUserInteractor) {
        this.getCurrentUserInteractor = getCurrentUserInteractor;
    }

    public GetCurrentUserOutputData execute() {
        return getCurrentUserInteractor.execute();
    }
}
