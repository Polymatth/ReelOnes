package use_case.filter_application;

import java.util.List;

/**
 * The DAO for the filter application use case.
 */
public interface FilterApplicationDataAccessInterface {

    List<String> getGenres(List<Integer> genreIDs);

    List<String> getStreamingServices(int movieID);
}
