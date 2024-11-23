package interface_adapter.filter_category;

import java.util.ArrayList;
import java.util.List;

public class FilterCategoryState {

    private String categoryName;
    private String[] categoryOptions;
    private List<String> selectedOptions = new ArrayList<>();

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getCategoryOptions(){
        return this.categoryOptions;
    }

    public void setCategoryOptions(String[] options) {
        this.categoryOptions = options;
    }

    public List<String> getSelectedOptions() {
        return this.selectedOptions;
    }

    public void addSelection(String option) {
        this.selectedOptions.add(option);
    }

    public void deleteSelection(String option) {
        this.selectedOptions.remove(option);
    }
}
