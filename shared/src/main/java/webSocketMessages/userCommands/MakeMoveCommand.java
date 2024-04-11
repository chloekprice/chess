package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;

public class MakeMoveCommand extends  UserGameCommand{
    String visitorName;
    String message;
    ChessPiece piece;
    ChessGame game;
    int gameID;
    public MakeMoveCommand(String authToken, String visitorName, ChessPiece piece, ChessGame game, int id) {
        super(authToken);
        commandType = CommandType.MAKE_MOVE;
        this.visitorName = visitorName;
        this.piece = piece;
        this.game = game;
        this.gameID = id;

        this.message = visitorName + " has moved their " + piece.getPieceType().toString();
    }
    public String getMessage() {
        return this.message;
    }
    public ChessGame getGame() {
        return this.game;
    }
    public int getID() {
        return this.gameID;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
}
