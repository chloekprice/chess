package webSocketMessages.serverMessages;


import chess.ChessGame;
import com.google.gson.Gson;

public class LoadGame extends ServerMessage {
    ChessGame game;
    int gameID;
    String message;
    public LoadGame(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
        this.gameID = game.getID();
        this.message = new Gson().toJson(game);
    }
    public ChessGame getServerGame() {
        return this.game;
    }
    public ServerMessageType serverMessageType() {
        return this.getServerMessageType();
    }
    public int getID() {
        return this.gameID;
    }
    public String getMessage() {
        return this.message;
    }

}