package server;

import chess.ChessBoard;
import chess.ChessGame;
import model.AuthData;
import model.GameData;
import model.UserData;

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
    private String gameName;
    private String whiteUsername;
    private String blackUsername;
    private ChessGame game;
    public ResultInfo() {
        this.authData = null;
        this.gameData = null;
        this.userData = null;
        this.message = null;
        this.status = 200;
    }

    public void updateGame(ChessGame game) {
        this.game = game;
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
        this.gameName = gameData.getName();
        this.whiteUsername = gameData.getWhiteUsername();
        this.blackUsername = gameData.getBlackUsername();
        this.game = gameData.getGame();
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
    public void setGames(HashSet<GameData> games) {
        this.games = games;
    }

    public void setStatus(int status_code) {
        this.status = status_code;
    }

    // getters
    public AuthData getAuthData() {
        return authData;
    }

    public int getStatus() {
        return status;
    }
    public int getGameID() {
        return gameID;
    }
    public String getGameName() {
        return this.gameName;
    }
    public String getMessage() {
        return this.message;
    }
    public HashSet<GameData> getGamesList(){
        return this.games;
    }
    public ChessGame getGame() {
        return this.game;
    }
}
