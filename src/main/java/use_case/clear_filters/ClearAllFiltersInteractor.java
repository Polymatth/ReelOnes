package use_case.clear_filters;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClearAllFiltersInteractor implements ClearAllFiltersInputBoundary {

    private ClearAllFiltersOutputBoundary clearAllFiltersPresenter;

    public ClearAllFiltersInteractor(ClearAllFiltersOutputBoundary clearAllFiltersPresenter) {
        this.clearAllFiltersPresenter = clearAllFiltersPresenter;
    }

    @Override
    public void execute(ClearAllFiltersInputData clearAllFiltersInputData) {
        Map<String, List<Movie>> filtersToMovies = clearAllFiltersInputData.getFiltersToMovies();
        Map<String, List<String>> filtersToSelections = clearAllFiltersInputData.getFiltersToSelections();
        //Note: filtersToMovies and filtersToCategories have the same keyset.
        for (String category : filtersToMovies.keySet()) {
            filtersToMovies.replace(category, new ArrayList<>());
            filtersToSelections.replace(category, new ArrayList<>());
        }
        final ClearAllFiltersOutputData clearAllFiltersOutputData = new ClearAllFiltersOutputData(filtersToMovies,
                filtersToSelections);
        clearAllFiltersPresenter.executeClearAllFilters(clearAllFiltersOutputData);
    }
}
