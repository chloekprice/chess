package webSocketMessages.userCommands;

import chess.ChessGame;

public class ResignGameCommand extends  UserGameCommand {
    String message;
    int id;
    String visitorName;
    ChessGame.TeamColor playerColor;
    ChessGame.TeamColor notPlayerColor = ChessGame.TeamColor.BLACK;

    public ResignGameCommand(String authToken, String visitorName, int gameID, ChessGame.TeamColor playerColor) {
        super(authToken);
        commandType = CommandType.RESIGN;
        id = gameID;
        this.visitorName = visitorName;
        this.playerColor = playerColor;
        if (playerColor.equals(ChessGame.TeamColor.BLACK)) {
            notPlayerColor = ChessGame.TeamColor.WHITE;
        }

        this.message = this.visitorName + " has resigned.\n" + notPlayerColor + " WINS THE GAME!!";
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