
package beans;

//An object that represents a single review
public class Review {
    String username;
    String rev;
    //constructor
    public Review(String username, String rev){
        this.username=username;
        this.rev=rev;
    }
    //setter and getter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
    
    
}
