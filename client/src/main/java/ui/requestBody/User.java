package ui.requestBody;

import com.google.gson.Gson;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this(username, password, null);
    }
    public String toString() {
        return new Gson().toJson(this);
    }

}
