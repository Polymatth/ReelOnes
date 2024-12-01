package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Movie;
import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.search_movie.SearchMovieController;
import interface_adapter.userprofile.CircularButton;
import interface_adapter.userprofile.UserProfileController;
import interface_adapter.userprofile.UserProfileState;
import interface_adapter.userprofile.UserProfileViewModel;


/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private LogoutController logoutController;
    private SearchMovieController searchMovieController;
    private UserProfileController userProfileController;

    private final JLabel usernameLabel;
    private final JPanel nowPlayingMoviesPanel;
    private final JPanel popularMoviesPanel;
    private final JPanel searchResultsPanel;
    private final JButton searchButton;
    private final JButton logOutButton;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.DARK_GRAY);
        CircularButton profileButton = new CircularButton("Profile");
        profileButton.setPreferredSize(new Dimension(150, 150));  // Ensure button size is larger for visibility
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));  // Bigger font size
        profileButton.setToolTipText("Go to Profile");
        profileButton.addActionListener(e -> userProfileController.switchToProfileView());


        gbc.gridx = 0;  // Place it in the first column
        gbc.gridy = 0;  // Ensure it stays in the top-left corner
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding
        gbc.anchor = GridBagConstraints.NORTHWEST;  // Anchor to top-left
        gbc.weightx = 0;  // No horizontal weight for fixed placement
        gbc.weighty = 0;  // No vertical weight

        this.add(profileButton, gbc);

// App Name (ReelOnes)
        JLabel appName = new JLabel("ReelOnes");
        appName.setFont(new Font("Arial", Font.BOLD, 60));  // Ensure larger font for app title
        appName.setForeground(Color.WHITE);
        appName.setHorizontalAlignment(JLabel.CENTER);  // Center-align horizontally


        gbc.gridx = 1;  // Place next to the profile button
        gbc.gridy = 0;  // On the same row as the button
        gbc.gridwidth = 2;  // Span two columns
        gbc.anchor = GridBagConstraints.CENTER;  // Center alignment horizontally
        gbc.insets = new Insets(10, 20, 10, 10);  // Adjust padding
        gbc.weightx = 1;  // Allow the appName to expand
        gbc.weighty = 0;

        this.add(appName, gbc);

        // Username Display
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(Color.DARK_GRAY);
        JLabel loggedInAsLabel = new JLabel("Logged in as: ");
        loggedInAsLabel.setForeground(Color.WHITE);

        usernameLabel = new JLabel();
        usernameLabel.setForeground(Color.LIGHT_GRAY);

        usernamePanel.add(loggedInAsLabel);
        usernamePanel.add(usernameLabel);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;  // Span across multiple columns if necessary
        gbc.insets = new Insets(20, 10, 10, 10);  // Add padding
        gbc.weightx = 1.0;  // Allow horizontal stretching
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
        gbc.anchor = GridBagConstraints.CENTER;  // Center the panel

// Create the search panel with FlowLayout
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.DARK_GRAY);
        JTextField searchField = new JTextField(20);
         searchButton = new JButton("Search");

// Add components to the search panel
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

// Add the search panel to the layout
        this.add(searchPanel, gbc);

// Configure GridBagConstraints for the search results panel
        gbc.gridy = 3;  // Place below the search panel
        gbc.gridwidth = 3;  // Ensure it spans as needed
        gbc.weighty = 1.0;  // Allow vertical expansion
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding
        gbc.fill = GridBagConstraints.BOTH;  // Allow both horizontal and vertical filling

// Panel to display search results
        searchResultsPanel = new JPanel(new GridLayout(0, 1));  // Single-column grid layout
        searchResultsPanel.setBackground(Color.DARK_GRAY);

// Add the search results panel to the layout
        this.add(searchResultsPanel, gbc);


         //Search Bar Section
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.insets = new Insets(20, 10, 10, 10);
//        gbc.anchor = GridBagConstraints.CENTER;
//        JPanel searchPanel = new JPanel(new FlowLayout());
//        searchPanel.setBackground(Color.DARK_GRAY);
//        JTextField searchField = new JTextField(20);
//        searchButton = new JButton("Search");
//        searchPanel.add(searchField);
//        searchPanel.add(searchButton);
//        this.add(searchPanel, gbc);
//
//        // Panel to display search results below search field
//        searchResultsPanel = new JPanel(new GridLayout(0, 1));  // Display results in a single column
//        searchResultsPanel.setBackground(Color.DARK_GRAY);
//        gbc.gridy = 3;
//        this.add(searchResultsPanel, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim();
                if (!query.isEmpty()) {
                    searchMovieController.executeSearch(query);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a movie title to search.");
                }
            }
        });

        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(searchPanel, gbc);

        // Now Playing Movies Title
        JLabel nowPlayingTitle = new JLabel("Now Playing Movies");
        nowPlayingTitle.setFont(new Font("Arial", Font.BOLD, 24));
        nowPlayingTitle.setForeground(Color.WHITE);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nowPlayingTitle, gbc);

        // Now Playing Movies Panel
        nowPlayingMoviesPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        nowPlayingMoviesPanel.setBackground(Color.DARK_GRAY);

        gbc.gridy = 4;
        add(nowPlayingMoviesPanel, gbc);

        // Popular Movies Title
        JLabel popularMoviesTitle = new JLabel("Popular Movies");
        popularMoviesTitle.setFont(new Font("Arial", Font.BOLD, 24));
        popularMoviesTitle.setForeground(Color.WHITE);

        gbc.gridy = 5;
        add(popularMoviesTitle, gbc);

        // Popular Movies Panel
        popularMoviesPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        popularMoviesPanel.setBackground(Color.DARK_GRAY);

        gbc.gridy = 6;
        add(popularMoviesPanel, gbc);

        // Log Out Button
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(e -> logoutController.execute(loggedInViewModel.getState().getUsername()));

        gbc.gridy = 7;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.SOUTH;
        add(logOutButton, gbc);
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setSearchMovieController(SearchMovieController searchMovieController) {
        this.searchMovieController = searchMovieController;
    }

    public void setUserProfileController(UserProfileController userProfileController) {
        this.userProfileController = userProfileController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("loggedin")) {
            final LoggedInState currentState =(LoggedInState) evt.getNewValue();
            final UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
            UserProfileState state = userProfileViewModel.getState();
            System.out.println("LoggedInState: " + currentState.getFavMovie() + ", " + currentState.getFavDirector());
            state.setUsername(currentState.getUsername());
            //System.out.println("LoggedInState: " + state.getUsername());
            state.setFavMovie(currentState.getFavMovie());
            state.setFavDirector(currentState.getFavDirector());

        }
        else if ("nowplaying".equals(evt.getPropertyName())) {
            // Update the UI with the new movies
            final LoggedInState currentState =(LoggedInState) evt.getNewValue();
            List<Movie> movies= currentState.getNowPlayingMovies();
            loggedInViewModel.displayMovies(nowPlayingMoviesPanel, movies);
            loggedInViewModel.renderMovies(nowPlayingMoviesPanel, movies);
        }
        else if ("popular".equals(evt.getPropertyName())) {
            final LoggedInState currentState =(LoggedInState) evt.getNewValue();
            List<Movie> movies = currentState.getPopularMovies();
            loggedInViewModel.displayMovies(popularMoviesPanel, movies);
            loggedInViewModel.renderMovies(popularMoviesPanel, movies);
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchMoviesController(SearchMovieController searchMovieController) {
        this.searchMovieController= searchMovieController;
    }
}
