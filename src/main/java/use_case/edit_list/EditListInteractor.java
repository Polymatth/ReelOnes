package use_case.edit_list;

import entity.MovieList;

public class EditListInteractor implements EditListInputBoundary {
    private final EditListDataAccessInterface dataAccessObject;
    private final EditListOutputBoundary presenter;

    public EditListInteractor(EditListDataAccessInterface dao, EditListOutputBoundary presenter) {
        this.dataAccessObject = dao;
        this.presenter = presenter;
    }

    @Override
    public void execute(EditListInputData inputData) {
        MovieList movieList = dataAccessObject.getList(inputData.getUsername(), inputData.getOldListName());
        if (movieList == null) {
            presenter.prepareFailView("List not found!");
            return;
        }
        movieList.setListName(inputData.getNewListName());
        dataAccessObject.updateList(inputData.getUsername(), movieList);
        presenter.prepareSuccessView(new EditListOutputData(movieList.getListName(), movieList));
    }

    @Override
    public void switchToEditListView() {
        presenter.switchToEditListView();
    }

    @Override
    public void switchToOpenListView() {
        presenter.switchToOpenListView();
    }

}