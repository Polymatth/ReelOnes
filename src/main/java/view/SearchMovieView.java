package view;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.Movie;
import interface_adapter.login.LoginState;
import interface_adapter.search_movie.SearchMoviesController;
import interface_adapter.search_movie.SearchMovieState;
import interface_adapter.search_movie.SearchMovieViewModel;
import use_case.search_movie.SearchMovieOutputData;


/**
 * The View for when the user is logging into the program.
 */
public class SearchMovieView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "movie search";
    private final SearchMovieViewModel searchMovieViewModel;
    private final JLabel searchErrorField = new JLabel();
    private SearchMovieOutputData searchMovieOutputData;

    private SearchMoviesController searchMoviesController;

    public SearchMovieView(SearchMovieViewModel searchMovieViewModel) {

        this.searchMovieViewModel = searchMovieViewModel;
        this.searchMovieViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JList results = new JList<>();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);

        if (searchMovieViewModel.getState().getMovies() != null) {
            for (Movie movie : searchMovieViewModel.getState().getMovies()) {
                final JButton result = new JButton(movie.getTitle());
                final Icon resultIcon = new ImageIcon(movie.getPosterPath());
                result.setIcon(resultIcon);
                result.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(result)) {
                                    //movieDetailController.execute(movie);
                                }
                            }
                        }
                );
                results.add(result);
            }
        }
        this.add(title);
        this.add(results);
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

    }

    private void setFields(SearchMovieState state) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchMovieOutputData(SearchMovieOutputData searchMovieOutputData) {
        this.searchMovieOutputData = searchMovieOutputData;
    }

    public void setSearchMoviesController(SearchMoviesController searchMovieController) {
        this.searchMoviesController = searchMovieController;
    }
}
