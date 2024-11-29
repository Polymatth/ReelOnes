package interface_adapter.movie_list;

import entity.Movie;
import entity.MovieList;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.movie_detail_page.MovieDetailState;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListOutputBoundary;
import use_case.movie_list.MovieListOutputData;

import java.util.List;

public class MovieListPresenter implements MovieListOutputBoundary {

    private MovieListViewModel movieListViewModel;
    private ViewManagerModel viewManagerModel;

    public MovieListPresenter(MovieListViewModel movieListViewModel, ViewManagerModel viewManagerModel) {
        this.movieListViewModel = movieListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(MovieListOutputData movieListOutputData) {
        final MovieListState movieListState = movieListViewModel.getState();

        movieListState.setName(movieListOutputData.getName());

        this.movieListViewModel.setState(movieListState);
        this.movieListViewModel.firePropertyChanged();

        this.viewManagerModel.setState(movieListViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        final MovieListState movieListState = movieListViewModel.getState();
        movieListState.setMovieListError(error);
        movieListViewModel.firePropertyChanged();
    }

}