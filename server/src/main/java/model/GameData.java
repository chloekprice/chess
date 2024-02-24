package model;

import chess.ChessGame;

import java.util.HashSet;

public class GameData {
    private final int gameID;
    private  String whiteUsername;
    private  String blackUsername;
    private final ChessGame game;
    private HashSet<String> watchers;
    private String gameName;

    public GameData(int gameID, String whiteUsername, String blackUsername, ChessGame game, String gameName) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.game = game;
        this.watchers = new HashSet<String>();
        this.gameName = gameName;
    }
    // empty constructor
    public GameData() {
        this.gameID = 0;
        this.whiteUsername = "";
        this.blackUsername = "";
        this.game = null;
    }

    public void updateGame(String color, String user) {
        if (color.equals("BLACK")) {
            this.blackUsername = user;
        } else if (color.equals("WHITE")) {
            this.whiteUsername = user;
        } else {
            watchers.add(user);
        }
    }
    public int getGameID() {
        return this.gameID;
    }

    public String getWhiteUsername() {
        return this.whiteUsername;
    }

    public String getBlackUsername() {
        return this.blackUsername;
    }

    public ChessGame getGame() {
        return this.game;
    }

    public String getName() {
        return this.gameName;
    }
}
