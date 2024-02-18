package dataAccess.dataModelClasses;

public class authData {
    private final String username;
    private final String auth_token;

    public authData(String username, String auth_token) {
        this.username = username;
        this.auth_token = auth_token;
    }
    // empty constructor
    public authData() {
        this.username = null;
        this.auth_token = null;
    }
    public String getUsername() {
        return username;
    }

    public String getAuthToken() {
        return auth_token;
    }
}
