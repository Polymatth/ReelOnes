package view;

import entity.MovieList;
import entity.UserList;
import interface_adapter.logout.LogoutController;
import interface_adapter.open_list.OpenListController;
import interface_adapter.userprofile.CircularButton;
import interface_adapter.userprofile.UserProfileState;
import interface_adapter.userprofile.UserProfileViewModel;


import javax.swing.*;
import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View for when the user is in their profile.
 */
public class UserProfileView extends JPanel implements PropertyChangeListener {

    private final String viewName = "userprofile";
    private final UserProfileViewModel userProfileViewModel;
    private LogoutController logoutController;
    private OpenListController openListController;

    private final JButton logOut;
    private final JLabel username;
    private final JPanel listsSection;

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


        username = new JLabel(userProfileViewModel.getState().getUsername());
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel movieLabel = new JLabel("<html>Favorite Movie:<br>Director:<br>Genre:<br>Streaming Services:</html>");

        profileSection.add(profilePictureButton);
        profileSection.add(username);
        profileSection.add(movieLabel);
        profileSection.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel buttons = new JPanel( new BorderLayout());
        logOut = new JButton("Log Out");
        buttons.add(logOut, BorderLayout.NORTH);

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

        profilePanel.add(profileSection);
        profilePanel.add(Box.createVerticalStrut(20)); // Space

        // Add the "My Lists" section title
        JLabel myListsLabel = new JLabel("My Lists");
        myListsLabel.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        myListsLabel.setForeground(Color.WHITE);
        this.add(myListsLabel);

        listsSection = new JPanel(new GridLayout(0, 4, 10, 10));
        listsSection.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listsSection.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(listsSection);
        this.add(scrollPane, BorderLayout.CENTER);

        // Add Create New List Button
        JButton createNewListButton = new JButton("Create New List");
        createNewListButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(createNewListButton);

        createNewListButton.addActionListener(e -> {
            // pop-up panel with input fields
            JPanel panel = new JPanel(new GridLayout(3, 1));

            // Choose list name
            JTextField listNameField = new JTextField();
            panel.add(new JLabel("Enter the name of the new list:"));
            panel.add(listNameField);

            // Choose public or private
            JRadioButton publicButton = new JRadioButton("Public");
            JRadioButton privateButton = new JRadioButton("Private");
            publicButton.setSelected(true);  // Default is public
            ButtonGroup group = new ButtonGroup();
            group.add(publicButton);
            group.add(privateButton);
            JPanel radioPanel = new JPanel(new FlowLayout());
            radioPanel.add(publicButton);
            radioPanel.add(privateButton);
            panel.add(radioPanel);

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Create New List", JOptionPane.OK_CANCEL_OPTION);

            // If user presses OK
            if (result == JOptionPane.OK_OPTION) {
                String listName = listNameField.getText().trim();
                Boolean isPublic = publicButton.isSelected();

                // getting username (internally) to create list
                String userId = userProfileViewModel.getState().getUsername();

                // errors
                if (listName.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "List name cannot be empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                } else if( userProfileViewModel.movieListExists(listName)) {
                    JOptionPane.showMessageDialog(null,
                            "List name already exists.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // create list
                    userProfileViewModel.createNewList(userId, listName, isPublic);
                    userProfileViewModel.addNewList(new UserList(userId, listName, isPublic));

                    // success message
                    JOptionPane.showMessageDialog(null,
                            "List \"" + listName + "\" created successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Updating UI
                    refreshLists();
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(createNewListButton);
        add(bottomPanel, BorderLayout.SOUTH);


        profilePanel.add(listsSection);
        profilePanel.add(buttons, BorderLayout.SOUTH);
        this.add(profilePanel);

    }

    private void refreshLists() {
        listsSection.removeAll();
        for (MovieList list : userProfileViewModel.getUserLists()) {
            JButton listButton = new JButton(list.getListName());
            listButton.setSize(200, 200);
            listButton.setHorizontalTextPosition(SwingConstants.CENTER);
            listButton.setBackground(Color.lightGray);
                if (openListController != null) {
                    listButton.addActionListener(
                            evt -> {
                                if (evt.getSource().equals(listButton)) {
                                    openListController.execute(list.getListName());
                                }
                            }
                    );

            listsSection.add(listButton);
                }
        listsSection.revalidate();
        listsSection.repaint();
        }
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
        else if (evt.getPropertyName().equals("userLists")) {
        refreshLists();
        }

        else if (evt.getPropertyName().equals("username")) {
            username.setText(userProfileViewModel.getState().getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setOpenListController(OpenListController openListContoller) {
        this.openListController = openListContoller;
    }
}
