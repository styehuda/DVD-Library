
package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

@Named("search")
@SessionScoped
//The class represents a movie search using a search word
public class Search implements Serializable {
    
    private String word;
    private ArrayList<Movie> result;
//setter and getter
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Movie> getResult() {
        return result;
    }

    public void setResults(ArrayList<Movie> result) {
        this.result = result;
    }
    //Returns search results - list of found movies
    public String resultList() throws ClassNotFoundException, SQLException{
        result = new ArrayList<Movie>();
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql ="SELECT * FROM YEHUDA.MOVIES WHERE MOVIENAME LIKE '%" + this.word + "%'";
            PreparedStatement st=con.prepareStatement(sql);
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(st.executeQuery());
            while(rs.next()){
                result.add(new Movie(rs.getInt("movieid"),rs.getString("moviename"),rs.getInt("minutes"),
                rs.getString("genre"),rs.getString("director"),rs.getInt("units"),rs.getInt("unitsused"),
                rs.getString("description"),rs.getInt("release"),rs.getString("image"),rs.getString("link")));
            }
        }finally{
            con.close();
        }
        if (result.isEmpty()){
            return "/pages/error/errorSearch";
        }
        return "/pages/userPages/searchResult";
    }
    
}
