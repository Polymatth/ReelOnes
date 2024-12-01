package view;

import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.movie_detail_page.MovieDetailViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetailView extends JPanel implements ActionListener, PropertyChangeListener {

        private final String viewName = "movie detail page";
        private final MovieDetailViewModel movieDetailViewModel;

        private JButton addTo = null;
        private BufferedImage poster = null;
        private MovieDetailController movieDetailController;

    public MovieDetailView(MovieDetailViewModel movieDetailViewModel) {

        this.movieDetailViewModel = movieDetailViewModel;
        this.movieDetailViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        if (poster != null) {
            displayDetails();
        }
        else {
            JPanel frame = new JPanel();
            this.add(frame);
        }
    }

    public void displayDetails() {
        this.removeAll();
        final JLabel title = new JLabel(this.movieDetailViewModel.getState().getTitle() +
                " (" + this.movieDetailViewModel.getState().getYear() + ")");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JLabel director = new JLabel("dir. " + this.movieDetailViewModel.getState().getDirector());
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JLabel genre = new JLabel("Genres: " + this.movieDetailViewModel.getState().getGenre());
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JLabel streaming = new JLabel("Streaming on: " + this.movieDetailViewModel.getState()
                .getStreamingServices());

        String posterUrl = "https://image.tmdb.org/t/p/w500" + this.movieDetailViewModel.getState().getPosterImagePath();
        URL url = null;
        try {
            url = new URL(posterUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            poster = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final JLabel posterPic = new JLabel(new ImageIcon(poster));

        addTo = new JButton("Add to");
        final JButton back = new JButton("Back to list");

        final JPanel movieInfo = new JPanel();
        movieInfo.setLayout(new BoxLayout(movieInfo, BoxLayout.PAGE_AXIS));
        movieInfo.add(title);
        movieInfo.add(director);
        movieInfo.add(genre);
        movieInfo.add(streaming);

        final JPanel addButton = new JPanel();
        addButton.add(addTo);

        final JPanel backButton = new JPanel();
        backButton.add(back);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movieDetailController.executeReturnToListView(movieDetailViewModel.getState().getOriginView());
                    }
                }
        );

        final JPanel addPoster = new JPanel();
        addPoster.add(posterPic);

        final JPanel rightSide = new JPanel();
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.PAGE_AXIS));
        rightSide.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightSide.add(movieInfo);
        rightSide.add(addButton);
        rightSide.add(backButton);

        this.add(addPoster);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        displayDetails();
    }

    public String getViewName() {
        return viewName;
    }

    public void setMovieDetailController(MovieDetailController movieDetailController) {
        this.movieDetailController = movieDetailController;
    }
}
