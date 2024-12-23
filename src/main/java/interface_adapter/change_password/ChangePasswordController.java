package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String username, String password,String favMovie, String favDirector) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password,favMovie, favDirector);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    public void switchToUserView() { userChangePasswordUseCaseInteractor.switchToUserView();}


}
