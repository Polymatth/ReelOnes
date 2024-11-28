package interface_adapter.userprofile;
import data_access.InMemoryMovieListDataAccessObject;
import entity.MovieList;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import use_case.movie_list.MovieListDataAccessInterface;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListInteractor;

import java.util.ArrayList;
import java.util.List;

public class UserProfileViewModel  extends ViewModel<UserProfileState> {

    private MovieListInputBoundary movieListInteractor;
    private MovieListDataAccessInterface movieListDataAccessInterface;
    private List<MovieList> userLists;

    public UserProfileViewModel() {
        super("userprofile");
        setState(new UserProfileState());
        this.movieListDataAccessInterface = new InMemoryMovieListDataAccessObject();
        this.movieListInteractor = new MovieListInteractor(movieListDataAccessInterface);
        this.userLists = new ArrayList<>(movieListInteractor.getUserListsForUser(getState().getUsername()));
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
}
