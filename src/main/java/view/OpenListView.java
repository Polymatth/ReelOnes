package view;

import interface_adapter.edit_list.EditListController;
import interface_adapter.filter_categories.FilterCategoriesController;
import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.logout.LogoutController;
import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.open_list.OpenListController;
import interface_adapter.open_list.OpenListViewModel;
import entity.Movie;
import interface_adapter.userprofile.UserProfileController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;

public class OpenListView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "open list view";
    private final String GO_BACK = "Go Back";
    private final OpenListViewModel viewModel;
    private JLabel listNameLabel;
    private JPanel moviesPanel;
    private UserProfileController userProfileController;
    private MovieDetailController movieDetailController;
    private EditListController editListController;
    private FilterCategoriesController filterCategoriesController;
    private OpenListController openListController;

    public OpenListView(OpenListViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Initialize UI Components
        initializeTopPanel();
        initializeMoviesPanel();

        // Load initial state
        updateListName(viewModel.getListName());
        updateMovies(viewModel.getMovies());
    }

    private void initializeTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        String listName = viewModel.getListName();

        // List name label
        listNameLabel = new JLabel(listName);
        listNameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(listNameLabel, BorderLayout.WEST);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton applyFiltersButton = new JButton("Apply Filters");
//        applyFiltersButton.addActionListener( e->
//                new ActionListener() {
//                     public void actionPerformed(ActionEvent evt) {
//                     if (evt.getSource().equals(applyFiltersButton)) {
//                         filterCategoriesController.goToFilterCategoriesView(viewModel.getState().getMovies(),
//                                 viewModel.getState().getFiltersToMovies(),
//                                 viewModel.getState().getFiltersToSelections(),
//                                 viewModel.getViewName());
//                     }
//                     }
//                }
//        );

        buttonPanel.add(applyFiltersButton);

        JButton editListButton = new JButton("Edit List");
        editListButton.addActionListener(
                 new ActionListener() {
                     public void actionPerformed(ActionEvent evt) {
                         if (evt.getSource().equals(editListButton)) {
                            editListController.switchToEditListView(); }}});
        buttonPanel.add(editListButton);

        JButton goBackButton = new JButton(GO_BACK);
        goBackButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(goBackButton)) {
                            userProfileController.switchToProfileView();
                        }
                    }
                }
        );
        buttonPanel.add(goBackButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void initializeMoviesPanel() {
        moviesPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        moviesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(moviesPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void updateListName(String listName) {
        listNameLabel.setText(listName);
    }

    private void updateMovies(List<Movie> movies) {
        moviesPanel.removeAll();
        if (movies == null || movies.isEmpty()) {
            // Display a placeholder message when no movies are available
            JLabel placeholder = new JLabel("No movies in this list. Add some to get started!");
            placeholder.setHorizontalAlignment(SwingConstants.CENTER);
            placeholder.setFont(new Font("Arial", Font.ITALIC, 16));
            moviesPanel.add(placeholder);
        } else {
            // Populate the movies in the grid
            for (Movie movie : movies) {
                JPanel movieBox = createMovieBox(movie);
                moviesPanel.add(movieBox);
            }
        }

        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    private JPanel createMovieBox(Movie movie) {
        JPanel movieBox = new JPanel();
        movieBox.setLayout(new BoxLayout(movieBox, BoxLayout.Y_AXIS));
        movieBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel moviePoster = new JLabel();
        moviePoster.setIcon(new ImageIcon(movie.getPosterPath())); // Replace with actual loading logic
        moviePoster.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel movieTitle = new JLabel(movie.getTitle());
        movieTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        movieTitle.setFont(new Font("Arial", Font.PLAIN, 14));

        movieBox.add(moviePoster);
        movieBox.add(Box.createVerticalStrut(5));
        movieBox.add(movieTitle);

        movieBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieDetailController.execute(movie, viewName);
            }
        });

        return movieBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SwingUtilities.invokeLater(() -> {
            switch (evt.getPropertyName()) {
                case "listName":
                    updateListName((String) evt.getNewValue());
                    break;
                case "movies":
                    updateMovies((List<Movie>) evt.getNewValue());
                    break;
                default:
                    break;
            }
        });
    }

    public String getViewName() {
        return viewName;
    }


    @Override
    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    public void setMovieDetailController(MovieDetailController movieDetailController) {
        this.movieDetailController = movieDetailController;
    }

    public void setUserProfileController(UserProfileController userProfileController) {
        this.userProfileController = userProfileController;
    }

    public void setFilterCategoriesController(FilterCategoriesController filterCategoriesController) {
        this.filterCategoriesController = filterCategoriesController;
    }

    public void setEditListController(EditListController editListController) {
        this.editListController = editListController;
    }
}
