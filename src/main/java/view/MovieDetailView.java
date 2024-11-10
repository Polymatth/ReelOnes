package view;

import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.movie_detail_page.MovieDetailViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class MovieDetailView extends JPanel implements ActionListener, PropertyChangeListener {

        private final String viewName = "movie detail page";
        private final MovieDetailViewModel movieDetailViewModel;

        private final JButton addTo;
        private final BufferedImage poster;
        private MovieDetailController movieDetailController;

    public MovieDetailView(MovieDetailViewModel movieDetailViewModel) {

        this.movieDetailViewModel = movieDetailViewModel;
        this.movieDetailViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(this.movieDetailViewModel.getState().getTitle() +
                " (" + this.movieDetailViewModel.getState().getYear() + ")");
        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

//        final JLabel director = new JLabel("dir. " + this.movieDetailViewModel.getState().getDirector());
//        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JLabel genre = new JLabel("dir. " + this.movieDetailViewModel.getState().getGenre());
        title.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JLabel streaming = new JLabel(this.movieDetailViewModel.getState().getStreamingServices());

        File file = new File(this.movieDetailViewModel.getState().getPosterImagePath());
        poster = ImageIO.read(file);
        final JLabel posterPic = new JLabel(new ImageIcon(poster));
        posterPic.setAlignmentX(Component.LEFT_ALIGNMENT);

        addTo = new JButton("Add to");

        final JPanel movieInfo = new JPanel();
        movieInfo.add(title);
    //    movieInfo.add(director);
        movieInfo.add(genre);
        movieInfo.add(streaming);

        final JPanel addButton = new JPanel();
        addButton.add(addTo);

        final JPanel addPoster = new JPanel();
        addPoster.add(posterPic);

        final JPanel page = new JPanel();
        page.add(movieInfo);
        page.add(addButton);
        page.add(addPoster);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(page);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
