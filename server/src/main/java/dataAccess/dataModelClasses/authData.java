package dataAccess.dataModelClasses;

public class authData {
    private final String username;
    private final String authToken;

    public authData(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
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
