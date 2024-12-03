package interface_adapter.add_movie_to_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.open_list.OpenListViewModel;
import use_case.add_movie_to_list.AddMovieOutputBoundary;
import use_case.add_movie_to_list.AddMovieOutputData;

/**
 * implementation of AddMovieOutputBoundary.
 */
public class AddMoviePresenter implements AddMovieOutputBoundary {
    private OpenListViewModel openListViewModel;

    public AddMoviePresenter( OpenListViewModel openListViewModel) {
        this.openListViewModel = openListViewModel;
    }

    @Override
    public void prepareSuccessView(AddMovieOutputData outputData) {

        //openListViewModel.getState().setMovieListList(outputData.getUser().getMovieLists());
        //openListViewModel.firePropertyChanged("movies");
        //openListViewModel.firePropertyChanged("listName");
        System.out.println("Movie \"" + outputData.getMovieTitle() + "\" added to list \"" + outputData.getListName() + "\" successfully.");}

    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
}