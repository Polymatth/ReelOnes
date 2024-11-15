package use_case.movie_detail;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieDetailInteractor implements MovieDetailInputBoundary {

    private final MovieDetailOutputBoundary movieDetailPresenter;
    private final MovieDetailDataAccessInterface dataAccessInterface;

    public MovieDetailInteractor(MovieDetailOutputBoundary movieDetailOutputBoundary,
                                 MovieDetailDataAccessInterface dataAccessInterface) {
        this.movieDetailPresenter = movieDetailOutputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(MovieDetailInputData movieDetailInputData) {
        Movie movie = movieDetailInputData.getMovie();
        movie.setDirector(dataAccessInterface.getDirector(movie.getID()));
        Map<Integer, String> genres = dataAccessInterface.getGenres();
        List<String> genreList = new ArrayList<>();
        for (int genreId : movie.getGenre_ids()) {
            genreList.add(genres.get(genreId));
        }
        movie.setGenres(genreList);
        final MovieDetailOutputData movieDetailOutputData = new MovieDetailOutputData(movie);
        this.movieDetailPresenter.prepareSuccessView(movieDetailOutputData);
        //note: no prepareFailView because there is no way for this use case to fail, since the user will only click on
        //a movie if it is part of a list and it would only be on a list if it is a movie we have necessary info for.
    }

}
