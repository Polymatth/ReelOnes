package interface_adapter.loggedin;

import entity.Movie;
import interface_adapter.ViewModel;
import interface_adapter.fetch_nowplayingmovies.FetchNowPlayingMoviesController;
import interface_adapter.fetch_popularmovies.FetchPopularMoviesController;
import interface_adapter.get_currentuser.GetCurrentUserController;
import interface_adapter.movie_detail_page.MovieDetailController;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputData;
import use_case.get_currentuser.GetCurrentUserOutputData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public LoggedInViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

    private List<Movie> popularMovies;
    private List<Movie> topRatedMovies;
    private List<Movie> upcomingMovies;
    private List<Movie> nowPlayingMovies;
    private FetchNowPlayingMoviesController fetchNowPlayingMoviesController;
    private FetchPopularMoviesController fetchPopularMoviesController;
    private MovieDetailController movieDetailController;
    private Color primaryColor = new Color(186, 183, 237);

    public void setPopularMovies(List<Movie> movies) {
        this.popularMovies = movies;
    }

    public List<Movie> getPopularMovies() {
        return this.popularMovies;
    }

    public void setTopRatedMovies(List<Movie> movies) {
        this.topRatedMovies = movies;
    }

    public List<Movie> getTopRatedMovies() {
        return this.topRatedMovies;
    }

    public void setUpcomingMovies(List<Movie> movies) {
        this.upcomingMovies = movies;
    }

    public List<Movie> getUpcomingMovies() {
        return this.upcomingMovies;
    }

    public List<Movie> getNowPlayingMovies() {
       return nowPlayingMovies;
    }

    public void setNowPlayingMovies(List<Movie> nowPlayingMovies) {
        this.nowPlayingMovies = nowPlayingMovies;
    }


    public void setErrorMessage(String errorMessage) {
        getState().setErrorMessage(errorMessage);
    }

    public void loadNowPlayingMovies() {
        fetchNowPlayingMoviesController.execute();
    }

    public void loadPopularMovies() { fetchPopularMoviesController.execute(); }

    public ImageIcon loadImage(String url, int width, int height) {
        try {
            String baseUrl = "https://image.tmdb.org/t/p/w500/";

            // Combine the base URL with the relative image path
            URL fullUrl = new URL(baseUrl + url); //
            Image image = new ImageIcon(fullUrl).getImage();
            return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle broken or missing images gracefully
        }
    }

    public void displayMovies(JPanel panel, List<Movie> movies) {
        panel.removeAll();
        for (Movie movie : movies) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            moviePanel.setBackground(Color.DARK_GRAY);

            // Load the movie poster
            ImageIcon poster = loadImage(movie.getPosterPath(), 100, 150); // Adjust size as needed
            JLabel posterLabel = new JLabel(poster);

            // Add the movie title
            JLabel titleLabel = new JLabel("<html><center>" + movie.getTitle() + "</center></html>");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Assemble the movie panel
            moviePanel.add(posterLabel, BorderLayout.CENTER);
            moviePanel.add(titleLabel, BorderLayout.SOUTH);

            panel.add(moviePanel);
        }
        panel.revalidate();
        panel.repaint();
    }

    public void renderMovies(JPanel panel, List<Movie> movies) {
        panel.removeAll();  // Remove any old movie buttons

        for (Movie movie : movies) {
            final JButton movieButton = new JButton(movie.getTitle());
            try {
                URL url = new URL("https://image.tmdb.org/t/p/w500" + movie.getPosterPath());
                Image image = ImageIO.read(url).getScaledInstance(160, 240, Image.SCALE_FAST);
                final Icon movieIcon = new ImageIcon(image);

                movieButton.setIcon(movieIcon);
                movieButton.setLabel(movie.getTitle());
                movieButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                movieButton.setHorizontalTextPosition(SwingConstants.CENTER);
                movieButton.setBackground(primaryColor);

                // ActionListener to navigate to movie detail page
                movieButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(movieButton)) {
                            movieDetailController.execute(movie, "logged in");
                        }
                    }
                });

                panel.add(movieButton);  // Add the button to the panel
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        panel.revalidate();  // Revalidate the layout after adding new components
        panel.repaint();     // Repaint to reflect changes
    }
     public void setFetchNowPlayingMoviesController(FetchNowPlayingMoviesController fetchNowPlayingMoviesController) {
        this.fetchNowPlayingMoviesController = fetchNowPlayingMoviesController;
     }

     public void setFetchPopularMoviesController(FetchPopularMoviesController fetchPopularMoviesController) {
        this.fetchPopularMoviesController = fetchPopularMoviesController;
     }

     public void setMovieDetailController(MovieDetailController movieDetailController) {
        this.movieDetailController = movieDetailController;
     }

}
