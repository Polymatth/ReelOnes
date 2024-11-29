package interface_adapter.open_list;

import entity.Movie;

import java.util.List;

import interface_adapter.ViewModel;

public class OpenListViewModel extends ViewModel<OpenListState> {
    private String listName;
    private List<Movie> movies;

    public OpenListViewModel() {
        super("open list view");
        setState(new OpenListState());
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}