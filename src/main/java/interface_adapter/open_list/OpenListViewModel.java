package interface_adapter.open_list;

import interface_adapter.ViewModel;

public class OpenListViewModel extends ViewModel<OpenListState> {

    public OpenListViewModel() {
        super("open list view");
        setState(new OpenListState());
    }
}