package view;

import interface_adapter.filter_categories.FilterCategoriesController;
import interface_adapter.filter_categories.FilterCategoriesState;
import interface_adapter.filter_categories.FilterCategoriesViewModel;
import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.filter_category.FilterCategoryState;
import interface_adapter.filter_category.FilterCategoryViewModel;
import use_case.filter_application.FilterCategoryConstants;

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
    private FilterCategoriesController filterCategoriesController;

    private JButton apply;
    private JButton clear;

    public FilterCategoriesView(FilterCategoriesViewModel filterCategoriesViewModel) {
        this.filterCategoriesViewModel = filterCategoriesViewModel;
        this.filterCategoriesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Filters", SwingConstants.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(title);

        JButton genre = new JButton(FilterCategoryConstants.GENRE);
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        genre.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        this.add(genre);
        JButton decade = new JButton(FilterCategoryConstants.DECADE_OF_RELEASE);
        decade.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        decade.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(decade);
        JButton streaming = new JButton(FilterCategoryConstants.STREAMING_SERVICES);
        streaming.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        streaming.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(streaming);
        JButton popularity = new JButton(FilterCategoryConstants.POPULARITY_RATING);
        popularity.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        popularity.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(popularity);

        JPanel otherButtons = new JPanel();
        otherButtons.setLayout(new BoxLayout(otherButtons, BoxLayout.LINE_AXIS));
        otherButtons.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        otherButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        clear = new JButton("Clear All Filters");
        otherButtons.add(clear);
        JButton back = new JButton("See List");
        otherButtons.add(back);

        this.add(otherButtons);

        genre.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] genresListed = {"Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama",
                                "Family", "Fantasy", "History", "Horror", "Music", "Mystery", "Romance", "Science Fiction"};
                        filterCategoryController.executeFilterCategorySelection(FilterCategoryConstants.GENRE, genresListed,
                                filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get(FilterCategoryConstants.GENRE),
                                filterCategoriesViewModel.getState().getFiltersToSelections().get(FilterCategoryConstants.GENRE));
                    }
                }
        );

        decade.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] decadeListed = {"2020s", "2010s", "2000s", "1990s", "1980s", "1970s", "1960s",
                                "1950s", "1940s", "1930s", "1920s", "1910s", "1900s"};
                        filterCategoryController.executeFilterCategorySelection(FilterCategoryConstants.DECADE_OF_RELEASE,
                                decadeListed, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get(FilterCategoryConstants.DECADE_OF_RELEASE),
                                filterCategoriesViewModel.getState().getFiltersToSelections().get(FilterCategoryConstants.DECADE_OF_RELEASE));
                    }
                }
        );

        streaming.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] servicesListed = {"Netflix", "Amazon Prime Video", "Disney Plus", "Iqiyi", "Apple TV",
                                "Max"};
                        filterCategoryController.executeFilterCategorySelection(FilterCategoryConstants.STREAMING_SERVICES,
                                servicesListed, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get(FilterCategoryConstants.STREAMING_SERVICES),
                                filterCategoriesViewModel.getState().getFiltersToSelections().get(FilterCategoryConstants.STREAMING_SERVICES));
                    }
                }
        );

        popularity.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String[] ratingsList = {"0.0-0.9", "1.0-1.9", "2.0-2.9", "3.0-3.9", "4.0-4.9", "5.0-5.9",
                                "6.0-6.9", "7.0-7.9", "8.0-8.9", "9.0-10.0"};
                        filterCategoryController.executeFilterCategorySelection(FilterCategoryConstants.POPULARITY_RATING,
                                ratingsList, filterCategoriesViewModel.getState().getOriginalMovieList(),
                                filterCategoriesViewModel.getState().getFilterToMovies().get(FilterCategoryConstants.POPULARITY_RATING),
                                filterCategoriesViewModel.getState().getFiltersToSelections().get(FilterCategoryConstants.POPULARITY_RATING));
                    }
                }
        );

        clear.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterCategoriesController.executeClearAllFilters(filterCategoriesViewModel.getState()
                                .getFiltersToSelections(), filterCategoriesViewModel.getState().getFilterToMovies(),
                                filterCategoriesViewModel.getState().getOriginalMovieList());
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterCategoriesController.executeReturnToList(filterCategoriesViewModel.getState()
                                        .getFiltersToSelections(),
                                filterCategoriesViewModel.getState().getFilterToMovies(),
                                filterCategoriesViewModel.getState().getListView());
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

    public void setFilterCategoriesController(FilterCategoriesController filterCategoriesController) {
        this.filterCategoriesController = filterCategoriesController;
    }
}

