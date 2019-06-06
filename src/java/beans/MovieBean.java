package beans;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

@Named("movieBean")
@SessionScoped
//The class represents the list of films on the site.
//With the help of the class you can create lists of different types of movies
//You can also perform different operations in the movies table
public class MovieBean implements Serializable {
    
    private ArrayList<Movie> movies;
    private ArrayList<Movie> drama;
    private ArrayList<Movie> comedy;
    private ArrayList<Movie> fantasy;
    private ArrayList<Movie> romance;
    private ArrayList<Movie> thriller;
    private ArrayList<Movie> action;
    private ArrayList<Movie> animation;
    private ArrayList<Review> reviews;
    
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
    
    private  String msgRev;
    
    //constructor
    public MovieBean()throws SQLException, ClassNotFoundException{
        movies = new ArrayList<Movie>();
        drama = new ArrayList<Movie>();
        comedy = new ArrayList<Movie>();
        fantasy = new ArrayList<Movie>();
        romance = new ArrayList<Movie>();
        thriller = new ArrayList<Movie>();
        action = new ArrayList<Movie>();
        animation = new ArrayList<Movie>();
        //Create a connection to the driver
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            
            String sql = "SELECT * FROM YEHUDA.MOVIES";
            String sql1 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%drama%'";
            String sql2 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%comedy%'";
            String sql3 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%fantasy%'";
            String sql4 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%romance%'";
            String sql5 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%thriller%'";
            String sql6 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%action%'";
            String sql7 ="SELECT * FROM YEHUDA.MOVIES WHERE GENRE LIKE '%animation%'";
            //Create a PreparedStatement for all lists
            PreparedStatement st=con.prepareStatement(sql);
            PreparedStatement st1=con.prepareStatement(sql1);
            PreparedStatement st2=con.prepareStatement(sql2);
            PreparedStatement st3=con.prepareStatement(sql3);
            PreparedStatement st4=con.prepareStatement(sql4);
            PreparedStatement st5=con.prepareStatement(sql5);
            PreparedStatement st6=con.prepareStatement(sql6);
            PreparedStatement st7=con.prepareStatement(sql7);
            
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs1=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs2=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs3=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs4=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs5=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs6=RowSetProvider.newFactory().createCachedRowSet();
            CachedRowSet rs7=RowSetProvider.newFactory().createCachedRowSet();
            
            rs.populate(st.executeQuery());
            rs1.populate(st1.executeQuery());
            rs2.populate(st2.executeQuery());
            rs3.populate(st3.executeQuery());
            rs4.populate(st4.executeQuery());
            rs5.populate(st5.executeQuery());
            rs6.populate(st6.executeQuery());
            rs7.populate(st7.executeQuery());
            //Build all lists
            while(rs.next()){
                movies.add(new Movie(rs.getInt("movieid"),rs.getString("moviename"),rs.getInt("minutes"),
                rs.getString("genre"),rs.getString("director"),rs.getInt("units"),rs.getInt("unitsused"),
                rs.getString("description"),rs.getInt("release"),rs.getString("image"),rs.getString("link")));
            }
            
            while(rs1.next()){
                drama.add(new Movie(rs1.getInt("movieid"),rs1.getString("moviename"),rs1.getInt("minutes"),
                rs1.getString("genre"),rs1.getString("director"),rs1.getInt("units"),rs1.getInt("unitsused"),
                rs1.getString("description"),rs1.getInt("release"),rs1.getString("image"),rs1.getString("link")));
            }
            
            while(rs2.next()){
                comedy.add(new Movie(rs2.getInt("movieid"),rs2.getString("moviename"),rs2.getInt("minutes"),
                rs2.getString("genre"),rs2.getString("director"),rs2.getInt("units"),rs2.getInt("unitsused"),
                rs2.getString("description"),rs2.getInt("release"),rs2.getString("image"),rs2.getString("link")));
            }
            
            while(rs3.next()){
                fantasy.add(new Movie(rs3.getInt("movieid"),rs3.getString("moviename"),rs3.getInt("minutes"),
                rs3.getString("genre"),rs3.getString("director"),rs3.getInt("units"),rs3.getInt("unitsused"),
                rs3.getString("description"),rs3.getInt("release"),rs3.getString("image"),rs3.getString("link")));
            }
            
            while(rs4.next()){
                romance.add(new Movie(rs4.getInt("movieid"),rs4.getString("moviename"),rs4.getInt("minutes"),
                rs4.getString("genre"),rs4.getString("director"),rs4.getInt("units"),rs4.getInt("unitsused"),
                rs4.getString("description"),rs4.getInt("release"),rs4.getString("image"),rs4.getString("link")));
            }
            
            while(rs5.next()){
                thriller.add(new Movie(rs5.getInt("movieid"),rs5.getString("moviename"),rs5.getInt("minutes"),
                rs5.getString("genre"),rs5.getString("director"),rs5.getInt("units"),rs5.getInt("unitsused"),
                rs5.getString("description"),rs5.getInt("release"),rs5.getString("image"),rs5.getString("link")));
            }
            
            while(rs6.next()){
                action.add(new Movie(rs6.getInt("movieid"),rs6.getString("moviename"),rs6.getInt("minutes"),
                rs6.getString("genre"),rs6.getString("director"),rs6.getInt("units"),rs6.getInt("unitsused"),
                rs6.getString("description"),rs6.getInt("release"),rs6.getString("image"),rs6.getString("link")));
            }
            
            while(rs7.next()){
                animation.add(new Movie(rs7.getInt("movieid"),rs7.getString("moviename"),rs7.getInt("minutes"),
                rs7.getString("genre"),rs7.getString("director"),rs7.getInt("units"),rs7.getInt("unitsused"),
                rs7.getString("description"),rs7.getInt("release"),rs7.getString("image"),rs7.getString("link")));
            }
        }finally{
            con.close();//Close the connection
        }
    }
    //getter and setter
    public ArrayList<Movie> getMovies(){
        return movies;
    }
    
    public ArrayList<Movie> getDrama(){
        return drama;
    }
    
    public ArrayList<Movie> getComedy(){
        return comedy;
    }
    
    public ArrayList<Movie> getFantasy(){
        return fantasy;
    }
    
    public ArrayList<Movie> getRomance(){
        return romance;
    }
    
    public ArrayList<Movie> getThriller(){
        return thriller;
    }
    
    public ArrayList<Movie> getAction(){
        return action;
    }
    
    public ArrayList<Movie> getAnimation(){
        return animation;
    }
    
    public ArrayList<Review> getReviews(){
        return reviews;
    }

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
    
    public int getAvailable(){
        return this.units - this.unitsused;
    }
    
    public String getMsgRev(){
        return msgRev;
    }
    
    public void setMsgRev(String msgRev) {
        this.msgRev = msgRev;
    }
   //Moves the manager from the movie list page to a description of the selected movie
    public String descMovie(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

        this.description = params.get("desc");
        return "/pages/adminPages/descPage";
    }
     
    public Movie getCurrentMovie(){
        return new Movie(this.movieid,this.moviename,this.minutes,this.genre,this.director,
                        this.units,this.unitsused,this.description,this.release,this.image,this.link);
    }
     //Saving changes made to movies information 
    public String saveAction() throws ClassNotFoundException, SQLException, FileNotFoundException{
        for(Movie movie : movies){
            movie.setEditable(false);
            Connection con = null;
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
                String sql = "UPDATE YEHUDA.MOVIES "
                        + "SET MOVIENAME=?, MINUTES=?, UNITSUSED=?, DESCRIPTION=?, RELEASE=?, IMAGE=?, GENRE=?, DIRECTOR=?, UNITS=?, LINK=? "
                        + "WHERE MOVIEID=?";
                PreparedStatement st=con.prepareStatement(sql);
                
                st.setString(1, movie.getMoviename());
                st.setInt(2, movie.getMinutes());
                st.setInt(3, movie.getUnitsused());
                st.setString(4, movie.getDescription());
                st.setInt(5,movie.getRelease());
                st.setString(6, movie.getImage());
                st.setString(7, movie.getGenre());
                st.setString(8, movie.getDirector());
                st.setInt(9, movie.getUnits());
                st.setString(10, movie.getLink() );
                st.setInt(11, movie.getMovieid() );
                
                st.executeUpdate();
            }finally{
                con.close();
            }
        }
        return null;
    }
    //In order to allow changes
    public String editAction(Movie movie){
        movie.setEditable(true);
        return null;
    }
    //Adds a movie to the list and updates the database
    public String addAction() throws ClassNotFoundException, SQLException, FileNotFoundException{
        Movie movie = new Movie(this.movieid,this.moviename,this.minutes,this.genre,this.director,this.units,
                                this.unitsused,this.description,this.release,this.image,this.link);
        if (exists (this.movieid)) return "/pages/error/errorMovie";
        
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "INSERT INTO YEHUDA.MOVIES (MOVIEID,MOVIENAME,MINUTES,GENRE,DIRECTOR,UNITS,"
                    + "UNITSUSED,DESCRIPTION,RELEASE,IMAGE,LINK)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            
                st.setInt(1, movie.getMovieid() );
                st.setString(2, movie.getMoviename());
                st.setInt(3, movie.getMinutes());
                st.setString(4, movie.getGenre());
                st.setString(5, movie.getDirector());
                st.setInt(6, movie.getUnits());
                st.setInt(7, movie.getUnitsused());
                st.setString(8, movie.getDescription());
                st.setInt(9,movie.getRelease());
                st.setString(10, movie.getImage());
                st.setString(11, movie.getLink() );
                
                st.executeUpdate();
        }finally{
            con.close();
        }
        return "/pages/adminPages/moviesManagement";
    }
   //Check whether there is a movie with the same ID number 
    public boolean exists(int movieid) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql= "SELECT * FROM YEHUDA.MOVIES WHERE MOVIEID=? ";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, movieid);
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(st.executeQuery());
            if(rs.next()){
                return true;
            }
        }finally{
            con.close();
        }
        return false;
    }
    //Deleting an movie and updating the database
    public String deleteAction(Movie movie) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "DELETE FROM YEHUDA.MOVIES WHERE MOVIEID=?";
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setInt(1, movie.getMovieid());
            st.executeUpdate();
        }finally{
            con.close();
        }
        movies.remove(movie);
        return null;
    }
    //Takes the user from the movie list to the selected movie
    public String movieDetails(Movie movie){
        setMovieid(movie.getMovieid());
        setMoviename(movie.getMoviename());
        setMinutes(movie.getMinutes());
        setGenre(movie.getGenre());
        setDirector(movie.getDirector());
        setUnits(movie.getUnits());
        setUnitsused(movie.getUnitsused());
        setDescription(movie.getDescription());
        setRelease(movie.getRelease());
        setImage(movie.getImage());
        setLink(movie.getLink());
        
        return "/pages/userPages/movieDetails";
    }
    //Builds and returns the list of responses to the various films
    public String movieReviews() throws ClassNotFoundException, SQLException{
        reviews = new ArrayList<Review>();
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "SELECT*FROM YEHUDA.REVIEWS WHERE MOVIEID=" + this.movieid;
            PreparedStatement st=con.prepareStatement(sql);
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(st.executeQuery());
            while(rs.next()){
                reviews.add(new Review(rs.getString("username"), rs.getString("review")));
            }
        }finally{
            con.close();
        }
        
        return "/pages/userPages/movieReviews";
    }
    //Add a comment and update the database
    public String addReview() throws ClassNotFoundException, SQLException{
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> param = fc.getExternalContext().getRequestParameterMap();
        Review r = new Review(param.get("username"),this.msgRev);
        
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "INSERT INTO YEHUDA.REVIEWS (USERNAME,MOVIEID,REVIEW)"
                    + " VALUES (?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setString(1, r.getUsername());
            st.setInt(2, this.movieid);
            st.setString(3, r.getRev() );
            
            st.executeUpdate();
        }finally{
            con.close();
        }
        return movieReviews();
    }
}
