package use_case.get_currentuser;

/**
 * Input Boundary for actions which are related to getting the current user
 */
public interface GetCurrentUserInputBoundary {

    /**
     * Executes the get current user use case.
     * @return user output data
     */
    GetCurrentUserOutputData execute();

}
