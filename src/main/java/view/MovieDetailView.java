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

        this.setLayout(new GridLayout(1, 2));

        if (poster != null) {
            displayDetails();
        }
        else {
            JPanel frame = new JPanel();
            this.add(frame);
        }
    }

    public void displayDetails() {
        final JLabel title = new JLabel(this.movieDetailViewModel.getState().getTitle() +
                " (" + this.movieDetailViewModel.getState().getYear() + ")");
        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JLabel director = new JLabel("dir. " + this.movieDetailViewModel.getState().getDirector());
        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JLabel genre = new JLabel(this.movieDetailViewModel.getState().getGenre());
        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

        //        final JLabel streaming = new JLabel(this.movieDetailViewModel.getState().getStreamingServices());
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
        posterPic.setAlignmentX(Component.LEFT_ALIGNMENT);

        addTo = new JButton("Add to");

        final JPanel movieInfo = new JPanel();
        movieInfo.setLayout(new BoxLayout(movieInfo, BoxLayout.PAGE_AXIS));
        movieInfo.add(title);
        movieInfo.add(director);
        movieInfo.add(genre);
//        movieInfo.add(streaming);

        final JPanel addButton = new JPanel();
        addButton.add(addTo);

        final JPanel addPoster = new JPanel();
        addPoster.add(posterPic);

        final JPanel rightSide = new JPanel();
        rightSide.add(movieInfo);
        rightSide.add(addButton);

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
