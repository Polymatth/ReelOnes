package view;

import entity.Movie;
import entity.MovieList;
import entity.UserList;
import interface_adapter.add_movie_to_list.AddMovieController;
import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.movie_detail_page.MovieDetailViewModel;
import interface_adapter.movie_list.MovieListViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The view when the user selects a movie to view details about it.
 */
public class MovieDetailView extends JPanel implements ActionListener, PropertyChangeListener {

        private final String viewName = "movie detail page";
        private final MovieDetailViewModel movieDetailViewModel;
        private final MovieListViewModel movieListViewModel;

        private JButton addTo = null;
        private BufferedImage poster = null;
        private MovieDetailController movieDetailController;
        private AddMovieController addMovieController;

        private String username;
        private String password;
        private String favMovie;
        private String favDirector;
        private List<MovieList> userMovieListsList;

    public MovieDetailView(MovieDetailViewModel movieDetailViewModel, MovieListViewModel movieListViewModel) {

        this.movieDetailViewModel = movieDetailViewModel;
        this.movieListViewModel = movieListViewModel;
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
        //Ensures that there is nothing currently being displayed so that only the correct information is displayed.
        this.removeAll();

        //Set the labels for the different pieces of information about the movie.
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

        //Create button to add the movie to a list and a back button.
        addTo = new JButton("Add to");
        final JButton back = new JButton("Back to list");

        //Place labels that have information about the movie together.
        final JPanel movieInfo = new JPanel();
        movieInfo.setLayout(new BoxLayout(movieInfo, BoxLayout.PAGE_AXIS));
        movieInfo.add(title);
        movieInfo.add(director);
        movieInfo.add(genre);
        movieInfo.add(streaming);

        final JPanel addButton = new JPanel();
        addButton.add(addTo);
        addTo.addActionListener(e -> showAddToListPopup());

        final JPanel backButton = new JPanel();
        backButton.add(back);

        //Create action listener for the back button.
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

        //Place movie information in a panel with the 2 buttons, to be placed on the right side of the page.
        final JPanel rightSide = new JPanel();
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.PAGE_AXIS));
        rightSide.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightSide.add(movieInfo);
        rightSide.add(addButton);
        rightSide.add(backButton);

        this.add(addPoster);
        this.add(rightSide);
    }


    private void showAddToListPopup() {
        try {
            List<MovieList> userLists = movieListViewModel.getState().getUserLists();

            if (userLists == null || userLists.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "You don't have any lists. Create a list first!",
                        "No Lists Found",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String[] listArray = new String[userLists.size()];
            for (int i = 0; i < userLists.size(); i++) {
                listArray[i] = userLists.get(i).getListName();
            }
            String selectedList = (String) JOptionPane.showInputDialog(this,
                    "Select a list to add the movie to:",
                    "Add Movie to List",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    listArray,
                    listArray[0]);

            if (selectedList != null && !selectedList.trim().isEmpty()) {
                try {
                    Movie movie = movieDetailViewModel.getState().getMovie();
                    for (int i = 0; i < userLists.size(); i++) {
                        if (userLists.get(i).getListName() == selectedList){
                             userLists.get(i).addMovie(movieDetailViewModel.getState().getMovie());
                        }
                    }
                    addMovieController.addMovieToList(username,password,favMovie,favDirector,userLists,selectedList,movie.getTitle());
                    JOptionPane.showMessageDialog(this,
                            "Movie added to \"" + selectedList + "\" successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to add movie: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedList != null) {
                JOptionPane.showMessageDialog(null,
                        "List name cannot be empty.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,
                    "Error fetching lists: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     * React to a button click that results in evt.
     * @param e the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        displayDetails();
        if (evt.getPropertyName().equals("currentuser")) {
            userMovieListsList = movieDetailViewModel.getState().getUserLists();
            username = movieDetailViewModel.getState().getUsername();
            password = movieDetailViewModel.getState().getPassword();
            favMovie = movieDetailViewModel.getState().getFavMovie();
            favDirector =movieDetailViewModel.getState().getFavDirector();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setMovieDetailController(MovieDetailController movieDetailController) {
        this.movieDetailController = movieDetailController;
    }
    public void setAddMovieController(AddMovieController addMovieController) {
        this.addMovieController = addMovieController;
    }
}
