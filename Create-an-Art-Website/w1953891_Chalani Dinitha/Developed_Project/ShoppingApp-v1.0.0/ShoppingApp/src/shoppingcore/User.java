package shoppingcore;

import java.util.Objects;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) 
            return false;
        
        User user = (User)other;
        return userName.equals(user.userName) && password.equals(user.password);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}