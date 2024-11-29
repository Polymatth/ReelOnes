package use_case.filter_application;

public class FilterCategoryConstants {
    public static String GENRE = "Genre";
    public static String DECADE_OF_RELEASE = "Decade Of Release";
    public static String STREAMING_SERVICES = "Streaming Services";
    public static String POPULARITY_RATING = "Popularity Rating";
    public static String[] getCategories() {
        return new String[]{GENRE, DECADE_OF_RELEASE, STREAMING_SERVICES, POPULARITY_RATING};
    }
}
