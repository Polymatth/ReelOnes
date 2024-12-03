package use_case.open_list;

import entity.Movie;
import use_case.get_currentuser.GetCurrentUserDataAccessInterface;
import use_case.get_currentuser.GetCurrentUserInputBoundary;

import java.util.List;

/**
 * The OpenList Interactor.
 */
public class OpenListInteractor implements OpenListInputBoundary {
    private final OpenListDataAccessInterface repository;
    private final OpenListOutputBoundary presenter;

    public OpenListInteractor(OpenListDataAccessInterface repository, OpenListOutputBoundary presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(OpenListInputData inputData) {
        try{

            List<Movie> movies = repository.getMoviesForList(inputData.getListName());

            OpenListOutputData outputData = new OpenListOutputData(inputData.getListName(), movies);

            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Error opening list: " + e.getMessage());
    }
    }
}
