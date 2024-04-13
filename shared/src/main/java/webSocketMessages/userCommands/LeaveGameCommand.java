package webSocketMessages.userCommands;

import chess.ChessGame;
import webSocketMessages.serverMessages.ServerMessage;

public class LeaveGameCommand extends  UserGameCommand{
    String message;
    int id;
    String visitorName;
    ChessGame.TeamColor playerColor;
    public LeaveGameCommand(String authToken, UserGameCommand.CommandType type,  int gameID) {
        super(authToken);
        commandType = CommandType.LEAVE;
        this.id = gameID;

        this.message = this.visitorName + " has left the game";
    }
    public String getMessage() {
        return this.message;
    }
    public String getPlayerColor() {
        if (playerColor == ChessGame.TeamColor.BLACK) {
            return "BLACK";
        } else if (playerColor == ChessGame.TeamColor.WHITE){
            return "WHITE";
        } else {
            return "observer";
        }    }
    public int getId() {
        return this.id;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
    public void setMessage(String playerName) {
        this.message = playerName + " has left the game";
    }
}
