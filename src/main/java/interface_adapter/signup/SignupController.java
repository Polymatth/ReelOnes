package interface_adapter.signup;

import interface_adapter.user_repository.SaveUserController;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     *
     * @param username    the username to sign up
     * @param password1   the password
     * @param password2   the password repeated
     * @param favMovie
     * @param favDirector
     */
    public void execute(String username, String password1, String password2, String favMovie, String favDirector) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2, favMovie,favDirector);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to SignUpView" Use Case.
     */
    public void switchToSignUpView() {
        userSignupUseCaseInteractor.switchToSignUpView();
    }

}
