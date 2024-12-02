package use_case.open_list;

/**
 * The Input Data for the Open List Use Case.
 */
public class OpenListInputData {
    private final String listName;

    public OpenListInputData(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }
}