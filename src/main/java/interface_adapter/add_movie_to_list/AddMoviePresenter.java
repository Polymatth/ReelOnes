package interface_adapter.add_movie_to_list;

import interface_adapter.ViewManagerModel;
import use_case.add_movie_to_list.AddMovieOutputBoundary;
import use_case.add_movie_to_list.AddMovieOutputData;

/**
 * implementation of AddMovieOutputBoundary.
 */
public class AddMoviePresenter implements AddMovieOutputBoundary {

    public void AddMoviePresenter() {
    }

    @Override
    public void prepareSuccessView(AddMovieOutputData outputData) {
        System.out.println("Movie \"" + outputData.getMovieTitle() + "\" added to list \"" + outputData.getListName() + "\" successfully.");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
}