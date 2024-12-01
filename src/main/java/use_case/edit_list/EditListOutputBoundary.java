package use_case.edit_list;

/**
 * Output Boundary for the Edit List Use Case.
 */
public interface EditListOutputBoundary {
    void prepareSuccessView(EditListOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToEditListView();

    void switchToOpenListView();
}