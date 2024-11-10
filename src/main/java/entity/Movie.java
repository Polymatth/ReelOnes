package entity;

import java.util.List;

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
    }
}