package dataAccess.dataModelClasses;

public class userData {
    private final String username;
    private final String password;
    private final String email;
    public userData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    // empty constructor
    public userData() {
        this.username = null;
        this.password = null;
        this.email = null;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }
}
