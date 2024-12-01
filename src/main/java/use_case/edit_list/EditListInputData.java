package use_case.edit_list;

/**
 * The input data for the Edit List Use Case.
 */
public class EditListInputData {
    private final String username;
    private final String oldListName;
    private final String newListName;

    public EditListInputData(String username, String oldListName, String newListName) {
        this.username = username;
        this.oldListName = oldListName;
        this.newListName = newListName;
    }

    public String getUsername() {
        return username;
    }

    public String getOldListName() {
        return oldListName;
    }

    public String getNewListName() {
        return newListName;
    }
}