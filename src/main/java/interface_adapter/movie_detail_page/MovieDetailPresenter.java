package interface_adapter.movie_detail_page;

import interface_adapter.ViewManagerModel;
import use_case.movie_detail.MovieDetailOutputBoundary;
import use_case.movie_detail.MovieDetailOutputData;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieOutputBoundary;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieOutputData;

/**
 * The presenter for the Movie Detail use cases: Movie Detail Selection and Return to List From Movie Detail view
 */
public class MovieDetailPresenter implements MovieDetailOutputBoundary, ReturnToListFromMovieOutputBoundary {

    private MovieDetailViewModel movieDetailViewModel;
    private ViewManagerModel viewManagerModel;

    public MovieDetailPresenter(MovieDetailViewModel movieDetailViewModel, ViewManagerModel viewManagerModel) {
        this.movieDetailViewModel = movieDetailViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MovieDetailOutputData movieDetailOutputData) {
        final MovieDetailState movieDetailState = movieDetailViewModel.getState();
        //Set the appropriate instance variable values for the Movie Detail State.
        movieDetailState.setTitle(movieDetailOutputData.getTitle());
        movieDetailState.setGenre(movieDetailOutputData.getGenre());
        movieDetailState.setYear(movieDetailOutputData.getYear());
        movieDetailState.setDirector(movieDetailOutputData.getDirector());
        movieDetailState.setStreamingServices(movieDetailOutputData.getStreamingServices());
        movieDetailState.setPosterImagePath(movieDetailOutputData.getPosterImagePath());
        movieDetailState.setOriginView(movieDetailOutputData.getOriginView());
        //Update the State in the Movie Detail View Model
        this.movieDetailViewModel.setState(movieDetailState);
        this.movieDetailViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(movieDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void executeOriginView(ReturnToListFromMovieOutputData returnToListFromMovieOutputData) {
        viewManagerModel.setState(returnToListFromMovieOutputData.getOriginView());
        this.viewManagerModel.firePropertyChanged();
    }

}
