package beans;

import beans.User;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

@Named("userBean")
@SessionScoped
//The class represents the list of users on the site.
//You can also perform different operations in the users table
public class UserBean implements Serializable{
    
    private ArrayList<User> users;
    private ArrayList<Order> history;
    private String userid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phon;
    private boolean editable;
    //constructor
    public UserBean() throws SQLException, ClassNotFoundException{
        
        users=new ArrayList<User>();
        //Create a connection to the driver
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            //Create a PreparedStatement
            PreparedStatement getUsers=con.prepareStatement("SELECT * FROM YEHUDA.USERS");
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(getUsers.executeQuery());
            //build the list
            while (rs.next()){
                users.add(new User(rs.getString("user_id"),rs.getString("firstname"),rs.getString("lastname"),
                rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("phon")));            }
        }finally{
            con.close();//Close the connection
        }
    }
//getter and setter
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }
    
    
     public ArrayList<User> getUsers(){
        return users;
    }
    
    public boolean isEditable(){
        return editable;
    }
    
    public void setEditable(boolean editable){
        this.editable = editable;
    }
    
    public User getCurrentUser(){
        return new User(this.userid,this.firstname,this.lastname,this.username,
                        this.password,this.email,this.password);
    }
    //In order to allow changes by the user
    public String editAction(){
        setEditable(true);
        return null;
    }
    //In order to allow changes by the manager
    public String editAction(User user){
        user.setEditable(true);
        return null;
    }
    //Save changes made by the administrator to user details
    public String saveAction(String admin) throws ClassNotFoundException, SQLException{
        for (User user : users){
            user.setEditable(false);
            Connection con = null;
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
                String sql = "UPDATE YEHUDA.USERS "
                        + "SET FIRSTNAME=?, LASTNAME=?, USERNAME=?, PASSWORD=?, EMAIL=?, PHON=? "
                        + "WHERE USER_ID=?";
                PreparedStatement st=con.prepareStatement(sql);
                
                st.setString(1, user.getFirstname());
                st.setString(2, user.getLastname());
                st.setString(3, user.getUsername());
                st.setString(4, user.getPassword());
                st.setString(5, user.getEmail());
                st.setString(6, user.getPhon());
                st.setString(7, user.getUserid() );
            
                st.executeUpdate();
            }finally{
                con.close();
            }
        }
        return null;
    }
    //Save changes made by the user
    public String saveAction() throws ClassNotFoundException, SQLException{
        setEditable(false);
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "UPDATE YEHUDA.USERS "
                       + "SET FIRSTNAME=?, LASTNAME=?, USERNAME=?, PASSWORD=?, EMAIL=?, PHON=? "
                       + "WHERE USER_ID=?";
            PreparedStatement st=con.prepareStatement(sql);
                
            st.setString(1, getFirstname());
            st.setString(2, getLastname());
            st.setString(3, getUsername());
            st.setString(4, getPassword());
            st.setString(5, getEmail());
            st.setString(6, getPhon());
            st.setString(7, getUserid() );
                    
            st.executeUpdate();
        }finally{
            con.close();
        }
        return null;
    }
    //Adds a user to the list and updates the database
    public String addAction(String name) throws ClassNotFoundException, SQLException{
        User user = new User(this.userid,this.firstname,this.lastname,this.username,
                                this.password,this.email,this.phon);
        if (((exists (this.userid,this.username,this.password)) || (this.username.equals("admin"))) ||
                (this.username.equals("123456")))
            return "/pages/error/errorAccount";
        
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "INSERT INTO YEHUDA.USERS (USER_ID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD,EMAIL,PHON) "
                        + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
              
                st.setString(1, user.getUserid() );
                st.setString(2, user.getFirstname());
                st.setString(3, user.getLastname());
                st.setString(4, user.getUsername());
                st.setString(5, user.getPassword());
                st.setString(6, user.getEmail());
                st.setString(7, user.getPhon());
                
                st.executeUpdate();
        }finally{
            con.close();
        }
        if (name.equals("admin")){
            return "/pages/adminPages/ordersManagement";
        }
        else return "/pages/userPages/homePage";  
    }
    //Check whether there is a user with the same data
    public boolean exists(String userid, String username, String password) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql= "SELECT * FROM YEHUDA.USERS WHERE USER_ID=? OR USERNAME= ? OR PASSWORD = ? ";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, userid);
            st.setString(2, username);
            st.setString(3, password);
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
    //Deleting an user and updating the database
    public String deleteAction(User user) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "DELETE FROM YEHUDA.USERS WHERE USER_ID=?";
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setString(1, user.getUserid());
            st.executeUpdate();
        }finally{
            con.close();
        }
        users.remove(user);
        return null;
    }
    //The user is logged in, and the user will then be redirected to the appropriate page
    //(administrator - admin site, user - home page, otherwise - error)
    public String login() throws ClassNotFoundException, SQLException{
        if ((this.username.equals("admin")) && (this.password.equals("123456"))){
            return "/pages/adminPages/adminHomePage";
        }else{
            if(validate(this.username,this.password)){
                Connection con = null;
                try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
                    PreparedStatement st=con.prepareStatement("SELECT * FROM YEHUDA.USERS WHERE USERNAME= ?");
                    st.setString(1, this.username);
                    CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
                    rs.populate(st.executeQuery());
                    if (rs.next()){
                        setFirstname(rs.getString("firstname"));
                        setLastname(rs.getString("lastname"));
                        setUsername(rs.getString("username"));
                        setPassword(rs.getString("password"));
                        setEmail(rs.getString("email"));
                        setPhon(rs.getString("phon"));
                        setUserid(rs.getString("user_id"));
                    }
                }finally{
                     con.close();
                }
                return "/pages/userPages/homePage";
            }else{
                return "/pages/error/errorLogin";
            }
        }
    }
    //Check if the username and password are valid
    public boolean validate(String username, String password) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql= "SELECT * FROM YEHUDA.USERS WHERE USERNAME= ? AND PASSWORD = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
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
    //Returns the user's borrowing history
    public ArrayList getHistory()throws ClassNotFoundException, SQLException{
        history = new ArrayList(); 
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            PreparedStatement st=con.prepareStatement("SELECT * FROM YEHUDA.ORDERS WHERE USERNAME= '" + this.username +"'");
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(st.executeQuery());
            while(rs.next()){
                history.add(new Order(rs.getInt("orderid"),rs.getString("username"),rs.getString("userid"),rs.getInt("movieid"),
                    rs.getString("moviename"),new SimpleDateFormat("yyyy-mm-dd").format(rs.getDate("orderdate").getTime())
                            ,rs.getString("returned")));
            }
        }finally{
            con.close();
        }
        return history;
    }
    //Directs only users  to personal area (not managers)
    public String userPage(){
        if (this.username.equals("admin")){
            return "/pages/error/adminError";
        }
        return "/pages/userPages/userPage";
    }
}
