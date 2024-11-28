package interface_adapter.get_currentuser;

import interface_adapter.ViewManagerModel;

import use_case.get_currentuser.GetCurrentOutputBoundary;
import use_case.get_currentuser.GetCurrentUserOutputData;

public class GetCurrentUserPresenter implements GetCurrentOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public GetCurrentUserPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

    }


    @Override
    public void prepareSuccessView(GetCurrentUserOutputData outputData) {

    }
}
