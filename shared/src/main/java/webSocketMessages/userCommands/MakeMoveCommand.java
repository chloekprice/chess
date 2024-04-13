package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;

public class MakeMoveCommand extends  UserGameCommand{
    String visitorName;
    String message;
    ChessPiece.PieceType piece;
    ChessMove move;
    int gameID;
    public MakeMoveCommand(String authToken, CommandType type, int gameID, ChessMove move) {
        super(authToken);
        commandType = type;
        this.visitorName = "";
        this.piece = ChessPiece.PieceType.PAWN;
        this.gameID = gameID;
        this.move = move;
    }
    public String getMessage() {
        return this.message;
    }
    public int getID() {
        return this.gameID;
    }
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
    public void setMessage() {
        this.message = visitorName + " moved their " + piece;
    }
    public ChessMove getMove() {
        return this.move;
    }

}
