package server;

import dataAccess.dataModelClasses.AuthData;
import dataAccess.dataModelClasses.GameData;
import dataAccess.dataModelClasses.UserData;

import java.util.HashSet;

public class ResultInfo {
    private AuthData authData;
    private GameData gameData;
    private UserData userData;
    private String message;
    private int status;
    private String username;
    private String password;
    private String email;
    private String authToken;
    private Integer gameID = null;
    private HashSet<GameData> games;
    public ResultInfo() {
        this.authData = null;
        this.gameData = null;
        this.userData = null;
        this.message = null;
        this.status = 200;
    }

    // setters
    public void setAuthData(AuthData authData) {
        this.authData = authData;
        this.username = authData.getUsername();
        this.authToken = authData.getAuthToken();
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
        this.gameID = gameData.getGameID();
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
        this.username = userData.getUsername();
        this.password = userData.getPassword();
        this.email = userData.getEmail();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status_code) {
        this.status = status_code;
    }
    public HashSet<GameData> getGames() {
        return games;
    }

    // getters
    public AuthData getAuthData() {
        return authData;
    }

    public GameData getGameData() {
        return gameData;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGames(HashSet<GameData> games) {
        this.games = games;
    }
}
