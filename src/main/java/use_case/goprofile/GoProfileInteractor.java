package use_case.goprofile;

/**
 * The GoProfile Interactor.
 */

public class GoProfileInteractor implements GoProfileInputBoundary {


    private final GoProfileOutputBoundary userProfilePresenter;

    public GoProfileInteractor(GoProfileOutputBoundary userProfilePresenter) {
        this.userProfilePresenter = userProfilePresenter;
    }
    @Override
    public void execute() {
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
