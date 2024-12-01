package interface_adapter.edit_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.open_list.OpenListViewModel;
import use_case.edit_list.EditListOutputBoundary;
import use_case.edit_list.EditListOutputData;

/**
 * Presenter class for the Edit List use case. Transforms response models into view models.
 */
public class EditListPresenter implements EditListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final EditListViewModel editListViewModel;
    private final OpenListViewModel openListViewModel;

    public EditListPresenter(ViewManagerModel viewManagerModel, EditListViewModel editListViewModel, OpenListViewModel openListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editListViewModel = editListViewModel;
        this.openListViewModel = openListViewModel;
    }

    @Override
    public void prepareSuccessView(EditListOutputData outputData) {
        // Update the ViewModel with the modified list details.
        editListViewModel.getState().setListName(outputData.getUpdatedListName());
        editListViewModel.getState().setMovies(outputData.getUpdatedMovies());
        editListViewModel.firePropertyChanged("listUpdated");
    }


    @Override
    public void prepareFailView(String errorMessage) {
        // Handle any errors and update the ViewModel accordingly.
        editListViewModel.getState().setErrorMessage(errorMessage);
        editListViewModel.firePropertyChanged("errorOccurred");
    }

    @Override
    public void switchToEditListView() {
        viewManagerModel.setState(editListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToOpenListView() {
        viewManagerModel.setState(openListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}