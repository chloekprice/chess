package server;

import dataAccess.dataModelClasses.authData;
import dataAccess.dataModelClasses.gameData;
import dataAccess.dataModelClasses.userData;

public class ResultInfo {
    private authData authData;
    private gameData gameData;
    private userData userData;
    private String message;
    private int status;
    private String username;
    private String password;
    private String email;
    private String authToken;
    public ResultInfo() {
        this.authData = null;
        this.gameData = null;
        this.userData = null;
        this.message = null;
        this.status = 200;
    }

    // setters
    public void setAuthData(authData authData) {
        this.authData = authData;
        this.username = authData.getUsername();
        this.authToken = authData.getAuthToken();
    }

    public void setGameData(gameData gameData) {
        this.gameData = gameData;
    }
    public void setUserData(userData userData) {
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

    // getters
    public authData getAuthData() {
        return authData;
    }

    public gameData getGameData() {
        return gameData;
    }

    public userData getUserData() {
        return userData;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
