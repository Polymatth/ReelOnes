package view;

import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.filter_category.FilterCategoryViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;

public class FilterCategoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter category view";
    private String categoryName;
    private final FilterCategoryViewModel filterCategoryViewModel;
    private String[] categoryOptions;
    private List<String> optionsSelected;
    private FilterCategoryController filterCategoryController;

    public FilterCategoryView(FilterCategoryViewModel filterCategoryViewModel) {
      this.filterCategoryViewModel = filterCategoryViewModel;
      this.categoryName = this.filterCategoryViewModel.getState().getCategoryName();
      this.categoryOptions = this.filterCategoryViewModel.getState().getCategoryOptions();
      this.optionsSelected = this.filterCategoryViewModel.getState().getSelectedOptions();
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      JLabel title = new JLabel(this.categoryName, SwingConstants.CENTER);
      this.add(title);

      for (String option : this.categoryOptions) {
        JCheckBox addedFilter = new JCheckBox(option);
        this.add(addedFilter);
        if (this.optionsSelected.contains(option)) {
            addedFilter.setSelected(true);
          }
        addedFilter.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if (addedFilter.isSelected()) {
                          optionsSelected.add(option);
                      }
                      else {
                          optionsSelected.remove(option);
                      }
                  }
                }
        );
      }
      JButton apply = new JButton("Apply Filters");
      this.add(apply);
      apply.addActionListener(
              new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      filterCategoryController.executeFilterApplication(categoryName, optionsSelected,
                              Arrays.asList(categoryOptions), filterCategoryViewModel.getState().getOriginalList());
                  }
              }
      );
      JButton back = new JButton("Back to All Filters");
      this.add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

  public String getViewName() {
    return this.viewName;
  }

  public void setFilterCategoryController(FilterCategoryController filterCategoryController) {
        this.filterCategoryController = filterCategoryController;
  }
}