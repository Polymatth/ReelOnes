package use_case.clear_all_filters;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.clear_filters.*;
import use_case.filter_application.FilterCategoryConstants;
import use_case.go_to_filter_categories.*;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ClearAllFiltersInteractorTest {

    @Test
    void ClearAllFiltersTest() {
        AppConfig config = new AppConfig();
        //Create the original list that was being filtered through.
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Set the filters that have been selected and the movies that meet the filters for each category.
        Map<String, List<Movie>> filtersToMovies = new HashMap<>();
        Map<String, List<String>> filtersToSelections = new HashMap<>();
        for (String category : FilterCategoryConstants.getCategories()) {
            filtersToMovies.put(category, testList);
            filtersToSelections.put(category, new ArrayList<>());
        }
        filtersToSelections.replace(FilterCategoryConstants.POPULARITY_RATING,
                Arrays.asList(new String[]{"5.0-5.9", "6.0-6.9"}));
        List<Movie> moviesToAdd = new ArrayList<>();
        moviesToAdd.add(new Movie("/jpXGGmLfY5q8t8c5nkqZjuHNtQ1.jpg", false,
                "By coincidence rather than by design, the Swiss chemist Albert Hofmann makes a sensational " +
                        "discovery in the spring of 1943. He realizes that he is dealing with a powerful molecule that "
                        + "will have an impact that reaches far beyond the scientific world. THE SUBSTANCE is an " +
                        "investigation into our troubled relationship with LSD, told from its beginnings to today.",
                "2011-11-17", Arrays.asList(new Integer[]{99}), 103195, "de",
                "The Substance: Albert Hofmann's LSD",null, (float)6.217, 22,
                false, (float)6.9));
        moviesToAdd.add(new Movie("/kH5bjhWoHe0f8J2yMKGoTEhcngY.jpg", false, "Isaac " +
                "Geldhart is a Holocaust survivor who, overcome by grief at the recent death of his wife, seems " +
                "determined to run his publishing firm into the ground by printing books that have no hope of " +
                "financial success. His son Aaron, who also works at the company, grows frustrated with Isaac's " +
                "emotional decline and attempts to take over the firm. The resulting crisis involves Isaac's other two " +
                "children, his daughter Sarah and his dying son Martin.", "1996-09-11",
                Arrays.asList(new Integer[]{18}), 168535, "en", "The Substance of Fire",
                "/nYs0F16VKAArkwMAqSVOhQek5se.jpg", (float)4.607, 3, false,
                (float)6.3));
        moviesToAdd.add(new Movie("/htEeIySOOKsY6GCsE5YNohqbL9a.jpg", false, "Jaiane now " +
                "lives in Brazil, while Aissa, a Mozambican sailor who has just arrived in the city, tries to have a " +
                "real experience on dry land. A story of unconventional passion follows.", "2021-10-06",
                Arrays.asList(new Integer[]{18}), 874929, "pt", "The Night's Substance",
                "/d2DXhcnn6BNo9S9o7tOkn1lA7vI.jpg", (float)0.425, 2, false, (float)5.5));
        moviesToAdd.add(new Movie("/1WImrQrFtUFEGJUaF8YfGlmEuSA.jpg", false, "Robert " +
                "Lachmann was a German-Jewish ethnomusicologist. In the 1930s, his radio show \"Oriental Music\" " +
                "explored the musical traditions of Palestine and included regular live performances by musicians from " +
                "different ethnic and religious groups. Inspired by Lachmann’s musicological studies, Palestinian artist " +
                "Jumana Manna travels through Israel and the Palestinian territories of today with recordings from the " +
                "programme. What do these songs sound like now when performed by Moroccan, Kurdish, or Yemenite Jews, " +
                "by Samaritans, members of the urban and rural Palestinian communities, Bedouins and Coptic " +
                "Christians?", "2015-09-17", Arrays.asList(new Integer[]{99, 10402, 36}), 389112,
                "ar","A Magical Substance Flows Into Me",
                "/cWE42cHbmk2z9u9NlAvUews36Ev.jpg", (float)0.446, 4, false, (float)6.8));
        filtersToMovies.replace(FilterCategoryConstants.POPULARITY_RATING, moviesToAdd);
        ClearAllFiltersInputData clearAllFiltersInputData = new ClearAllFiltersInputData(filtersToSelections,
                filtersToMovies, testList);

        //Create a test presenter that tests that the interactor works as we expect.
        ClearAllFiltersOutputBoundary clearAllFiltersPresenter = new ClearAllFiltersOutputBoundary() {
            @Override
            public void executeClearAllFilters(ClearAllFiltersOutputData clearAllFiltersOutputData) {
                //Test that there are no selected filters that correspond to any given category (i.e. that for every
                // filter category key, the corresponding list value is empty), and that for every filter category,
                //every movie is considered applicable, since by clearing filters there are no movies being filtered out
                //(i.e. check that for every filter category key, the corresponding list value is the same as the
                //original list
                //Note: filtersToMovies and filtersToSelections have the same keyset.
                for (String category : clearAllFiltersOutputData.getFiltersToMovies().keySet()) {
                    assert(clearAllFiltersOutputData.getFiltersToSelections().get(category).isEmpty());
                    assertEquals(testList, clearAllFiltersOutputData.getFiltersToMovies().get(category));
                }
            }
        };
        ClearAllFiltersInputBoundary interactor = new ClearAllFiltersInteractor(clearAllFiltersPresenter);
        interactor.execute(clearAllFiltersInputData);
    }
}
