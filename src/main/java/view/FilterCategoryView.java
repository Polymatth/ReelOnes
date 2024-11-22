package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class FilterCategoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter category view";
    private String categoryName;
  //  private final FilterCategoryViewModel filterCategoryViewModel;
    private String[] categoryOptions;

    public FilterCategoryView(String category, String[] optionsList) {
      this.categoryName = category;
      this.categoryOptions = optionsList;
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      JLabel title = new JLabel(this.categoryName);
      this.add(title);

      JScrollPane filterBox = new JScrollPane();
      JList filterList = new JList<>();
      filterList.setLayout(new BoxLayout(filterList, BoxLayout.PAGE_AXIS));

      for (String option : this.categoryOptions) {
        JCheckBox addedFilter = new JCheckBox(option);
        filterList.add(addedFilter);
        addedFilter.addActionListener(
                new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {

                  }
                }
        );
      }
      filterBox.add(filterList);
      this.add(filterBox);

      JButton back = new JButton("Back to All Filters");
      this.add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
