package interface_adapter.movie_detail_page;

import entity.Movie;
import interface_adapter.ViewModel;
import interface_adapter.get_currentuser.GetCurrentUserController;
import use_case.get_currentuser.GetCurrentUserOutputData;
import use_case.movie_detail.MovieDetailOutputData;

/**
 * The view model for the movie detail view.
 */
public class MovieDetailViewModel extends ViewModel<MovieDetailState> {
    private GetCurrentUserController getCurrentUserController;

    public MovieDetailViewModel() {
        super("movie detail page");
        setState(new MovieDetailState());

    }

    public void setGetCurrentUserController(GetCurrentUserController getCurrentUserController) {
        this.getCurrentUserController = getCurrentUserController;
    }

    public void loadCurrentUser() {
        GetCurrentUserOutputData user = getCurrentUserController.execute();
        getState().setUsername(user.getUsername());
        getState().setPassword(user.getPassword());
        getState().setFavMovie(user.getFavMovie());
        getState().setFavDirector(user.getFavDirector());
        getState().setMovieListsList(user.getMovieLists());
        firePropertyChanged("currentuser");
    }
}
