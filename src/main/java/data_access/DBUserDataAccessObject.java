package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.User;
import entity.Movie;
import entity.MovieList;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import data_access.DBMovieListDataAccessObject;
import use_case.change_favorites.ChangeFavoritesUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.get_currentuser.GetCurrentUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.open_list.OpenListDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface , GetCurrentUserDataAccessInterface , ChangeFavoritesUserDataAccessInterface, OpenListDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MOVIELISTS = "movieLists";
    private static final String MESSAGE = "message";
    private static final String FAVMOVIE = "favoriteMovie";
    private static final String FAVDIRECTOR = "favoriteDirector";
    private final UserFactory userFactory;
    private final DBMovieListDataAccessObject dbMovieListDataAccessObject;

    private static String currentUsername;

    public DBUserDataAccessObject(UserFactory userFactory, DBMovieListDataAccessObject dbMovieListDataAccessObject) {
        this.userFactory = userFactory;
        this.dbMovieListDataAccessObject = dbMovieListDataAccessObject;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                final JSONObject infoObject = userJSONObject.getJSONObject("info");
                final String favMovie = infoObject.getString(FAVMOVIE);
                final String favDirector = infoObject.getString(FAVDIRECTOR);
                List<MovieList> movieLists = dbMovieListDataAccessObject.parseMovieLists(infoObject.getString(MOVIELISTS));

                return userFactory.create(name, password, favMovie, favDirector, movieLists);
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        currentUsername = name;
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void modifyInfo(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Create JSON body with user data
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();

        // Add username and password
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());


        // Add "info" object with favorite movie and favorite director
        JSONObject infoObject = new JSONObject();
        infoObject.put(FAVMOVIE, user.getFavMovie());
        infoObject.put(FAVDIRECTOR, user.getFavDirector());
        infoObject.put(MOVIELISTS, dbMovieListDataAccessObject.parseMovieListsToJSONArray(user.getMovieLists()));
        requestBody.put("info", infoObject);


        // Create request body for the HTTP call
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo") // Adjusted endpoint
                .method("PUT", body) // Use PUT for updating/saving user information
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            // Execute the request
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                System.out.println("User info saved successfully!");
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error saving user info", ex);
        }

    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public List<Movie> getMoviesForList(String listName) {
        try {
            String username = getCurrentUsername();
            User user = get(username);
            for (MovieList movieList : user.getMovieLists()) {
                if (movieList.getListName().equals(listName)) {
                    return movieList.getMovies();
                }
            }
        } catch (JSONException ex) {
            throw new RuntimeException("Error parsing the response for the movies list: " + listName, ex);
        }
        return new ArrayList<>();
    }
}
