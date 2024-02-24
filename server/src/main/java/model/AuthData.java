package model;

public class AuthData {
    private final String username;
    private final String authToken;

    public AuthData(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }
    // empty constructor
    public AuthData() {
        this.username = null;
        this.authToken = null;
    }
    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return authToken;
    }
}
