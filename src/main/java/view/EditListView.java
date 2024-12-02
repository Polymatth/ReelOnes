package view;

import entity.Movie;
import entity.MovieList;
import interface_adapter.edit_list.EditListController;
import interface_adapter.edit_list.EditListState;
import interface_adapter.edit_list.EditListViewModel;
import interface_adapter.get_currentuser.GetCurrentUserController;
import interface_adapter.open_list.OpenListController;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view when the user edits a movie List.
 */
public class EditListView extends JPanel implements PropertyChangeListener {
    private final String viewName = "edit list";
    private final EditListViewModel viewModel;
    private EditListController editListController;
    private OpenListController openListController;
    private GetCurrentUserController getCurrentUserController;

    private final JTextField listNameInput = new JTextField(15);
    private final JLabel errorLabel = new JLabel();

    public EditListView(EditListViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Edit " + viewModel.getState().getListName(), SwingConstants.CENTER);
        add(title);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("New List Name:"));
        inputPanel.add(listNameInput);
        add(inputPanel);

        errorLabel.setForeground(Color.RED);
        add(errorLabel);

        JButton updateButton = new JButton("Update List");
        updateButton.addActionListener(e -> {
            String newListName = listNameInput.getText();
            editListController.execute(getCurrentUserController.execute().getUsername(), viewModel.getState().getListName(), newListName);
        });
        add(updateButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> openListController.execute(viewModel.getState().getListName()));
        add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            EditListState state = (EditListState) evt.getNewValue();
            if (state.getErrorMessage() != null) {
                errorLabel.setText(state.getErrorMessage());
            } else {
                errorLabel.setText("");
            }
        }
    }

    public void setEditListController(EditListController controller) {
        this.editListController = controller;
    }

    public void setGetCurrentUserController(GetCurrentUserController controller) {
        this.getCurrentUserController = controller;
    }

    public void setOpenListController(OpenListController openListController) {
        this.openListController = openListController;
    }

    public String getViewName() {
        return viewName;
    }
}
