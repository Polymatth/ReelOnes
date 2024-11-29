package view;

import data_access.DBUserDataAccessObject;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.get_currentuser.GetCurrentUserController;
import use_case.get_currentuser.GetCurrentUserOutputData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is changing their password.
 */

public class ChangePasswordView extends  JPanel implements PropertyChangeListener {
    private final String viewName = "change password";
    private final ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController;
    private GetCurrentUserController getCurrentUserController;
    private GetCurrentUserOutputData currentUser;

    private final JButton backToUserView;
    private final JLabel username;
    private final JButton changePassword;
    private final JTextField passwordInputField = new JTextField(15);
    private final JLabel passwordErrorField = new JLabel();

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;

        this.changePasswordViewModel.addPropertyChangeListener(this);


        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        backToUserView = new JButton("Profile");
        buttons.add(backToUserView);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentUser = getCurrentUserController.execute();
                currentState.setPassword(passwordInputField.getText());
                currentState.setUsername(currentUser.getUsername());
                currentState.setFavMovie(currentUser.getFavMovie());
                currentState.setFavDirector(currentUser.getFavDirector());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final ChangePasswordState currentState = changePasswordViewModel.getState();
                        System.out.println(currentState.getUsername() + currentState.getPassword());
                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(), currentState.getFavMovie(), currentState.getFavDirector()
                        );
                    }
                }
        );

        backToUserView.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToUserView();
                    }
                }
        );

        this.add(usernameInfo);
        this.add(username);

        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
        System.out.println("ChangePasswordView initialized");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if (evt.getPropertyName().equals("password")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {return viewName;}

    public void setChangePasswordController(ChangePasswordController changePasswordController) {this.changePasswordController = changePasswordController;}

    public void setGetCurrentUserController(GetCurrentUserController getCurrentUserController) {this.getCurrentUserController = getCurrentUserController;}
}



