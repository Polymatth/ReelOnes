package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.loggedin.LoggedInState;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.logout.LogoutController;
import interface_adapter.search_movie.SearchMovieController;
import interface_adapter.signup.SignupState;
import interface_adapter.userprofile.CircularButton;
import interface_adapter.userprofile.UserProfileController;


/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private LogoutController logoutController;

    private SearchMovieController searchMovieController;

    private final JLabel username;
    private LoginController loginController;
    private final JButton logOut;
    private final JLabel usernameLabel;

   

    private UserProfileController userProfileController;



    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        username = new JLabel();


        // Main layout configuration
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(Color.DARK_GRAY);

        // Create a circular profile button
        CircularButton profileButton = new CircularButton("Profile");
        profileButton.setPreferredSize(new Dimension(40, 40));// Small circle


        // Position profile button at the top-left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 0);  // Padding for the profile button
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.weightx = 0; // No weight for profile button
        gbc.gridwidth = 1; // Profile button only takes up 1 column
        this.add(profileButton, gbc);

         profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {userProfileController.switchToProfileView();
            }
        });

// Add some space between profile button and appName by setting weightx on the column
        gbc.gridx = 1;  // Move to the next column
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Let the appName take up remaining space
        gbc.weightx = 1.0;  // This allows appName to take up the remaining horizontal space

// Name of the app at the top right
        JLabel appName = new JLabel("ReelOnes");
        Font font = new Font("Arial", Font.BOLD, 45);
        appName.setFont(font);
        gbc.insets = new Insets(10, 10, 0, 10);  // Padding for the app name
        gbc.anchor = GridBagConstraints.NORTHWEST;  // Anchor it to the top-left of its cell
        this.add(appName, gbc);


        // Username display below the profile button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JPanel usernamePanel = new JPanel();
        usernamePanel.setBackground(Color.DARK_GRAY);
        JLabel loggedInAsLabel = new JLabel("Logged in as: ");
        loggedInAsLabel.setForeground(Color.WHITE);
        usernameLabel = new JLabel();
        usernameLabel.setForeground(Color.LIGHT_GRAY);

        usernamePanel.add(loggedInAsLabel);
        usernamePanel.add(usernameLabel);
        this.add(usernamePanel, gbc);




        // Search Bar Section
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.DARK_GRAY);
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        this.add(searchPanel, gbc);

        // Panel to display search results below search field
        JPanel searchResultsPanel = new JPanel(new GridLayout(0, 1));  // Display results in a single column
        searchResultsPanel.setBackground(Color.DARK_GRAY);
        gbc.gridy = 3;
        this.add(searchResultsPanel, gbc);

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


        // Popular Movies Section
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel popularMoviesPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        popularMoviesPanel.setBackground(Color.DARK_GRAY);
        popularMoviesPanel.setBorder(BorderFactory.createTitledBorder("Popular Movies"));
        popularMoviesPanel.setPreferredSize(new Dimension(500, 100));

        for (int i = 0; i < 5; i++) {
            JPanel movieThumbnail = new JPanel();
            movieThumbnail.setBackground(new Color(200 + (i * 10), 200, 255 - (i * 20)));
            popularMoviesPanel.add(movieThumbnail);
        }
        this.add(popularMoviesPanel, gbc);

        // Watch List Section
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel watchListPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        watchListPanel.setBackground(Color.DARK_GRAY);
        watchListPanel.setBorder(BorderFactory.createTitledBorder("Watch List"));
        watchListPanel.setPreferredSize(new Dimension(500, 100));

        for (int i = 0; i < 5; i++) {
            JPanel movieThumbnail = new JPanel();
            movieThumbnail.setBackground(new Color(200 + (i * 10), 200, 255 - (i * 20)));
            watchListPanel.add(movieThumbnail);
        }
        this.add(watchListPanel, gbc);

        // Log Out button at the bottom
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.SOUTH;
        logOut = new JButton("Log Out");
        this.add(logOut, gbc);
       
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        logoutController.execute(
                                currentState.getUsername());
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );



    }


    public String getViewName() {
        return viewName;
    }


    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }


    public void setSearchMoviesController(SearchMovieController searchMovieController) {
        this.searchMovieController = searchMovieController;
    }

    public void setUserProfileController(UserProfileController userProfileController) {
        this.userProfileController= userProfileController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
