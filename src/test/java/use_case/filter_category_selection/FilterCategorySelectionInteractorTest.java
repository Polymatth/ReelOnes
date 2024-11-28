package use_case.filter_category_selection;

import app.AppConfig;
import entity.Movie;
import org.junit.jupiter.api.Test;
import use_case.filter_application.*;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.search_movie.SearchMovieDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilterCategorySelectionInteractorTest {

    @Test
    void FilterCategorySelectionSuccess() {
        AppConfig config = new AppConfig();
        SearchMovieDataAccessInterface dataAccessInterface = config.getMovieDataAccess();
        //Set the category being selected
        String categoryName = "Popularity Ratings";
        //Create the original MovieList.
        List<Movie> testList = dataAccessInterface.searchMoviesByQuery("The Substance");
        //Set the category options.
        String[] categoryOptions = new String[]{"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"};
        //Set the selected options.
        List<String> optionsSelected = Arrays.asList(new String[]{"5.0-5.9", "6.0-6.9"});
        //Set the current filtered list.
        List<Movie> testFilteredList = new ArrayList<>();
        testFilteredList.add(new Movie("/jpXGGmLfY5q8t8c5nkqZjuHNtQ1.jpg", false,
                "By coincidence rather than by design, the Swiss chemist Albert Hofmann makes a sensational " +
                        "discovery in the spring of 1943. He realizes that he is dealing with a powerful molecule that "
                        + "will have an impact that reaches far beyond the scientific world. THE SUBSTANCE is an " +
                        "investigation into our troubled relationship with LSD, told from its beginnings to today.",
                "2011-11-17", Arrays.asList(new Integer[]{99}), 103195, "de",
                "The Substance: Albert Hofmann's LSD",null, (float)6.217, 22,
                false, (float)6.9));
        testFilteredList.add(new Movie("/kH5bjhWoHe0f8J2yMKGoTEhcngY.jpg", false, "Isaac " +
                "Geldhart is a Holocaust survivor who, overcome by grief at the recent death of his wife, seems " +
                "determined to run his publishing firm into the ground by printing books that have no hope of " +
                "financial success. His son Aaron, who also works at the company, grows frustrated with Isaac's " +
                "emotional decline and attempts to take over the firm. The resulting crisis involves Isaac's other two " +
                "children, his daughter Sarah and his dying son Martin.", "1996-09-11",
                Arrays.asList(new Integer[]{18}), 168535, "en", "The Substance of Fire",
                        "/nYs0F16VKAArkwMAqSVOhQek5se.jpg", (float)4.607, 3, false,
                (float)6.3));
        testFilteredList.add(new Movie("/htEeIySOOKsY6GCsE5YNohqbL9a.jpg", false, "Jaiane now " +
                "lives in Brazil, while Aissa, a Mozambican sailor who has just arrived in the city, tries to have a " +
                "real experience on dry land. A story of unconventional passion follows.", "2021-10-06",
                Arrays.asList(new Integer[]{18}), 874929, "pt", "The Night's Substance",
                "/d2DXhcnn6BNo9S9o7tOkn1lA7vI.jpg", (float)0.425, 2, false, (float)5.5));
        testFilteredList.add(new Movie("/1WImrQrFtUFEGJUaF8YfGlmEuSA.jpg", false, "Robert " +
                "Lachmann was a German-Jewish ethnomusicologist. In the 1930s, his radio show \"Oriental Music\" " +
                "explored the musical traditions of Palestine and included regular live performances by musicians from " +
                "different ethnic and religious groups. Inspired by Lachmann’s musicological studies, Palestinian artist " +
                "Jumana Manna travels through Israel and the Palestinian territories of today with recordings from the " +
                "programme. What do these songs sound like now when performed by Moroccan, Kurdish, or Yemenite Jews, " +
                "by Samaritans, members of the urban and rural Palestinian communities, Bedouins and Coptic " +
                "Christians?", "2015-09-17", Arrays.asList(new Integer[]{99, 10402, 36}), 389112,
                "ar","A Magical Substance Flows Into Me",
                "/cWE42cHbmk2z9u9NlAvUews36Ev.jpg", (float)0.446, 4, false, (float)6.8));
        FilterCategorySelectionInputData filterCategorySelectionInputData = new FilterCategorySelectionInputData(
                categoryName, categoryOptions, testList, testFilteredList, optionsSelected);

        //Create a test presenter that tests that the interactor works as we expect.
        FilterCategorySelectionOutputBoundary filterCategorySelectionPresenter = new
                FilterCategorySelectionOutputBoundary() {
            @Override
            public void prepareSuccessView(FilterCategorySelectionOutputData filterCategorySelectionOutputData) {
                // Test that the filter category name, category options, selected options, the original movie list, and
                // the current filtered movie list in the Output Data object are the same as the filter category name,
                // options, selected options, original movie list, and current filtered movie list in the Input Data
                // object.
                assertEquals(categoryName, filterCategorySelectionOutputData.getCategoryName());
                assertEquals(categoryOptions, filterCategorySelectionOutputData.getCategoryOptions());
                assertEquals(testList, filterCategorySelectionOutputData.getOriginalList());
                assertEquals(testFilteredList, filterCategorySelectionOutputData.getFilteredList());
                assertEquals(optionsSelected, filterCategorySelectionOutputData.getSelectedOptions());
            }
        };
        FilterCategorySelectionInputBoundary interactor = new
                FilterCategorySelectionInteractor(filterCategorySelectionPresenter) {
        };
        interactor.execute(filterCategorySelectionInputData);
    }
}
