package interface_adapter.open_list;

import entity.Movie;
import entity.MovieList;
import entity.User;
import interface_adapter.ViewModel;
import interface_adapter.get_currentuser.GetCurrentUserController;
import use_case.get_currentuser.GetCurrentUserOutputData;

import java.util.List;

public class OpenListViewModel extends ViewModel<OpenListState> {

    private GetCurrentUserController getCurrentUserController;

    public OpenListViewModel() {
        super("open list view");
        setState(new OpenListState());
    }

    public void loadUserMovies() {
        GetCurrentUserOutputData currentUser = getCurrentUserController.execute();
        List<MovieList> movieListLists = currentUser.getMovieLists();
        String listName = getState().getListName();
        for (MovieList movieList: movieListLists) {
            if (movieList.getListName().equals(listName)) {
                System.out.println("movie list"+ movieList);
                getState().setMovies(movieList.getMovies());
            }
        }
        firePropertyChanged("movies");
    }

    public void setGetCurrentUserController(GetCurrentUserController getCurrentUserController){ this.getCurrentUserController = getCurrentUserController;}


}