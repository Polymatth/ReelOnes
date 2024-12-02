package interface_adapter.open_list;

import entity.Movie;
import interface_adapter.ViewManagerModel;
import use_case.open_list.OpenListOutputBoundary;
import use_case.open_list.OpenListOutputData;
import view.ViewManager;

import java.util.List;

public class OpenListPresenter implements OpenListOutputBoundary {
    private OpenListViewModel openListViewModel;
    private ViewManagerModel viewManager;

    public OpenListPresenter(OpenListViewModel viewModel, ViewManagerModel viewManager) {
        this.openListViewModel = viewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(OpenListOutputData outputData) {
        openListViewModel.getState().setListName(outputData.getListName());
        openListViewModel.getState().setMovies(outputData.getMovies());
        this.viewManager.setState(openListViewModel.getViewName());
        this.viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // implement later
    }
}