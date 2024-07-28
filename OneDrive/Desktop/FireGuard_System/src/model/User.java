package Dao; // Assuming User class is in the same package as UserDAO

public class User {
    private String fullName;
    private String email;
    private String password;
    private String userType;

    public User(String fullName, String email, String password, String userType) {
        this.fullName = fullName;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}