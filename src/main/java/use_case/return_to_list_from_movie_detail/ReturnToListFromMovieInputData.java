package use_case.return_to_list_from_movie_detail;

public class ReturnToListFromMovieInputData {

    private String originView;

    public ReturnToListFromMovieInputData(String originView) {
        this.originView = originView;
    }

    public String getOriginView() {
        return this.originView;
    }
}
