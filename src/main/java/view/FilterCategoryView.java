package view;

import interface_adapter.filter_category.FilterCategoryViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Filter;

public class FilterCategoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter category view";
    private String categoryName;
    private final FilterCategoryViewModel filterCategoryViewModel;
    private String[] categoryOptions;

    public FilterCategoryView(FilterCategoryViewModel filterCategoryViewModel) {
      this.filterCategoryViewModel = filterCategoryViewModel;
      this.categoryName = this.filterCategoryViewModel.getState().getCategoryName();
      this.categoryOptions = this.filterCategoryViewModel.getState().getCategoryOptions();
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      JLabel title = new JLabel(this.categoryName, SwingConstants.CENTER);
      this.add(title);

      for (String option : this.categoryOptions) {
        JCheckBox addedFilter = new JCheckBox(option);
        this.add(addedFilter);
        addedFilter.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
                }
        );
      }
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
}
