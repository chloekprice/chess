package model;

public class AuthData {
    private final String username;
    private final String authToken;

    public AuthData(String username, String auth_token) {
        this.username = username;
        this.authToken = auth_token;
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
