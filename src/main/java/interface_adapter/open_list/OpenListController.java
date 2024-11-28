package interface_adapter.open_list;

import use_case.open_list.OpenListInputBoundary;
import use_case.open_list.OpenListInputData;

public class OpenListController {
    private final OpenListInputBoundary openListInputBoundary;

    public OpenListController(OpenListInputBoundary openListInputBoundary) {
        this.openListInputBoundary = openListInputBoundary;
    }

    public void execute(String listName) {
        final OpenListInputData openListInputData = new OpenListInputData(listName);

        openListInputBoundary.execute(openListInputData);
    }
}