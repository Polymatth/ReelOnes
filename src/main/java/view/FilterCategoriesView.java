package view;

import interface_adapter.filter_categories.FilterCategoriesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FilterCategoriesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter categories page";
    private final FilterCategoriesViewModel filterCategoriesViewModel;

    //private FilterCategoryController filterCategoryController;

    private JButton apply;
    private JButton clear;

    public FilterCategoriesView(FilterCategoriesViewModel filterCategoriesViewModel) {
        this.filterCategoriesViewModel = filterCategoriesViewModel;
        this.filterCategoriesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Filters");
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);

        JButton genre = new JButton("Genre");
        genre.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(genre);
        JButton decade = new JButton("Decade of Release");
        decade.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(decade);
        JButton streaming = new JButton("Streaming Services");
        streaming.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(streaming);
        JButton popularity = new JButton("Popularity");
        popularity.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(popularity);

        apply = new JButton("Apply Filters");
        this.add(apply);
        clear = new JButton("Clear All Filters");
        this.add(clear);
        JButton back = new JButton("Back");
        this.add(back);

        genre.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
//                        String[] genresListed = {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
//                                "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction"};
//                        this.filterCategoryController.execute("Genre", genresListed);
                    }
                }
        );

        decade.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
//                        String[] decadeListed = {"2020S", "2010s", "2000s", "1990s", "1980s", "1970s", "1960s",
//                                "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"};
//                        this.filterCategoryController.execute("Decade of Release", decadeListed);
                    }
                }
        );

        streaming.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
//                        String[] servicesListed = {"Netflix", "Amazon Prime", "Disney+", "Iqiyi", "Apple TV+", "Max",
//                                "Other"};
//                        this.filterCategoryController.execute("Streaming Services", servicesListed);
                    }
                }
        );

        popularity.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
//                        String[] ratingsList = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69",
//                                "70-79", "80-89", "90-100"};
//                        this.filterCategoryController.execute("Popularity Ratings", ratingsList);
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void propertyChange(PropertyChangeEvent evt) {}

    public String getViewName() {
        return this.viewName;
    }

  //  public void setFilterCategoryController(FilterCategoryController filterCategoryController) {
    //    this.filterCategoryController = filterCategoryController;
    }

