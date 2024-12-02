package app;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


import data_access.*;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_favorites.ChangeFavoritesController;
import interface_adapter.change_favorites.ChangeFavoritesPresenter;
import interface_adapter.change_favorites.ChangeFavoritesViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.edit_list.EditListPresenter;
import interface_adapter.edit_list.EditListViewModel;
import interface_adapter.edit_list.EditListController;
import interface_adapter.fetch_nowplayingmovies.FetchNowPlayingMoviesController;
import interface_adapter.fetch_nowplayingmovies.FetchNowPlayingMoviesPresenter;
import interface_adapter.fetch_popularmovies.FetchPopularMoviesController;
import interface_adapter.fetch_popularmovies.FetchPopularMoviesPresenter;
import interface_adapter.get_currentuser.GetCurrentUserController;
import interface_adapter.get_currentuser.GetCurrentUserPresenter;
import interface_adapter.loggedin.LoggedInViewModel;
import interface_adapter.filter_categories.FilterCategoriesController;
import interface_adapter.filter_categories.FilterCategoriesPresenter;
import interface_adapter.filter_categories.FilterCategoriesViewModel;
import interface_adapter.filter_category.FilterCategoryController;
import interface_adapter.filter_category.FilterCategoryPresenter;
import interface_adapter.filter_category.FilterCategoryViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.movie_detail_page.MovieDetailController;
import interface_adapter.movie_detail_page.MovieDetailPresenter;
import interface_adapter.movie_detail_page.MovieDetailViewModel;
import interface_adapter.movie_list.MovieListPresenter;
import interface_adapter.movie_list.MovieListViewModel;
import interface_adapter.open_list.OpenListController;
import interface_adapter.open_list.OpenListPresenter;
import interface_adapter.open_list.OpenListViewModel;
import interface_adapter.search_movie.SearchMovieController;
import interface_adapter.search_movie.SearchMoviePresenter;
import interface_adapter.search_movie.SearchMovieViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.userprofile.UserProfileController;
import interface_adapter.userprofile.UserProfilePresenter;
import interface_adapter.userprofile.UserProfileViewModel;
import use_case.change_favorites.ChangeFavoritesInputBoundary;
import use_case.change_favorites.ChangeFavoritesInteractor;
import use_case.change_favorites.ChangeFavoritesOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;

import use_case.edit_list.EditListOutputBoundary;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesDataAccessInterface;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesInputBoundary;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesInteractor;
import use_case.fetch_nowplayingmovies.FetchNowPlayingMoviesOutputBoundary;
import use_case.fetch_popularmovies.FetchPopularMoviesDataAccessInterface;
import use_case.fetch_popularmovies.FetchPopularMoviesInputBoundary;
import use_case.fetch_popularmovies.FetchPopularMoviesOutputBoundary;
import use_case.fetch_popularmovies.FetchPopularMoviesInteractor;

import use_case.get_currentuser.GetCurrentOutputBoundary;
import use_case.get_currentuser.GetCurrentUserInputBoundary;
import use_case.get_currentuser.GetCurrentUserInteractor;
import use_case.clear_filters.ClearAllFiltersInputBoundary;
import use_case.clear_filters.ClearAllFiltersInteractor;
import use_case.clear_filters.ClearAllFiltersOutputBoundary;
import use_case.filter_application.FilterApplicationInputBoundary;
import use_case.filter_application.FilterApplicationInteractor;
import use_case.filter_application.FilterApplicationOutputBoundary;
import use_case.filter_application.FilterApplicationOutputData;
import use_case.filter_category_selection.FilterCategorySelectionInputBoundary;
import use_case.filter_category_selection.FilterCategorySelectionInteractor;
import use_case.filter_category_selection.FilterCategorySelectionOutputBoundary;
import use_case.go_to_filter_categories.GoToFilterCategoriesInputBoundary;
import use_case.go_to_filter_categories.GoToFilterCategoriesInteractor;
import use_case.go_to_filter_categories.GoToFilterCategoriesOutputBoundary;
import use_case.goprofile.GoProfileInteractor;
import use_case.goprofile.GoProfileOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.movie_detail.MovieDetailDataAccessInterface;
import use_case.movie_detail.MovieDetailInputBoundary;
import use_case.movie_detail.MovieDetailInteractor;
import use_case.movie_detail.MovieDetailOutputBoundary;

