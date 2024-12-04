package use_case.goprofile;

/**
 * Input Boundary for actions which are related to going to the profile.
 */

public interface GoProfileInputBoundary {
    /**
     * Executes the go profile use case.
     */

    void execute();

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();


    /**
     * Executes the switch to main view use case.
     */
    void switchToMainView();

    /**
     * Executes the switch to change password view use case.
     */
    void switchToChangePasswordView();
}
