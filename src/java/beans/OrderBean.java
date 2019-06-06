package beans;

import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

@Named("orderBean")
@SessionScoped
//The class represents the list of orders on the site.
//You can also perform different operations in the orders table
public class OrderBean implements Serializable{
    
    private ArrayList<Order> orders;
    private Order currentOrder;
    
    private int orderid;
    private String userid;
    private int movieid;
    private String moviename;
    private String orderdate;
    private String returned;
    private String username;
    //constructor
    public OrderBean() throws ClassNotFoundException, SQLException{
        orders = new ArrayList<Order>();
        //Create a connection to the driver
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            //Create a PreparedStatement
            PreparedStatement getOrders=con.prepareStatement("SELECT * FROM YEHUDA.ORDERS");
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(getOrders.executeQuery());
            //build the list
            while(rs.next()){
                orders.add(new Order(rs.getInt("orderid"),rs.getString("username"),rs.getString("userid"),rs.getInt("movieid"),
                    rs.getString("moviename"),new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("orderdate").getTime())
                    ,rs.getString("returned")));
            }
        }finally{
            con.close();//Close the connection
        }
    }
//getter and setter
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }
    
    public String getMoviename(){
        return moviename;
    }
    
    public void setMoviename(String moviename){
        this.moviename=moviename;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate)  {
        this.orderdate = orderdate;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
    
    public Order getCurrentOrder(){
        return currentOrder;
    }
    
    public void setCurrentOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }
    
    public ArrayList<Order> getOrders(){
        return orders;
    }
    //Saving changes made to orders information 
    public String saveAction() throws ClassNotFoundException, SQLException, ParseException{
        for(Order order : orders){
            order.setEditable(false);
            Connection con = null;
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
                String sql = "UPDATE YEHUDA.ORDERS "
                            + "SET MOVIEID=?, USERID=?, ORDERDATE=?, RETURNED=?, USERNAME=?, MOVIENAME=? "
                            + "WHERE ORDERID=?";
                
                PreparedStatement st=con.prepareStatement(sql);
                
                st.setInt(1, order.getMovieid());
                st.setString(2, order.getUserid());
                st.setDate(3, java.sql.Date.valueOf(order.getOrderdate()));
                st.setString(4, order.getReturned());
                st.setString(5, order.getUsername());
                st.setString(6, order.getMoviename());
                st.setInt(7, order.getOrderid());
                
                st.executeUpdate();
                
            }finally{
                con.close();
            }
        }
        return null;
    }
    //In order to allow changes
    public String editAction(Order order){
        order.setEditable(true);
        return null;
    }
    //Adds a order to the list and updates the database
    public String addAction(String name) throws ClassNotFoundException, SQLException, ParseException{
        Order order = new Order(this.orderid,this.username,this.userid,this.movieid,this.moviename,
                this.orderdate,this.returned);
        
        if (exists (this.orderid)) return "/pages/error/errorOrder";
        
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "INSERT INTO YEHUDA.ORDERS (ORDERID,MOVIEID,USERID,ORDERDATE,RETURNED,USERNAME,MOVIENAME) "
                        + "VALUES (?,?,?,?,?,?,?)";
            String sql2 = "UPDATE YEHUDA.MOVIES SET UNITSUSED = UNITSUSED + 1 WHERE MOVIEID = ?";
            
            PreparedStatement st=con.prepareStatement(sql);
            PreparedStatement st2=con.prepareStatement(sql2);
              
            st.setInt(1, order.getOrderid());
            st.setInt(2, order.getMovieid());
            st.setString(3, order.getUserid());
            st.setDate(4, java.sql.Date.valueOf(order.getOrderdate()));
            st.setString(5,order.getReturned() );
            st.setString(6, order.getUsername());
            st.setString(7, order.getMoviename());
            st2.setInt(1, order.getMovieid());
                
            st.executeUpdate();
            st2.executeUpdate();
        }finally{
            con.close();
        }
        if (name.equals("admin")){
            return "/pages/adminPages/ordersManagement";
        }
        else return "/pages/userPages/homePage";
    }
    ////Check whether there is a order with the same ID number 
    public boolean exists(int orderid) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql= "SELECT * FROM YEHUDA.ORDERS WHERE ORDERID=? ";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, orderid);
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
    //Deleting an order and updating the database
    public String deleteAction(Order order) throws ClassNotFoundException, SQLException{
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "DELETE FROM YEHUDA.ORDERS WHERE ORDERID=?";
            PreparedStatement st=con.prepareStatement(sql);
            
            st.setInt(1, order.getOrderid());
            st.executeUpdate();
        }finally{
            con.close();
        }
        orders.remove(order);
        return null;
    }
    //Create a new order based on the user's details and the selected movie details
    public String newOrder() throws ClassNotFoundException, SQLException{
        ArrayList<Integer>ids = new ArrayList<Integer>();
        int id = 1;
        Connection con = null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/movies","yehuda","1984");
            String sql = "SELECT ORDERID FROM YEHUDA.ORDERS";
            PreparedStatement st=con.prepareStatement(sql);
            CachedRowSet rs=RowSetProvider.newFactory().createCachedRowSet();
            rs.populate(st.executeQuery());
            while(rs.next()){
                ids.add(rs.getInt("orderid"));
            }
        }finally{
            con.close();
        }
        while (ids.contains((Integer)id)){
            id = new Random().nextInt(1000);
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

        this.orderid=id;
        this.moviename = params.get("moviename");
        this.movieid = Integer.parseInt((String)params.get("movieid"));
        this.userid = params.get("userid");
        this.username = params.get("username");
        this.orderdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.returned = "n";
        this.currentOrder = new Order(id,params.get("username"),params.get("userid"),
                Integer.parseInt((String)params.get("movieid")),params.get("moviename"),
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"n");
        int available = Integer.parseInt((String)params.get("available"));
        if (this.username.equals("admin")){
            return "/pages/error/adminError";
        }
        if (available == 0){
            return "/pages/error/noMoviesAvailable";
        }
        return "/pages/userPages/newOrder";
    }
}
