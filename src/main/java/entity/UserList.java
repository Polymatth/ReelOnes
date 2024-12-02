package entity;

import java.util.List;

public class UserList extends MovieList {
    public UserList(String userId, String listName, boolean isPublic) {
        super(userId, listName, isPublic);
    }

    public UserList(String userId, String listName, boolean isPublic, List<Movie> movieLists) {
        super(userId, listName, isPublic, movieLists);
    }




    @Override
    public boolean isEditable() {
        return true;
    }
}