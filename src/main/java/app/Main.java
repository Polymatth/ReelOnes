package app;

import javax.swing.JFrame;
import java.io.IOException;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) throws IOException {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder

                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addSearchMovieView()
                                            .addMovieDetailView()
                                            .addUserProfileView()
                                            .addChangeFavoritesView()
                                            .addOpenListView()
                                            .addUserProfileView()
                                            .addFilterCategoryView()
                                            .addFilterCategoriesView()
                                            .addFetchNowPlayingMoviesUseCase()
                                            .addFetchPopularMoviesUseCase()
                                            .addChangePasswordView()
                                            .addGetCurrentUserUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addGoProfileUseCase()
                                            .addChangeFavoritesUseCase()
                                            .addChangePasswordUseCase()
                                            .addSearchMovieUseCase()
                                            .addMovieDetailUseCases()
                                            .addAddMovieUseCase()
                                            .addMovieListUseCase()
                                            .addOpenListUseCase()
                                            .addCreateRecommendationUseCase()
                                            .addFilterCategoriesUseCases()
                                            .addFilterCategoryUseCases()

                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
