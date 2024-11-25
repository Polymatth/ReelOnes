package view;

import interface_adapter.filter_categories.FilterCategoriesViewModel;
import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.filter_category.FilterCategoryState;
import interface_adapter.filter_category.FilterCategoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FilterCategoriesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "filter categories page";
    private final FilterCategoriesViewModel filterCategoriesViewModel;

    private FilterCategoryController filterCategoryController;

    private JButton apply;
    private JButton clear;

    public FilterCategoriesView(FilterCategoriesViewModel filterCategoriesViewModel) {
        this.filterCategoriesViewModel = filterCategoriesViewModel;
        this.filterCategoriesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Filters", SwingConstants.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);

        JButton genre = new JButton("Genre");
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(genre);
        JButton decade = new JButton("Decade of Release");
        decade.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        decade.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(decade);
        JButton streaming = new JButton("Streaming Services");
        streaming.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        streaming.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(streaming);
        JButton popularity = new JButton("Popularity");
        popularity.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        popularity.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(popularity);

        JPanel otherButtons = new JPanel();
        otherButtons.setLayout(new BoxLayout(otherButtons, BoxLayout.LINE_AXIS));
        otherButtons.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        otherButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        apply = new JButton("Apply Filters");
        otherButtons.add(apply);
        clear = new JButton("Clear All Filters");
        otherButtons.add(clear);
        JButton back = new JButton("Back");
        otherButtons.add(back);

        this.add(otherButtons);

        genre.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] genresListed = {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
                                "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction"};
                        filterCategoryController.executeFilterCategorySelection("Genre", genresListed,
                                filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get("Genre"),
                                filterCategoriesViewModel.getState().getSelectedFilters().get("Genre"));
                    }
                }
        );

        decade.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] decadeListed = {"2020s", "2010s", "2000s", "1990s", "1980s", "1970s", "1960s",
                                "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"};
                        filterCategoryController.executeFilterCategorySelection("Decade of Release",
                                decadeListed, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get("Decade of Release"),
                                filterCategoriesViewModel.getState().getSelectedFilters().get("Decade of Release"));
                    }
                }
        );

        streaming.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] servicesListed = {"Netflix", "Amazon Prime", "Disney+", "Iqiyi", "Apple TV+", "Max",
                                "Other"};
                        filterCategoryController.executeFilterCategorySelection("Streaming Services",
                                servicesListed, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get("Streaming Services"),
                                filterCategoriesViewModel.getState().getSelectedFilters().get("Streaming Services"));
                    }
                }
        );

        popularity.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] ratingsList = {"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"};
                        filterCategoryController.executeFilterCategorySelection("Popularity Ratings",
                                ratingsList, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get("Rating"),
                                filterCategoriesViewModel.getState().getSelectedFilters().get("Rating"));
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void propertyChange(PropertyChangeEvent evt) {
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setFilterCategoryController(FilterCategoryController filterCategoryController) {
        this.filterCategoryController = filterCategoryController;
    }
}

