package dataAccess.dataModelClasses;

import chess.ChessGame;

public class gameData {
    private final int gameID;
    private final String whiteUsername;
    private final String blackUsername;
    private final ChessGame game;

    public gameData(int gameID, String whiteUsername, String blackUsername, ChessGame game) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.game = game;
    }
    // empty constructor
    public gameData() {
        this.gameID = 0;
        this.whiteUsername = null;
        this.blackUsername = null;
        this.game = null;
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
}
