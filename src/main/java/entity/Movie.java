package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Movie entity.
 */
public class Movie {

    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
    private int id;
    private String original_language;
    private String title;
    private String backdrop_path;
    private float popularity;
    private int vote_count;
    private Boolean video;
    private float vote_average;
    private List<String> genres;
    private List<String> streaming;
    private String director = "";

    public Movie(String poster_path, Boolean adult, String overview, String release_date,
                 List<Integer> genre_ids, int id, String original_language, String title, String backdrop_path,
                 float popularity, int vote_count, Boolean video, float vote_average) {
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
        this.genres = new ArrayList<>();
        this.streaming = new ArrayList<>();
    }

    //Getters

    public String getPosterPath() {
        return this.poster_path;
    }

    public String getOverview() {return this.overview;}

    public String getOriginal_language() {return this.original_language;}

    public String getReleaseDate() {
        return this.release_date;
    }

    public String getYear() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<4; i++) {
            result.append(this.release_date.charAt(i));
        }
        return result.toString();
    }

    public String getBackdropPath() {
        return this.backdrop_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Integer> getGenre_ids() {
        return this.genre_ids;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public List<String> getStreaming(){
        return this.streaming;
    }

    public String getDirector() {
        return this.director;
    }

    public int getID() {
        return this.id;
    }

    public int getVotecount() {
        return vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public boolean isAdult(){return this.adult;}

    public float getVoteAverage() {
        return this.vote_average;
    }

    //Setters

    public void setGenres(List<String> genreList) {
        this.genres = genreList;
    }

    public void setStreaming(List<String> streamingList) {
        this.streaming = streamingList;
    }

    public void setDirector(String dir) {
        this.director = dir;
    }
}