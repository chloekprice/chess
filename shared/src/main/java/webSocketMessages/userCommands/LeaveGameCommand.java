package webSocketMessages.userCommands;

import chess.ChessGame;
import webSocketMessages.serverMessages.ServerMessage;

public class LeaveGameCommand extends UserGameCommand{
    String message;
    int gameID;
    String visitorName;
    public LeaveGameCommand(String authToken, UserGameCommand.CommandType type,  int gameID) {
        super(authToken);
        commandType = CommandType.LEAVE;
        this.gameID = gameID;
    }
    public String getMessage() {
        return this.message;
    }
    public int getId() {
        return this.gameID;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
    public void setMessage(String playerName) {
        this.visitorName = playerName;
        this.message = playerName + " has left the game";
    }
}
