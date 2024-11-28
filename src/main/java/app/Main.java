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
                                            .addChangePasswordView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                .addGetCurrentUserUseCase()
                                            .addGoProfileUseCase()
                                            .addChangePasswordUseCase()
                                            .addSearchMovieUseCase()
                                            .addMovieDetailUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
