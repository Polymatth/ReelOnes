package use_case.filter_application;

import entity.Movie;
import interface_adapter.filter_categories.FilterCategoriesState;
import use_case.filter_category_selection.FilterCategorySelectionOutputBoundary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

/**
 * The filter application interactor.
 */
public class FilterApplicationInteractor implements FilterApplicationInputBoundary {

    private FilterApplicationOutputBoundary filterApplicationPresenter;
    private FilterApplicationDataAccessInterface dataAccessInterface;

    public FilterApplicationInteractor(FilterApplicationOutputBoundary filterApplicationPresenter,
                                       FilterApplicationDataAccessInterface dataAccessInterface) {
        this.filterApplicationPresenter = filterApplicationPresenter;
        this.dataAccessInterface = dataAccessInterface;
    }

    public void execute(FilterApplicationInputData filterApplicationInputData) {
        String categoryName = filterApplicationInputData.getCategoryName();
        List<String> optionsSelected = filterApplicationInputData.getOptionsSelected();
        List<String> allOptions = filterApplicationInputData.getAllOptions();
        List<Movie> originalList = filterApplicationInputData.getOriginalList();
        List<Movie> applicableMovies = new ArrayList<>();
        if (optionsSelected.isEmpty() || optionsSelected.size() == allOptions.size()) {
            applicableMovies.addAll(originalList);
        } else if (categoryName.equals(FilterCategoryConstants.GENRE)) {
            filterGenres(originalList, optionsSelected, applicableMovies);
        } else if (categoryName.equals(FilterCategoryConstants.DECADE_OF_RELEASE)) {
            filterDecades(optionsSelected, originalList, applicableMovies);
        } else if (categoryName.equals(FilterCategoryConstants.STREAMING_SERVICES)) {
            filterStreaming(originalList, optionsSelected, applicableMovies);
        } else if (categoryName.equals(FilterCategoryConstants.POPULARITY_RATING)) {
            filterRatings(originalList, optionsSelected, applicableMovies);
        }
        final FilterApplicationOutputData filterApplicationOutputData = new
                FilterApplicationOutputData(applicableMovies, optionsSelected, categoryName);
        this.filterApplicationPresenter.updateFilteredList(filterApplicationOutputData);

    }

    private static void filterRatings(List<Movie> originalList, List<String> optionsSelected, List<Movie> applicableMovies) {
        for (Movie movie : originalList) {
            for (String option : optionsSelected) {
                List<String> popRange = Arrays.asList(option.split("-"));
                if (movie.getVoteAverage() >= Float.parseFloat(popRange.get(0)) &&
                        movie.getVoteAverage() <= Float.parseFloat(popRange.get(1))) {
                    applicableMovies.add(movie);
                }
            }
        }
    }

    private void filterStreaming(List<Movie> originalList, List<String> optionsSelected, List<Movie> applicableMovies) {
        for (Movie movie : originalList) {
            List<String> servicesAvailable = dataAccessInterface.getStreamingServices(movie.getID());
            servicesAvailable.retainAll(optionsSelected);
            if (!servicesAvailable.isEmpty()) {
                applicableMovies.add(movie);
            }
        }
    }

    private static void filterDecades(List<String> optionsSelected, List<Movie> originalList, List<Movie> applicableMovies) {
        List<String> possibleDecades = new ArrayList<>();
        for (String option : optionsSelected) {
            StringBuilder decade = new StringBuilder();
            decade.append(option.charAt(0));
            decade.append(option.charAt(1));
            decade.append(option.charAt(2));
            possibleDecades.add(decade.toString());
        }
        for (Movie movie : originalList) {
            String year = movie.getYear();
            StringBuilder decade = new StringBuilder();
            decade.append(year.charAt(0));
            decade.append(year.charAt(1));
            decade.append(year.charAt(2));
            if (possibleDecades.contains(decade.toString())) {
                applicableMovies.add(movie);
    }
        } }

    private void filterGenres(List<Movie> originalList, List<String> optionsSelected, List<Movie> applicableMovies) {
        for (Movie movie : originalList) {
            List<String> genres = dataAccessInterface.getGenres(movie.getGenre_ids());
            List<String> copyList = new ArrayList<>(genres);
            copyList.retainAll(optionsSelected);
            if (!copyList.isEmpty()) {
                applicableMovies.add(movie);
            }
        }
    }
}