import use_case.movie_list.MovieListDataAccessInterface;
import use_case.movie_list.MovieListInputBoundary;
import use_case.movie_list.MovieListInteractor;
import use_case.movie_list.MovieListOutputBoundary;
import interface_adapter.movie_list.MovieListController;
import use_case.open_list.OpenListDataAccessInterface;
import use_case.open_list.OpenListInputBoundary;
import use_case.open_list.OpenListInteractor;
import use_case.open_list.OpenListOutputBoundary;
import use_case.edit_list.EditListDataAccessInterface;
import use_case.edit_list.EditListInputBoundary;
import use_case.edit_list.EditListInteractor;
import use_case.edit_list.EditListOutputBoundary;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesInputBoundary;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesInteractor;
import use_case.return_to_filter_categories.ReturnToFilterCategoriesOutputBoundary;
import use_case.return_to_list_from_filter_categories.ReturnToListInputBoundary;
import use_case.return_to_list_from_filter_categories.ReturnToListInteractor;
import use_case.return_to_list_from_filter_categories.ReturnToListOutputBoundary;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieInputBoundary;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieInteractor;
import use_case.return_to_list_from_movie_detail.ReturnToListFromMovieOutputBoundary;
import use_case.search_movie.SearchMovieDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.search_movie.SearchMovieInputBoundary;
import use_case.search_movie.SearchMovieInteractor;
import use_case.search_movie.SearchMovieOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final DBMovieListDataAccessObject dbMovieListDataAccessObject = new DBMovieListDataAccessObject();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory, dbMovieListDataAccessObject);


    private final SearchMovieDataAccessInterface searchMovieDataAccessInterface = new AppConfig().getMovieDataAccess();
    private final FetchNowPlayingMoviesDataAccessInterface fetchNowPlayingMoviesDataAccessInterface =  new AppConfig().getNowPlayingMovieDataAccess();
    private final FetchPopularMoviesDataAccessInterface fetchPopularMoviesDataAccessInterface =  new AppConfig().getPopularMoviesDataAccess();
    private final MovieDetailDataAccessInterface movieDetailDataAccessInterface = new AppConfig()
            .getMovieDetailDataAccess();
    private final OpenListDataAccessInterface openListDataAccessInterface = dbMovieListDataAccessObject;
    private final EditListDataAccessInterface editListDataAccessInterface = dbMovieListDataAccessObject;
    private final MovieListDataAccessInterface movieListDataAccessInterface = dbMovieListDataAccessObject;


    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private UserProfileViewModel userProfileViewModel;
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangeFavoritesViewModel changeFavoritesViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private UserProfileView userProfileView;

    private OpenListViewModel openListViewModel;
    private EditListViewModel editListViewModel;
    private ChangePasswordView changePasswordView;
    private MovieListViewModel movieListViewModel;


    private SearchMovieView searchMovieView;
    private SearchMovieViewModel searchMovieViewModel;
    private OpenListView openListView;
    private EditListView editListView;

    private MovieDetailView movieDetailView;
    private MovieDetailViewModel movieDetailViewModel;

    private FilterCategoriesView filterCategoriesView;
    private FilterCategoriesViewModel filterCategoriesViewModel;

    private FilterCategoryView filterCategoryView;
    private FilterCategoryViewModel filterCategoryViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }


    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the User Profile View to the application.
     * @return this builder
     */
    public AppBuilder addUserProfileView() {
        userProfileViewModel = new UserProfileViewModel();
        userProfileView = new UserProfileView(userProfileViewModel);
        cardPanel.add(userProfileView, userProfileView.getViewName());

        return this;
    }

    /**
     * Adds the Change Password View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }


    /** Adds the SearchMovie View to the application.
     * @return this builder
     */
    public AppBuilder addSearchMovieView() throws IOException {
    searchMovieViewModel = new SearchMovieViewModel();
    searchMovieView = new SearchMovieView(searchMovieViewModel);
    cardPanel.add(searchMovieView, searchMovieView.getViewName());
    return this;}

    /**
     * Adds the MovieDetail View to the application.
     * @return this builder
     */
    public AppBuilder addMovieDetailView() {
        movieDetailViewModel = new MovieDetailViewModel();
        movieListViewModel = new MovieListViewModel();
        movieDetailView = new MovieDetailView(movieDetailViewModel, movieListViewModel);
        cardPanel.add(movieDetailView, movieDetailView.getViewName());
        return this;
    }

    /**
     * Adds the OpenList View to the application.
     * @return this builder
     */
    public AppBuilder addOpenListView() {
        openListViewModel = new OpenListViewModel();
        openListView = new OpenListView(openListViewModel);
        cardPanel.add(openListView, openListView.getViewName());
        return this;
    }

    /**
     * Adds the EditList View to the application.
     * @return this builder
     */
    public AppBuilder addEditListView() {
        editListViewModel = new EditListViewModel();
        editListView = new EditListView(editListViewModel);
        cardPanel.add(editListView, editListView.getViewName());
        return this;
    }

    public AppBuilder addFilterCategoriesView() {
        filterCategoriesViewModel = new FilterCategoriesViewModel();
        filterCategoriesView = new FilterCategoriesView(filterCategoriesViewModel);
        cardPanel.add(filterCategoriesView, filterCategoriesView.getViewName());
        return this;
    }

    public AppBuilder addFilterCategoryView() {
        filterCategoryViewModel = new FilterCategoryViewModel();
        filterCategoryView = new FilterCategoryView(filterCategoryViewModel);
        cardPanel.add(filterCategoryView, filterCategoryView.getViewName());

        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addChangeFavoritesView() {
        changeFavoritesViewModel = new ChangeFavoritesViewModel();
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);
        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Get Current User Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGetCurrentUserUseCase() {
        final GetCurrentOutputBoundary getCurrentUserOutputBoundary = new GetCurrentUserPresenter(viewManagerModel);
        final GetCurrentUserInputBoundary getCurrentUserInteractor = new GetCurrentUserInteractor(
                userDataAccessObject, getCurrentUserOutputBoundary);

        final GetCurrentUserController getCurrentUserController = new GetCurrentUserController(getCurrentUserInteractor);
        System.out.println("Created GetCurrentUserController: " + getCurrentUserController);
        changePasswordView.setGetCurrentUserController(getCurrentUserController);
        editListView.setGetCurrentUserController(getCurrentUserController);
        userProfileViewModel.setGetCurrentUserController(getCurrentUserController);

        return this;
    }

    /**
     * Adds the Go Profile Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGoProfileUseCase() {
        GoProfileOutputBoundary goProfileOutputBoundary = new UserProfilePresenter(viewManagerModel,userProfileViewModel,loggedInViewModel,changePasswordViewModel);
        GoProfileInteractor goProfileInteractor = new GoProfileInteractor(goProfileOutputBoundary); // Assuming this is implemented
        UserProfileController userProfileController = new UserProfileController(goProfileInteractor);
        loggedInView.setUserProfileController(userProfileController);

         openListView.setUserProfileController(userProfileController);
         userProfileView.setUserProfileController(userProfileController);
        return this;
    }

    /**
     * Adds the Search Movie Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchMovieUseCase() {
        final SearchMovieOutputBoundary searchMovieOutputBoundary = new SearchMoviePresenter(viewManagerModel,
                searchMovieViewModel, loggedInViewModel);
        final SearchMovieInputBoundary searchMovieInteractor = new SearchMovieInteractor(searchMovieOutputBoundary,
                searchMovieDataAccessInterface);
        final SearchMovieController searchMovieController = new SearchMovieController(searchMovieInteractor);
        loggedInView.setSearchMoviesController(searchMovieController);
        searchMovieView.setSearchMoviesController(searchMovieController);
        return this;
    }


    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(viewManagerModel,changePasswordViewModel, userProfileViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);


        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        changePasswordView.setChangePasswordController(changePasswordController);
        return this;
    }



    /**
     * Adds the Change Favorites Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangeFavoritesUseCase() {
        final ChangeFavoritesOutputBoundary changeFavoritesOutputBoundary = new ChangeFavoritesPresenter(viewManagerModel,changeFavoritesViewModel,userProfileViewModel);

        final ChangeFavoritesInputBoundary changeFavoritesInteractor = new ChangeFavoritesInteractor(userDataAccessObject,changeFavoritesOutputBoundary,userFactory);

        final ChangeFavoritesController changeFavoritesController = new ChangeFavoritesController(changeFavoritesInteractor);
        userProfileView.setChangeFavoritesController(changeFavoritesController);
        return this;
    }


    /**
     * Adds the Movie Detail Selection Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMovieDetailUseCases() {
        final MovieDetailOutputBoundary movieDetailOutputBoundary = new MovieDetailPresenter(movieDetailViewModel,
                viewManagerModel);
        final MovieDetailInputBoundary movieDetailInteractor = new MovieDetailInteractor(movieDetailOutputBoundary,
                movieDetailDataAccessInterface);
        final ReturnToListFromMovieInputBoundary returnToListFromMovieInteractor = new ReturnToListFromMovieInteractor(
                (ReturnToListFromMovieOutputBoundary)movieDetailOutputBoundary);
        final MovieDetailController movieDetailController = new MovieDetailController(movieDetailInteractor,
                returnToListFromMovieInteractor);
        movieDetailView.setMovieDetailController(movieDetailController);
        searchMovieView.setMovieDetailController(movieDetailController);
        openListView.setMovieDetailController(movieDetailController);
        loggedInViewModel.setMovieDetailController(movieDetailController);
        return this;
    }

    public AppBuilder addFilterCategoriesUseCases() {
        final GoToFilterCategoriesOutputBoundary goToFilterCategoriesOutputBoundary = new FilterCategoriesPresenter(
                filterCategoriesViewModel, searchMovieViewModel, viewManagerModel);
        final GoToFilterCategoriesInputBoundary goToFilterCategoriesInteractor = new GoToFilterCategoriesInteractor(
                goToFilterCategoriesOutputBoundary);
        final ClearAllFiltersInputBoundary clearAllFiltersInteractor = new ClearAllFiltersInteractor(
                (ClearAllFiltersOutputBoundary)goToFilterCategoriesOutputBoundary);
        final ReturnToListInputBoundary returnToListInteractor = new ReturnToListInteractor((ReturnToListOutputBoundary)
                goToFilterCategoriesOutputBoundary);
        final ReturnToFilterCategoriesInputBoundary returnToFilterCategoriesInteractor = new
                ReturnToFilterCategoriesInteractor((ReturnToFilterCategoriesOutputBoundary)
                goToFilterCategoriesOutputBoundary);
        final FilterCategoriesController filterCategoriesController = new FilterCategoriesController(
                returnToFilterCategoriesInteractor, goToFilterCategoriesInteractor, clearAllFiltersInteractor,
                returnToListInteractor);
        filterCategoriesView.setFilterCategoriesController(filterCategoriesController);
        filterCategoryView.setFilterCategoriesController(filterCategoriesController);
        searchMovieView.setFilterCategoriesController(filterCategoriesController);
        openListView.setFilterCategoriesController(filterCategoriesController);
        return this;
    }

    public AppBuilder addFilterCategoryUseCases() {
        final FilterCategorySelectionOutputBoundary filterCategoryPresenter = new FilterCategoryPresenter(
                filterCategoryViewModel, filterCategoriesViewModel, viewManagerModel);
        final FilterCategorySelectionInputBoundary filterCategorySelectionInteractor = new
                FilterCategorySelectionInteractor(filterCategoryPresenter);
        final FilterApplicationInputBoundary filterApplicationInteractor = new FilterApplicationInteractor(
                (FilterApplicationOutputBoundary)filterCategoryPresenter, movieDetailDataAccessInterface);
        final FilterCategoryController filterCategoryController = new FilterCategoryController(
                filterCategorySelectionInteractor, filterApplicationInteractor);
        filterCategoriesView.setFilterCategoryController(filterCategoryController);
        filterCategoryView.setFilterCategoryController(filterCategoryController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel,userProfileViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        userProfileView.setLogoutController(logoutController);
        return this;
    }


    /**
     * Adds the Open List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMovieListUseCase() {
        final MovieListOutputBoundary movieListOutputBoundary = new MovieListPresenter(movieListViewModel,
                viewManagerModel);
        final MovieListInputBoundary movieListInteractor = new MovieListInteractor(movieListDataAccessInterface, movieListOutputBoundary,userFactory);
        final MovieListController movieListController = new MovieListController(movieListInteractor);
        //movieDetailView.setMovieListController(movieListController);
        userProfileView.setMovieListController(movieListController);
        return this;
    }

    /**
     * Adds the Open List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addOpenListUseCase() {
        final OpenListOutputBoundary openListOutputBoundary = new OpenListPresenter(openListViewModel,
                viewManagerModel);
        final OpenListInputBoundary openListInteractor = new OpenListInteractor(openListDataAccessInterface, openListOutputBoundary);
        final OpenListController openListController = new OpenListController(openListInteractor);
        userProfileView.setOpenListController(openListController);
        editListView.setOpenListController(openListController);
        return this;
    }

    /**
     * Adds the Edit List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addEditListUseCase() {
        final EditListOutputBoundary editListOutputBoundary = new EditListPresenter(viewManagerModel, editListViewModel, openListViewModel);
        final EditListInputBoundary editListInteractor = new EditListInteractor(editListDataAccessInterface, editListOutputBoundary);
        final EditListController editListController = new EditListController(editListInteractor);
        editListView.setEditListController(editListController);
        openListView.setEditListController(editListController);
        return this;
    }

    /**
     * Adds the  FetchNowPlayingMovies Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFetchNowPlayingMoviesUseCase() {
        final FetchNowPlayingMoviesOutputBoundary fetchNowPlayingMoviesOutputBoundary = new FetchNowPlayingMoviesPresenter(viewManagerModel,loggedInViewModel);
        final FetchNowPlayingMoviesInputBoundary fetchNowPlayingMoviesInteractor = new FetchNowPlayingMoviesInteractor(fetchNowPlayingMoviesOutputBoundary,fetchNowPlayingMoviesDataAccessInterface);
        final FetchNowPlayingMoviesController fetchNowPlayingMoviesController = new FetchNowPlayingMoviesController(fetchNowPlayingMoviesInteractor);
        loggedInViewModel.setFetchNowPlayingMoviesController(fetchNowPlayingMoviesController);
        return this;
    }

    /**
     * Adds the FetchPopularMovies Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFetchPopularMoviesUseCase() {
        final FetchPopularMoviesOutputBoundary fetchPopularMoviesOutputBoundary = new FetchPopularMoviesPresenter(viewManagerModel,loggedInViewModel);
        final FetchPopularMoviesInputBoundary fetchPopularMoviesInteractor = new FetchPopularMoviesInteractor(fetchPopularMoviesOutputBoundary,fetchPopularMoviesDataAccessInterface);
        final FetchPopularMoviesController fetchPopularMoviesController = new FetchPopularMoviesController(fetchPopularMoviesInteractor);
        loggedInViewModel.setFetchPopularMoviesController(fetchPopularMoviesController);
        return this;
    }




    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
