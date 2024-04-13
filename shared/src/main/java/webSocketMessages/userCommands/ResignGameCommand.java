package webSocketMessages.userCommands;

import chess.ChessGame;

public class ResignGameCommand extends  UserGameCommand {
    String message;
    int gameID;
    String visitorName;
    ChessGame.TeamColor playerColor;
    ChessGame.TeamColor notPlayerColor = ChessGame.TeamColor.BLACK;

    public ResignGameCommand(String authToken, UserGameCommand.CommandType type, int gameID) {
        super(authToken);
        commandType = CommandType.RESIGN;
        this.gameID = gameID;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPlayerColor() {
        return this.playerColor.toString();
    }

    public int getId() {
        return this.gameID;
    }

    public String getVisitorName() {
        return this.visitorName;
    }
    public void setMessage(String visitorName, ChessGame.TeamColor playerColor) {
        if (playerColor.equals(ChessGame.TeamColor.BLACK)) {
            notPlayerColor = ChessGame.TeamColor.WHITE;
        }
        this.visitorName = visitorName;
        this.playerColor = playerColor;
        this.message = visitorName + " has resigned.\n" + notPlayerColor + " WINS THE GAME!!";
    }
}