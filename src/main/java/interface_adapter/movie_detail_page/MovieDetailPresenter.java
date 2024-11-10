package interface_adapter.movie_detail_page;

import interface_adapter.ViewManagerModel;
import use_case.movie_detail.MovieDetailOutputBoundary;
import use_case.movie_detail.MovieDetailOutputData;

public class MovieDetailPresenter implements MovieDetailOutputBoundary {

    private MovieDetailViewModel movieDetailViewModel;
    private ViewManagerModel viewManagerModel;

    public MovieDetailPresenter(MovieDetailViewModel movieDetailViewModel, ViewManagerModel viewManagerModel) {
        this.movieDetailViewModel = movieDetailViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(MovieDetailOutputData movieDetailOutputData) {
        final MovieDetailState movieDetailState = movieDetailViewModel.getState();
        //Set the appropriate instance variable values for the Movie Detail State.
        movieDetailState.setTitle(movieDetailOutputData.getTitle());
        movieDetailState.setGenre(movieDetailOutputData.getGenre());
        movieDetailState.setYear(movieDetailOutputData.getYear());
//        movieDetailState.setDirector(movieDetailOutputData.getDirector());
 //       movieDetailState.setStreamingServices(movieDetailOutputData.getStreamingServices());
        movieDetailState.setPosterImagePath(movieDetailOutputData.getPosterImagePath());
        //Update the State in the Movie Detail View Model
        this.movieDetailViewModel.setState(movieDetailState);
        this.movieDetailViewModel.firePropertyChanged();
        //Update the View Manager Model.
        this.viewManagerModel.setState(movieDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView() {
        //note: there is currently no way for this use case to fail.
        ;
    }
}
