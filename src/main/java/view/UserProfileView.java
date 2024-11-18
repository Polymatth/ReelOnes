package view;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.logout.LogoutController;
import interface_adapter.userprofile.CircularButton;
import interface_adapter.userprofile.UserProfileState;
import interface_adapter.userprofile.UserProfileViewModel;


import javax.swing.*;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The View for when the user is in their profile.
 */
public class UserProfileView extends JPanel implements PropertyChangeListener {

    private final String viewName = "userprofile";
    private final UserProfileViewModel userProfileViewModel;
    private LogoutController logoutController;

    private final JButton logOut;
    private final JLabel username;

    public UserProfileView(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        // Main layout configuration
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.DARK_GRAY);

        // Title
        JLabel title = new JLabel("Profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);
        this.add(title);


        // Right panel (Profile and Lists)
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        // Profile section
        JPanel profileSection = new JPanel();
        profileSection.setBackground(Color.WHITE);
        profileSection.setLayout(new BoxLayout(profileSection, BoxLayout.Y_AXIS));

        CircularButton profilePictureButton = new CircularButton("profile picture");
        profilePictureButton.setPreferredSize(new Dimension(80, 80));


        username = new JLabel("Username"); // Initialize the username label here
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel movieLabel = new JLabel("<html>Favorite Movie:<br>Director:<br>Genre:<br>Streaming Services:</html>");

        profileSection.add(profilePictureButton);
        profileSection.add(username);
        profileSection.add(movieLabel);


        JPanel listsSection = new JPanel(new GridLayout(1, 5, 10, 10));
        listsSection.setBackground(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            JPanel listThumbnail = new JPanel();
            listThumbnail.setBackground(new Color(100 + i * 30, 150, 200 - i * 30));
            listsSection.add(listThumbnail);
        }

        profilePanel.add(profileSection);
        profilePanel.add(Box.createVerticalStrut(20)); // Space
        profilePanel.add(listsSection);
        this.add(profilePanel);

        final JPanel buttons = new JPanel( new BorderLayout());
        logOut = new JButton("Log Out");
        buttons.add(logOut, BorderLayout.NORTH);
        this.add(buttons, BorderLayout.NORTH);

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final UserProfileState currentState = userProfileViewModel.getState();
                        logoutController.execute(
                                currentState.getUsername());
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );



    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final UserProfileState state = (UserProfileState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

}
