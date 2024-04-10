package ui.requestBody;
import chess.ChessGame;
import com.google.gson.Gson;

public class Game {
    private String gameName;
    private String playerColor;
    private int gameID;
    private ChessGame game;

    public Game(String gameName) {
        this.gameName = gameName;
    }
    public Game(String gameName, int gameID, String playerColor) {
        this.gameName = gameName;
        this.gameID = gameID;
        this.playerColor = playerColor;
    }
    public Game (int gameID, ChessGame game) {
        this.gameID = gameID;
        this.game = game;
    }
    public String toString() {
        return new Gson().toJson(this);
    }

}

