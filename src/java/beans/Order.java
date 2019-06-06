
package beans;

//An object that represents a single order


public class Order {
    
    private int orderid;
    private String username;
    private String userid;
    private int movieid;
    private String moviename;
    private String orderdate;
    private String returned;
    private boolean editable;//For editing order data (via jsf file)
    //constructor
    public Order(int orderid,String username,String userid,int movieid,String moviename,
            String orderdate, String returned){
        this.orderid=orderid;
        this.username=username;
        this.userid=userid;
        this.movieid=movieid;
        this.moviename=moviename;
        this.orderdate=orderdate;
        this.returned=returned;
    }
//setter and getter
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

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
    
    public boolean isEditable(){
        return editable;
    }
        
    public void setEditable(boolean editable){
        this.editable=editable;
    }
}
