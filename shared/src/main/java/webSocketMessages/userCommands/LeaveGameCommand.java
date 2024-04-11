package webSocketMessages.userCommands;

import chess.ChessGame;

public class LeaveGameCommand extends  UserGameCommand{
    String message;
    int id;
    String visitorName;
    ChessGame.TeamColor playerColor;
    public LeaveGameCommand(String authToken, String visitorName, int gameID, ChessGame.TeamColor playerColor) {
        super(authToken);
        commandType = CommandType.LEAVE;
        id = gameID;
        this.visitorName = visitorName;
        this.playerColor = playerColor;

        this.message = this.visitorName + " has left the game";
    }
    public String getMessage() {
        return this.message;
    }
    public String getPlayerColor() {
        return this.playerColor.toString();
    }
    public int getId() {
        return this.id;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
}
