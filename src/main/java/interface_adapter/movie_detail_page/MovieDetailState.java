package interface_adapter.movie_detail_page;

/**
 * The state for the Movie Detail View Model
 */
public class MovieDetailState {

    private String title = "";
    private String genre = "";
    private String director = "";
    private String year = "";
    private String streamingServices = "";
    private String posterImagePath = "";
    private String originView;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStreamingServices(String streamingServices) {
        this.streamingServices = streamingServices;
    }

    public void setPosterImagePath(String path) {
        this.posterImagePath = path;
    }

    public String getTitle() {
        return this.title;
    }

    public String getYear() {
        return this.year;
    }

    public String getGenre() {
        if (this.genre.equals("")) {
            return "Unknown";
        }
        return this.genre;
    }

    public String getDirector() {
        if (this.director.equals("")) {
            return "Unknown";
        }
        return this.director;
    }

    public String getStreamingServices() {
        if (this.streamingServices.equals("")) {
            return "Unavailable";
        }
        return this.streamingServices;
    }

    public String getPosterImagePath() {
        return this.posterImagePath;
    }

    public String getOriginView() {
        return this.originView;
    }

    public void setOriginView(String originView) {
        this.originView = originView;
    }
}
