package use_case.get_currentuser;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.MovieList;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetCurrentUserInteractorTest {

    private static final String FAVMOVIE = "Inception";
    private static final String FAVDIRECTOR = "Christopher Nolan";
    private final List<MovieList> movieListList = new ArrayList<>();

    @Test
    void testExecuteReturnsCurrentUser() {
        // Arrange
        InMemoryUserDataAccessObject dataAccessObject = new InMemoryUserDataAccessObject();
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", FAVMOVIE, FAVDIRECTOR, movieListList);
        dataAccessObject.save(user);
        dataAccessObject.setCurrentUsername("Paul");

        GetCurrentUserInteractor interactor = new GetCurrentUserInteractor(dataAccessObject, new MockPresenter());

        // Act
        GetCurrentUserOutputData output = interactor.execute();

        // Assert
        assertNotNull(output);
        assertEquals("Paul", output.getUsername());
        assertEquals(FAVMOVIE, output.getFavMovie());
        assertEquals(FAVDIRECTOR, output.getFavDirector());
        assertEquals("password", output.getPassword());
        assertEquals(movieListList, output.getMovieLists());
    }


    // Mock presenter for testing
    private static class MockPresenter implements GetCurrentOutputBoundary {
        @Override
        public void prepareSuccessView(GetCurrentUserOutputData outputData) {
            // Do nothing for mock
        }
    }
}