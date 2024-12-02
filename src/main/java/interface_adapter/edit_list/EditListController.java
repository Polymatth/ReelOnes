package interface_adapter.edit_list;

import use_case.edit_list.EditListInputBoundary;
import use_case.edit_list.EditListInputData;

/**
 * Controller for handling requests to edit a user list.
 */
public class EditListController {
    private final EditListInputBoundary editListInputBoundary;

    public EditListController(EditListInputBoundary useCase) {
        this.editListInputBoundary = useCase;
    }

    public void execute(String username, String oldListName, String newListName) {
        EditListInputData inputData = new EditListInputData(username, oldListName, newListName);
        editListInputBoundary.execute(inputData);
    }

    public void switchToEditListView() {
        editListInputBoundary.switchToEditListView();
    }

    public void switchToOpenListView() {
        editListInputBoundary.switchToOpenListView();
    }
}