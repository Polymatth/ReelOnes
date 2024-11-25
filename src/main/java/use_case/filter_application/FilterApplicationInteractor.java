package use_case.filter_application;

import entity.Movie;
import use_case.filter_category_selection.FilterCategorySelectionOutputBoundary;
import use_case.movie_detail.MovieDetailDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FilterApplicationInteractor implements FilterApplicationInputBoundary {

    private FilterApplicationOutputBoundary filterApplicationPresenter;
    private MovieDetailDataAccessInterface dataAccessInterface;

    public FilterApplicationInteractor(FilterApplicationOutputBoundary filterApplicationPresenter,
                                       MovieDetailDataAccessInterface dataAccessInterface) {
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
        } else if (categoryName.equals("Genre")) {
            for (Movie movie : originalList) {
                Map<Integer, String> genres = dataAccessInterface.getGenres();
                List<String> genreList = new ArrayList<>();
                for (int genreId : movie.getGenre_ids()) {
                    genreList.add(genres.get(genreId));
                }
                movie.setGenres(genreList);
                List<String> copyList = movie.getGenres();
                copyList.retainAll(optionsSelected);
                if (!copyList.isEmpty()) {
                    applicableMovies.add(movie);
                }
            }
        } else if (categoryName.equals("Decade of Release")) {
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
            }
        } else if (categoryName.equals("Streaming Services")) {
        } else if (categoryName.equals("Popularity Ratings")) {
            for (Movie movie : originalList) {
                for (String option : optionsSelected) {
                    List<String> popRange = Arrays.asList(option.split("-"));
                    if (movie.getVote_average() >= Float.parseFloat(popRange.get(0)) &&
                            movie.getVote_average() <= Float.parseFloat(popRange.get(1))) {
                        applicableMovies.add(movie);
                    }
                }
            }
        }
        final FilterApplicationOutputData filterApplicationOutputData = new
                FilterApplicationOutputData(applicableMovies);
        this.filterApplicationPresenter.updateFilteredList(filterApplicationOutputData);

    }
}
