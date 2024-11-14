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
import interface_adapter.search_movie.SearchMovieController;
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

    private SearchMovieController searchMovieController;

    public SearchMovieView(SearchMovieViewModel searchMovieViewModel) throws IOException {

        this.searchMovieViewModel = searchMovieViewModel;
        this.searchMovieViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JList results = new JList<>();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        updateResults(searchMovieViewModel);
        this.add(title);
        this.add(results);
    }

    private void updateResults(SearchMovieViewModel searchMovieViewModel) throws IOException {
        if (searchMovieViewModel.getState().getMovies() != null) {
            for (Movie movie : searchMovieViewModel.getState().getMovies()) {
                final JButton result = new JButton(movie.getTitle());
                URL url = new URL("https://image.tmdb.org/t/p/w500"+ movie.getPosterPath());
                BufferedImage image = ImageIO.read(url);
                final Icon resultIcon = new ImageIcon(image);
                System.out.println("https://image.tmdb.org/t/p/w500"+ movie.getPosterPath());
                result.setLabel(movie.getTitle());
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
                this.add(result);
            }
        }
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
}
