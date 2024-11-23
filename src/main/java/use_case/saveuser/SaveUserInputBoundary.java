package use_case.saveuser;

import use_case.login.LoginInputData;

/**
 * Input Boundary for actions which are related to saving user information.
 */
public interface SaveUserInputBoundary {

    /**
     * Executes the save user use case.
     * @param saveUserInputData the input data
     */
    void execute(SaveUserInputData  saveUserInputData);

}
