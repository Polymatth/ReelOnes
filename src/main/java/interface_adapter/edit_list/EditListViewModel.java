package interface_adapter.edit_list;


import interface_adapter.ViewModel;

public class EditListViewModel extends ViewModel<EditListState> {
    public EditListViewModel() {
        super("edit list");
        setState(new EditListState());
    }
}