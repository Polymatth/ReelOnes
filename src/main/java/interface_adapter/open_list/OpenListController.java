package interface_adapter.open_list;

import entity.Movie;
import use_case.open_list.OpenListInputBoundary;
import use_case.open_list.OpenListInputData;

import java.util.List;

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