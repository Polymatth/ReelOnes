package entity;

public class RecommendedList extends MovieList {
    public RecommendedList(String userId) {
        super(userId, "Recommended Movies", false);
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public void setListName(String listName) {
        throw new UnsupportedOperationException("Cannot rename RecommendedList");
    }
}