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

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        AuthData other = (AuthData) o;
        return (other.getUsername().equals(this.getUsername()) && this.getAuthToken().equals(other.getAuthToken()));
    }

    @Override
    public int hashCode() {
        return (this.username.hashCode() * this.authToken.hashCode());
    }
}
