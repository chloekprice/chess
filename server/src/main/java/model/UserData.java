package model;

import chess.ChessMove;

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

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        UserData other = (UserData) o;
        return (other.getUsername().equals(this.getUsername()) && other.getPassword().equals(this.getPassword()) && other.getEmail().equals(this.getEmail()));
    }

    @Override
    public int hashCode() {
        return (this.username.hashCode() * this.email.hashCode() * this.password.hashCode());
    }
}
