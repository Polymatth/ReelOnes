package use_case.get_currentuser;

import entity.User;

/**
 * The Get Current User Interactor.
 */
public class GetCurrentUserInteractor implements GetCurrentUserInputBoundary {

    private final GetCurrentUserDataAccessInterface userDataAccessObject;
    private final GetCurrentOutputBoundary getCurrentUserPresenter;

    public GetCurrentUserInteractor(GetCurrentUserDataAccessInterface userDataAccessObject, GetCurrentOutputBoundary getCurrentUserPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.getCurrentUserPresenter = getCurrentUserPresenter;
    }
    @Override
    public GetCurrentUserOutputData execute() {
         final String username = userDataAccessObject.getCurrentUsername();
         final User currentUser = userDataAccessObject.get(username);
         final GetCurrentUserOutputData outputData = new GetCurrentUserOutputData(currentUser.getName(),currentUser.getPassword(),currentUser.getFavMovie(),currentUser.getFavDirector());
         getCurrentUserPresenter.prepareSuccessView(outputData);

        return outputData;
    }

}
