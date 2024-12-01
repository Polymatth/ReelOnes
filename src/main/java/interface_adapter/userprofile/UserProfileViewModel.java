package interface_adapter.userprofile;
import data_access.InMemoryMovieListDataAccessObject;
import entity.MovieList;
import entity.User;
import interface_adapter.ViewModel;
import interface_adapter.change_favorites.ChangeFavoritesPresenter;
import interface_adapter.get_currentuser.GetCurrentUserController;
import interface_adapter.login.LoginState;
import use_case.get_currentuser.GetCurrentUserOutputData;
import use_case.movie_list.MovieListDataAccessInterface;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListInteractor;

import java.util.ArrayList;
import java.util.List;

import use_case.get_currentuser.GetCurrentUserInputBoundary;
import use_case.get_currentuser.GetCurrentUserOutputData;

public class UserProfileViewModel  extends ViewModel<UserProfileState> {

    private MovieListInputBoundary movieListInteractor;
    private MovieListDataAccessInterface movieListDataAccessInterface;
    private List<MovieList> userLists;
    private GetCurrentUserController getCurrentUserController;


    public UserProfileViewModel() {
        super("userprofile");
        setState(new UserProfileState());
        this.movieListDataAccessInterface = new InMemoryMovieListDataAccessObject();
        this.movieListInteractor = new MovieListInteractor(movieListDataAccessInterface);
        this.userLists = new ArrayList<>(movieListInteractor.getUserListsForUser(getState().getUsername()));
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
        System.out.println(getState().getUsername());
        firePropertyChanged("loggedin");
    }


    public void createNewList(String userId, String listName, boolean isPublic) {
        movieListInteractor.createNewList(userId, listName, isPublic);
        refreshUserLists();
    }

    public List<MovieList> getUserLists() {
        return movieListInteractor.getUserListsForUser(getState().getUsername());
    }

    public void addNewList(MovieList newList) {
        this.userLists.add(newList);
        firePropertyChanged("userLists"); // Notify listeners
    }

    public boolean movieListExists(String listName) {
        return movieListInteractor.movieListExists(listName);
    }

    private void refreshUserLists() {
        this.userLists = new ArrayList<>(movieListInteractor.getUserListsForUser(getState().getUsername()));
        firePropertyChanged("userLists"); // Notify listeners
    }


    @Override
    public String getViewName() {
        return "userprofile";
    }


    public void updateFavorites(String newFavMovie, String newFavDirector) {
        getState().setFavMovie(newFavMovie);
        getState().setFavDirector(newFavDirector);
        firePropertyChanged("favorites");
    }



}
