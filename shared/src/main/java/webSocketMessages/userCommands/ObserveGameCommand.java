package webSocketMessages.userCommands;

import chess.ChessGame;

public class ObserveGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    int gameID;
    ChessGame game;
    public ObserveGameCommand(String authToken, CommandType type,  int gameID) {
        super(authToken);
        commandType = CommandType.JOIN_OBSERVER;
        this.gameID = gameID;

    }
    public String getMessage() {
        return this.message;
    }
    public int getID() {
        return this.gameID;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
    public ChessGame getGame() {
        return this.game;
    }
    public void setGame(ChessGame game) {
        this.game = game;
    }
    public void setMessage(String visitorName) {
        this.visitorName =visitorName;
        this.message = visitorName + " has joined the game as an observer";
    }
}
