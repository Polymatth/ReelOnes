package use_case.saveuser;

public interface SaveUserOutputBoundary {

    void presentSuccess(String message);
    void presentFailure(String errorMessage);
}
