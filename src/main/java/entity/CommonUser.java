package entity;

import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private String favMovie;
    private String favDirector;
    private List<MovieList> movieLists;

    public CommonUser(String name, String password,String favMovie, String favDirector, List<MovieList> movieLists) {
        this.name = name;
        this.password = password;
        this.favMovie = favMovie;
        this.favDirector = favDirector;
        this.movieLists = movieLists;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getFavMovie() {return favMovie;}

    public String getFavDirector() {return favDirector;
    }

    @Override
    public List<MovieList> getMovieLists() {
        return this.movieLists;
    }

    @Override
    public String toString() {
        return "CommonUser{"
                + "name='" + name + '\''
                + ", favoriteMovie=" + favMovie
                + ", favoriteDirector=" + favDirector
                + '}';
    }




}
