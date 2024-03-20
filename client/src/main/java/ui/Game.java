package ui;
import com.google.gson.Gson;

public class Game {
    private String gameName;
    private String playerColor;
    private int gameID;

    public Game(String gameName) {
        this.gameName = gameName;
    }
    public String toString() {
        return new Gson().toJson(this);
    }

}

