package webSocketMessages.userCommands;

import chess.ChessGame;

public class JoinPlayerGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    int gameID;
    ChessGame game;
    public JoinPlayerGameCommand(String authToken, String visitorName, String playerColor, int gameID, ChessGame game) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.visitorName = visitorName;
        this.playerColor = playerColor;
        this.gameID = gameID;
        this.game = game;

        this.message = visitorName + " has joined the game as " + playerColor;
    }
    public String getMessage() {
        return this.message;
    }
    public String getName() {
        return this.visitorName;
    }
    public int getID() {
        return this.gameID;
    }
    public ChessGame getGame() {
        return this.game;
    }
}
