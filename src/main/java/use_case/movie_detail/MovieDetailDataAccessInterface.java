package use_case.movie_detail;

import java.util.Map;

public interface MovieDetailDataAccessInterface {

    Map<Integer, String> getGenres();

    String getDirector(int movieID);

}
