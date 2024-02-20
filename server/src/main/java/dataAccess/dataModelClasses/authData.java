package dataAccess.dataModelClasses;

public class authData {
    private final String username;
    private final String authToken;

    public authData(String username, String auth_token) {
        this.username = username;
        this.authToken = auth_token;
    }
    // empty constructor
    public authData() {
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
