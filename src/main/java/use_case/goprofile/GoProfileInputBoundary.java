package use_case.goprofile;

/**
 * Input Boundary for actions which are related to going to the profile.
 */

public interface GoProfileInputBoundary {
    /**
     * Executes the go profile use case.
     * @param profileInputData the input data
     */

    void execute(GoProfileInputData profileInputData);

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();

}
