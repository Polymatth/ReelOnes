package use_case.get_currentuser;



/**
 * The output boundary for the Get Current User Use Case.
 */
public interface GetCurrentOutputBoundary {

    /**
     * Prepares the success view for the Get Current User Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetCurrentUserOutputData outputData);

}
