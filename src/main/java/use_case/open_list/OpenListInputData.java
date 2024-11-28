package use_case.open_list;

public class OpenListInputData {
    private final String listName;

    public OpenListInputData(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }
}