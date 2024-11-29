package view;

import interface_adapter.filter_categories.FilterCategoriesController;
import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.filter_category.FilterCategoryViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

public class FilterCategoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter category page";
    private String categoryName;
    private final FilterCategoryViewModel filterCategoryViewModel;
    private String[] categoryOptions;
    private List<String> optionsSelected;

    private FilterCategoryController filterCategoryController;
    private FilterCategoriesController filterCategoriesController;

    private final JPanel titlePanel = new JPanel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel otherButtons = new JPanel();
    private final JPanel allTogether = new JPanel();

    public FilterCategoryView(FilterCategoryViewModel filterCategoryViewModel) {
      this.filterCategoryViewModel = filterCategoryViewModel;
      this.filterCategoryViewModel.addPropertyChangeListener(this);

      this.filterCategoryViewModel.addPropertyChangeListener(evt -> {
          this.add(new JLabel("Filter Applied"), 0);
          System.out.println(evt);
      });

      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      if (this.categoryName != null) {
          displayOptions();
      }
      else {
          this.add(new JPanel());
      }
    }

    private void displayOptions() {
        SwingWorker<Void, Void> applyFiltersWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    filterCategoryController.executeFilterApplication(categoryName, optionsSelected,
                            Arrays.asList(categoryOptions), filterCategoryViewModel.getState().getOriginalList());
                } catch (Exception e) {
                    System.out.println("Fail silently");
                    System.out.println(e);
                }
                return null;
            }
        };
        JPanel container = this;
        clearPanels();
        JLabel title = new JLabel(this.categoryName, SwingConstants.CENTER);
        titlePanel.add(title);
        this.add(titlePanel);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        for (String option : this.categoryOptions) {
            JCheckBox addedFilter = new JCheckBox(option);
            if (this.optionsSelected.contains(option)) {
                addedFilter.setSelected(true);
            }
            addedFilter.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (addedFilter.isSelected()) {
                                optionsSelected.add(option);
                            } else {
                                optionsSelected.remove(option);
                            }
                        }
                    }
            );
            JPanel filterPanel = new JPanel();
            filterPanel.add(addedFilter);
            optionsPanel.add(filterPanel);
        }
        this.add(optionsPanel);
        JButton apply = new JButton("Apply Filters");
        otherButtons.add(apply);
        JButton back = new JButton("Back to All Filters");
        otherButtons.add(back);
        apply.addActionListener(actionEvent -> applyFiltersWorker.execute());
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterCategoriesController.executeReturnToFilterCategoriesView(categoryName, optionsSelected,
                                filterCategoryViewModel.getState().getFilteredList());
                    }
                }
        );
        this.add(otherButtons);
        allTogether.setLayout(new BoxLayout(allTogether, BoxLayout.PAGE_AXIS));
        allTogether.add(titlePanel);
        allTogether.add(optionsPanel);
        allTogether.add(otherButtons);
        this.add(allTogether);
    }

    private void clearPanels() {
        titlePanel.removeAll();
        optionsPanel.removeAll();
        otherButtons.removeAll();
        allTogether.removeAll();
        this.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayOptions();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.categoryName = this.filterCategoryViewModel.getState().getCategoryName();
        this.categoryOptions = this.filterCategoryViewModel.getState().getCategoryOptions();
        this.optionsSelected = this.filterCategoryViewModel.getState().getSelectedOptions();
        displayOptions();
    }

  public String getViewName() {
    return this.viewName;
  }

  public void setFilterCategoryController(FilterCategoryController filterCategoryController) {
        this.filterCategoryController = filterCategoryController;
  }

  public void setFilterCategoriesController(FilterCategoriesController filterCategoriesController) {
        this.filterCategoriesController = filterCategoriesController;
  }
}