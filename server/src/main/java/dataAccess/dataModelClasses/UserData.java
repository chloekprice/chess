package dataAccess.dataModelClasses;

public class UserData {
    private final String username;
    private final String password;
    private final String email;
    public UserData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    // empty constructor
    public UserData() {
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
