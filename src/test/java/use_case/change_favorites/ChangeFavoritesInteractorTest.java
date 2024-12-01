package use_case.change_favorites;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeFavoritesInteractorTest {

    private static final String ORIGINAL_MOVIE = "Original Movie";
    private static final String ORIGINAL_DIRECTOR = "Original Director";
    private static final String UPDATED_MOVIE = "Updated Movie";
    private static final String UPDATED_DIRECTOR = "Updated Director";

    private InMemoryUserDataAccessObject userRepository;
    private ChangeFavoritesOutputBoundary outputBoundary;
    private ChangeFavoritesInteractor interactor;

    @BeforeEach
    void setUp() {
        // Initialize the user repository
        userRepository = new InMemoryUserDataAccessObject();

        // Output boundary mock to verify the interactor's behavior
        outputBoundary = new ChangeFavoritesOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeFavoritesOutputData data) {
                assertEquals("testUser", data.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Interactor should not fail in this test.");
            }
        };

        // Initialize the interactor
        UserFactory userFactory = new CommonUserFactory();
        interactor = new ChangeFavoritesInteractor(userRepository, outputBoundary, userFactory);
    }

    @Test
    void testSuccessfulFavoriteUpdate() {
        // Add a user to the repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("testUser", "password", ORIGINAL_MOVIE, ORIGINAL_DIRECTOR);
        userRepository.save(user);

        // Prepare input data
        ChangeFavoritesInputData inputData = new ChangeFavoritesInputData(
                "testUser",
                "password",
                UPDATED_MOVIE,
                UPDATED_DIRECTOR
        );

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the user in the repository has updated favorites
        User updatedUser = userRepository.get("testUser");
        assertEquals(UPDATED_MOVIE, updatedUser.getFavMovie());
        assertEquals(UPDATED_DIRECTOR, updatedUser.getFavDirector());
    }

}
