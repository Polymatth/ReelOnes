package use_case.return_to_list_from_movie_detail;

/**
 * The output data for the return to list from movie detail view use case
 */
public class ReturnToListFromMovieOutputData {

    private String originView;

    public ReturnToListFromMovieOutputData(String originView) {
        this.originView = originView;
    }

    public String getOriginView() {
        return this.originView;
    }
}
