package use_case.return_to_list_from_filter_categories;

import entity.Movie;
import use_case.filter_application.FilterCategoryConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReturnToListInteractor implements ReturnToListInputBoundary {

    private ReturnToListOutputBoundary returnToListPresenter;

    public ReturnToListInteractor(ReturnToListOutputBoundary returnToListPresenter) {
        this.returnToListPresenter = returnToListPresenter;
    }

    public void execute(ReturnToListInputData returnToListInputData) {
        Map<String, List<Movie>> filtersToMovies = returnToListInputData.getFiltersToMovies();
        List<Movie> finalFilteredList = new ArrayList<>(filtersToMovies.get(FilterCategoryConstants.GENRE));
        for (String category : filtersToMovies.keySet()) {
            finalFilteredList.retainAll(filtersToMovies.get(category));
        }
        final ReturnToListOutputData returnToListOutputData = new ReturnToListOutputData(filtersToMovies,
                returnToListInputData.getFiltersToSelections(), returnToListInputData.getListView(), finalFilteredList);
        this.returnToListPresenter.executeReturnToList(returnToListOutputData);
    }
}
