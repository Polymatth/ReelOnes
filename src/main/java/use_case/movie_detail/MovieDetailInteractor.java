package use_case.movie_detail;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieDetailInteractor implements MovieDetailInputBoundary {

    private MovieDetailOutputBoundary movieDetailPresenter;
    private MovieDetailDataAccessInterface dataAccessInterface;

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
        //note: maybe no need to do preparesuccessview and preparefailview methods since there's no circumstance where
        // this would fail? doing it just to follow the format that is generally seen in clean architecture, but maybe
        // this should be changed.
        this.movieDetailPresenter.prepareFailView();
    }

}
