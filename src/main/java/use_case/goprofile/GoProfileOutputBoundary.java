package use_case.goprofile;

public interface GoProfileOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();
}
