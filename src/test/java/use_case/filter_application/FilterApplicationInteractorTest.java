package use_case.filter_application;

import app.AppConfig;
import entity.Movie;
import interface_adapter.filter_categories.FilterCategoriesState;
import org.junit.jupiter.api.Test;
import use_case.movie_detail.*;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterApplicationInteractorTest {

    @Test
    void GenreFilterApplicationSuccessThreeFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> optionsSelected = new ArrayList<>();
        optionsSelected.add("Fantasy");
        optionsSelected.add("Drama");
        optionsSelected.add("Comedy");
        List<String> allOptions = Arrays.asList(new String[]{"Action", "Adventure", "Animation", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance",
                "Science Fiction"});
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.GENRE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that you get the right number of movies
                assertEquals(5, filterApplicationOutputData.getApplicableMovies().size());
                //Test that you get the right movie titles
                List<String> expectedTitles = Arrays.asList(new String[]{"The Substance", "The Substance of Fire",
                        "The Night's Substance", "The Substance of Things Hoped For", "Substance: Milk"});
                List<String> actualTitles = new ArrayList<>();
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    actualTitles.add(movie.getTitle());
                }
                assertEquals(expectedTitles, actualTitles);
                //Test that there are no movies in the resulting list that are NOT fantasy, comedy, or drama.
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    assert(movie.getGenre_ids().contains(14) || movie.getGenre_ids().contains(35) ||
                            movie.getGenre_ids().contains(18));
                }
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.GENRE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void GenreFilterApplicationSuccessAllFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"Action", "Adventure", "Animation", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance",
                "Science Fiction"});
        List<String> optionsSelected = new ArrayList<>(allOptions);
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.GENRE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies are included in the resulting list.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.GENRE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());

            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void GenreFilterApplicationSuccessNoFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> optionsSelected = new ArrayList<>();
        List<String> allOptions = Arrays.asList(new String[]{"Action", "Adventure", "Animation", "Comedy", "Crime",
                "Documentary", "Drama", "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance",
                "Science Fiction"});
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.GENRE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies are included in the resulting list.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.GENRE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void DecadeFilterApplicationSuccessThreeFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> optionsSelected = new ArrayList<>();
        optionsSelected.add("2020s");
        optionsSelected.add("2010s");
        List<String> allOptions = Arrays.asList(new String[]{"2020s", "2010s", "2000s", "1990s", "1980s", "1970s",
                "1960s", "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"});

        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.DECADE_OF_RELEASE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that you get the right number of movies
                assertEquals(10, filterApplicationOutputData.getApplicableMovies().size());
                //Test that you get the right movie titles
                List<String> expectedTitles = Arrays.asList(new String[]{"The Substance",
                        "The Substance: Albert Hofmann's LSD",
                        "The Night's Substance", "Postmodernism: The Substance of Style",
                        "A Magical Substance Flows Into Me", "Substance: Jam", "Substance", "Unexplainable Substance",
                        "Substance: Milk", "Corpo dei giorni"});
                List<String> actualTitles = new ArrayList<>();
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    actualTitles.add(movie.getTitle());
                }
                assertEquals(expectedTitles, actualTitles);
                //Test that there are no movies in the resulting list that are NOT released in the 2010s or 2020s.
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    assert((2020 <= Integer.parseInt(movie.getYear()) || 2029 >= Integer.parseInt(movie.getYear())) ||
                            (2010 <= Integer.parseInt(movie.getYear()) || 2019 >= Integer.parseInt(movie.getYear())));
                }
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.DECADE_OF_RELEASE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void DecadeFilterApplicationSuccessAllFilterss() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"2020s", "2010s", "2000s", "1990s", "1980s", "1970s",
                "1960s", "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"});
        List<String> optionsSelected = new ArrayList<>(allOptions);
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.DECADE_OF_RELEASE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.DECADE_OF_RELEASE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void DecadeFilterApplicationSuccessNoFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"2020s", "2010s", "2000s", "1990s", "1980s", "1970s",
                "1960s", "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"});
        List<String> optionsSelected = new ArrayList<>();
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants.DECADE_OF_RELEASE,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.DECADE_OF_RELEASE, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void PopularityFilterApplicationSuccessTwoFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> optionsSelected = new ArrayList<>();
        optionsSelected.add("5.0-5.9");
        optionsSelected.add("6.0-6.9");
        List<String> allOptions = Arrays.asList(new String[]{"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"});

        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(
                FilterCategoryConstants.POPULARITY_RATING, optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that you get the right number of movies
                assertEquals(4, filterApplicationOutputData.getApplicableMovies().size());
                //Test that you get the right movie titles
                List<String> expectedTitles = Arrays.asList(new String[]{"The Substance: Albert Hofmann's LSD",
                        "The Substance of Fire", "The Night's Substance", "A Magical Substance Flows Into Me"});
                List<String> actualTitles = new ArrayList<>();
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    actualTitles.add(movie.getTitle());
                }
                assertEquals(expectedTitles, actualTitles);
                //Test that there are no movies in the resulting list that are NOT released in the 2010s or 2020s.
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    assert(((float)5.0 <= movie.getVoteAverage() && (float)5.9 >= movie.getVoteAverage()) ||
                            ((float)6.0 <= movie.getVoteAverage() && (float)6.9 >= movie.getVoteAverage()));
                }
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.POPULARITY_RATING, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void PopularityFilterApplicationSuccessAllFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"});
        List<String> optionsSelected = new ArrayList<>(allOptions);
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants
                .POPULARITY_RATING,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.POPULARITY_RATING, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void PopularityFilterApplicationSuccessNoFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"});
        List<String> optionsSelected = new ArrayList<>();
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants
                .POPULARITY_RATING,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.POPULARITY_RATING, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void StreamingServiceFilterApplicationSuccessThreeFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> optionsSelected = new ArrayList<>();
        optionsSelected.add("Netflix");
        optionsSelected.add("Amazon Prime Video");
        List<String> allOptions = Arrays.asList(new String[]{"Netflix", "Amazon Prime Video", "Disney Plus", "Iqiyi",
                "Apple TV", "Max"});

        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(
                FilterCategoryConstants.STREAMING_SERVICES, optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that you get the right number of movies
                assertEquals(1, filterApplicationOutputData.getApplicableMovies().size());
                //Test that you get the right movie titles
                List<String> expectedTitles = Arrays.asList(new String[]{"The Substance of Things Hoped For"});
                List<String> actualTitles = new ArrayList<>();
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    actualTitles.add(movie.getTitle());
                }
                assertEquals(expectedTitles, actualTitles);
                //Test that there are no movies in the resulting list that are NOT released in the 2010s or 2020s.
                for (Movie movie : filterApplicationOutputData.getApplicableMovies()) {
                    assert(((FilterApplicationDataAccessInterface)dataAccessInterface).getStreamingServices(movie.getID())
                            .retainAll(optionsSelected));
                }
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.STREAMING_SERVICES, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void StreamingServicesFilterApplicationSuccessAllFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"Netflix", "Amazon Prime Video", "Disney Plus", "Iqiyi",
                "Apple TV", "Max"});
        List<String> optionsSelected = new ArrayList<>(allOptions);
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(FilterCategoryConstants
                .STREAMING_SERVICES,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.STREAMING_SERVICES, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }

    @Test
    void StreamingServicesFilterApplicationSuccessNoFilters() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Create the Movie List that is being filtered.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        List<String> allOptions = Arrays.asList(new String[]{"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"});
        List<String> optionsSelected = new ArrayList<>();
        FilterApplicationInputData filterApplicationInputData = new FilterApplicationInputData(
                FilterCategoryConstants.STREAMING_SERVICES,
                optionsSelected, allOptions, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterApplicationOutputBoundary filterApplicationPresenter = new FilterApplicationOutputBoundary() {
            @Override
            public void updateFilteredList(FilterApplicationOutputData filterApplicationOutputData) {
                //Test that all the movies of the original list are included.
                assertEquals(16, filterApplicationOutputData.getApplicableMovies().size());
                //Test that the category name is correct.
                assertEquals(FilterCategoryConstants.STREAMING_SERVICES, filterApplicationOutputData.getCategoryName());
                //Test that the correct list of selected options has been stored.
                assertEquals(optionsSelected, filterApplicationOutputData.getOptionsSelected());
            }
        };
        FilterApplicationInputBoundary interactor = new FilterApplicationInteractor(filterApplicationPresenter,
                (FilterApplicationDataAccessInterface) dataAccessInterface) {
        };
        interactor.execute(filterApplicationInputData);
    }
}
