package interface_adapter.filter_category;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The state for the filter category view.
 */
public class FilterCategoryState {

    private String categoryName;
    private String[] categoryOptions;
    private List<String> selectedOptions = new ArrayList<>();
    private List<Movie> originalList;
    private List<Movie> filteredList;

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

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public void addSelection(String option) {
        this.selectedOptions.add(option);
    }

    public void deleteSelection(String option) {
        this.selectedOptions.remove(option);
    }

    public void setFilteredList(List<Movie> filteredList) {
        this.filteredList = filteredList;
    }

    public List<Movie> getFilteredList() {
        return this.filteredList;
    }

    public void setOriginalList(List<Movie> originalList) {
        this.originalList = originalList;
    }

    public List<Movie> getOriginalList() {
        return this.originalList;
    }
}
