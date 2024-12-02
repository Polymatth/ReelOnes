package use_case.return_to_filter_categories;

import entity.Movie;

import java.util.List;

/**
 * The return to filter categories interactor.
 */
public class ReturnToFilterCategoriesInteractor implements ReturnToFilterCategoriesInputBoundary {

    private ReturnToFilterCategoriesOutputBoundary returnToFilterCategoriesPresenter;

    public ReturnToFilterCategoriesInteractor(ReturnToFilterCategoriesOutputBoundary returnToFilterCategoriesPresenter) {
        this.returnToFilterCategoriesPresenter = returnToFilterCategoriesPresenter;
    }

    @Override
    public void execute(ReturnToFilterCategoriesInputData returnToFilterCategoriesInputData) {
        String categoryName = returnToFilterCategoriesInputData.getCategoryName();
        List<String> selectedOptions = returnToFilterCategoriesInputData.getSelectedOptions();
        List<Movie> filteredList = returnToFilterCategoriesInputData.getFilteredList();
        ReturnToFilterCategoriesOutputData returnToFilterCategoriesOutputData = new ReturnToFilterCategoriesOutputData(
                categoryName, selectedOptions, filteredList);
        this.returnToFilterCategoriesPresenter.executeReturnToFilterCategories(returnToFilterCategoriesOutputData);
    }
}
