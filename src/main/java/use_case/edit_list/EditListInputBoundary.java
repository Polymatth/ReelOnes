package use_case.edit_list;

/**
 * The Edit List Input Boundary.
 */
public interface EditListInputBoundary {
    /**
     * Executes the Edit List Use Case.
     *
     * @param inputData the input data for this use case
     */
    void execute(EditListInputData inputData);

    /**
     * Switches back to the edit List View.
     */
    void switchToEditListView();

    /**
     * Switches back to the Open List View.
     */
    void switchToOpenListView();
}