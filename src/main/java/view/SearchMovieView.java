package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import entity.Movie;
import interface_adapter.filter_categories.FilterCategoriesController;
import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.search_movie.SearchMovieController;
import interface_adapter.search_movie.SearchMovieState;
import interface_adapter.search_movie.SearchMovieViewModel;
import use_case.search_movie.SearchMovieOutputData;


/**
 * The View for when the user is logging into the program.
 * --text: rgb(233, 232, 236);
 * --background: rgb(11, 5, 28);
 * --primary: rgb(186, 183, 237);
 * --secondary: rgb(33, 19, 44);
 * --accent: rgb(232, 149, 112);
 */
public class SearchMovieView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "movie search";
    private final SearchMovieViewModel searchMovieViewModel;
    private final JLabel searchErrorField = new JLabel();
    private SearchMovieOutputData searchMovieOutputData;

    private SearchMovieController searchMovieController;
    private MovieDetailController movieDetailController;
    private FilterCategoriesController filterCategoriesController;

    private Color textColor = new Color(233, 232, 236);
    private Color backgroundColor = new Color(11, 5, 28);
    private Color primaryColor = new Color(186, 183, 237);
    private Color secondaryColor = new Color(33, 19, 44);
    private Color accent = new Color(232, 149, 112);


    public SearchMovieView(SearchMovieViewModel searchMovieViewModel) throws IOException {

        this.searchMovieViewModel = searchMovieViewModel;
        this.searchMovieViewModel.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(secondaryColor);
    }

    private void updateResults(SearchMovieViewModel searchMovieViewModel) throws IOException {
        this.removeAll();
        final JLabel title = new JLabel("Search Results", JLabel.CENTER);
        final JButton filters = new JButton("Filters");
        filters.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterCategoriesController.goToFilterCategoriesView(searchMovieViewModel.getState().getMovies(),
                                searchMovieViewModel.getState().getFiltersToMovies(),
                                searchMovieViewModel.getState().getFiltersToSelections(),
                                searchMovieViewModel.getViewName());
                    }
                }
        );
        title.setForeground(textColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);
        this.add(filters);
        //TODO: when you return to a list after looking at filters, you see double of the list. Clear Jpanel.
        final JPanel moviePosters = new JPanel();
        moviePosters.setLayout(new FlowLayout());
        moviePosters.setBackground(backgroundColor);
        if (searchMovieViewModel.getState().moviesToDisplay() != null) {
            for (Movie movie : searchMovieViewModel.getState().moviesToDisplay()) {
                final JButton result = new JButton(movie.getTitle());
                URL url = new URL("https://image.tmdb.org/t/p/w500"+ movie.getPosterPath());
                Image image = ImageIO.read(url).getScaledInstance(160, 240, Image.SCALE_FAST);
                final Icon resultIcon = new ImageIcon(image);
                result.setLabel(movie.getTitle());
                result.setIcon(resultIcon);
                result.setVerticalTextPosition(SwingConstants.BOTTOM);
                result.setHorizontalTextPosition(SwingConstants.CENTER);
                result.setBackground(primaryColor);
                result.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(result)) {
                                    movieDetailController.execute(movie, viewName);
                                }
                            }
                        }
                );
                moviePosters.add(result);
            }
        }
        this.add(moviePosters);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            updateResults(searchMovieViewModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFields(SearchMovieState state) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchMovieOutputData(SearchMovieOutputData searchMovieOutputData) {
        this.searchMovieOutputData = searchMovieOutputData;
    }

    public void setSearchMoviesController(SearchMovieController searchMovieController) {
        this.searchMovieController = searchMovieController;
    }

    public void setMovieDetailController(MovieDetailController movieDetailController) {
        this.movieDetailController = movieDetailController;
    }

    public void setFilterCategoriesController(FilterCategoriesController filterCategoriesController) {
        this.filterCategoriesController = filterCategoriesController;
    }
}
