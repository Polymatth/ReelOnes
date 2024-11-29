package entity;

public class UserList extends MovieList {
    public UserList(String userId, String listName, boolean isPublic) {
        super(userId, listName, isPublic);
    }

    @Override
    public boolean isEditable() {
        return true;
    }
}