package ui;
import com.google.gson.Gson;

public class Game {
    private String gameName;
    private String playerColor;
    private int gameID;

    public Game(String gameName) {
        this.gameName = gameName;
    }
    public Game(String gameName, int gameID, String playerColor) {
        this.gameName = gameName;
        this.gameID = gameID;
        this.playerColor = playerColor;
    }
    public String toString() {
        return new Gson().toJson(this);
    }

}

