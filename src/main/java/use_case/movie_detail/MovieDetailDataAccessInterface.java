package use_case.movie_detail;

import java.util.List;
import java.util.Map;

/**
 * The DAO for the movie detail selection use case.
 */
public interface MovieDetailDataAccessInterface {

    List<String> getGenres(List<Integer> genreIDs);

    String getDirector(int movieID);

    List<String> getStreamingServices(int movieID);

}
