package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessPiece;
import webSocketMessages.serverMessages.ServerMessage;

public class JoinPlayerGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    ChessGame.TeamColor playerColor;
    int gameID;
    ChessGame game;
    public JoinPlayerGameCommand(String authToken, CommandType type, int gameID, ChessGame.TeamColor color) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.playerColor = color;
        this.gameID = gameID;
        this.visitorName = null;
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
    public void setMessage(String visitorName) {
        this.message = visitorName + " has joined the game as " + playerColor;
        this.visitorName = visitorName;
    }
    public void setGame(ChessGame game) {
        this.game = game;
    }
    public String getVisitorName() {
        return  this.visitorName;
    }
    public ChessGame getGame() {
        return this.game;
    }
    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }
}
