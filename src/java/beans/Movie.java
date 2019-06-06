
package beans;

//An object that represents a single movie

public class Movie {
    
    private int movieid;
    private String moviename;
    private int minutes;
    private String genre;
    private String director;
    private int units;
    private int unitsused;
    private String description;
    private int release;
    private String image;
    private String link;
    private boolean editable;//For editing movie data (via jsf file)
    //constructor
    public Movie(int movieid, String moviename, int minutes, String genre, String director,
            int units, int unitsused, String description, int release, String image, String link){
        this.movieid=movieid;
        this.moviename=moviename;
        this.minutes=minutes;
        this.genre=genre;
        this.director=director;
        this.units=units;
        this.unitsused=unitsused;
        this.description=description;
        this.release=release;
        this.image= image;
        this.link= link;
    }
//setter and getter
    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getUnitsused() {
        return unitsused;
    }

    public void setUnitsused(int unitsused) {
        this.unitsused = unitsused;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public boolean isEditable(){
        return editable;
    }
        
    public void setEditable(boolean editable){
        this.editable=editable;
    }
}
