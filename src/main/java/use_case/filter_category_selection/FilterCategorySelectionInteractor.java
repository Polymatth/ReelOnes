package use_case.filter_category_selection;

import entity.Movie;

import java.util.List;

public class FilterCategorySelectionInteractor implements FilterCategorySelectionInputBoundary {

    private FilterCategorySelectionOutputBoundary filterCategorySelectionPresenter;

    public FilterCategorySelectionInteractor(FilterCategorySelectionOutputBoundary filterCategorySelectionPresenter) {
        this.filterCategorySelectionPresenter = filterCategorySelectionPresenter;
    }

    @Override
    public void execute(FilterCategorySelectionInputData filterCategorySelectionInputData) {
        String categoryName = filterCategorySelectionInputData.getCategoryName();
        String[] categoryOptions = filterCategorySelectionInputData.getCategoryOptions();
        List<Movie> originalMovieList = filterCategorySelectionInputData.getOriginalMovieList();
        final FilterCategorySelectionOutputData filterCategorySelectionOutputData = new
                FilterCategorySelectionOutputData(categoryName, categoryOptions, originalMovieList);
        this.filterCategorySelectionPresenter.prepareSuccessView(filterCategorySelectionOutputData);
        //note: there is no fail view because there is currently no way for this use case to fail.
    }
}
