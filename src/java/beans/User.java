
package beans;

//An object that represents a single user
public class User {
    
    private String userid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phon;
    private boolean editable;//For editing user data (via jsf file)
    //constructor
    public User(String userid, String firstname,String lastname,String username,
            String password,String email, String phon){
        this.userid=userid;
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.password=password;
        this.email=email;
        this.phon=phon;
    }
//setter and getter
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
    
    public boolean isEditable(){
        return editable;
    }
        
    public void setEditable(boolean editable){
        this.editable=editable;
    }
}
