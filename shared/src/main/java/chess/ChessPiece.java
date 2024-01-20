package chess;

import java.util.ArrayList;
import java.util.Collection;

import chess.moveCalculators.BishopCalculator;
import chess.moveCalculators.RookCalculator;
import chess.moveCalculators.QueenCalculator;
import chess.moveCalculators.KingCalculator;
import chess.moveCalculators.PawnCalculator;
import chess.moveCalculators.KnightCalculator;



/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessPiece.PieceType piece;
    private final ChessGame.TeamColor color;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.piece = type;
        this.color = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.piece;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        //TO-DO
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        if (piece == PieceType.BISHOP) {
            BishopCalculator bishop = new BishopCalculator(board, myPosition);
            possibleMoves = bishop.getMoves();
        } else if (piece == PieceType.QUEEN) {
            QueenCalculator queen = new QueenCalculator(board, myPosition);
            possibleMoves = queen.getMoves();
        } else if (piece == PieceType.KING) {
            KingCalculator king = new KingCalculator(board, myPosition);
            possibleMoves = king.getMoves();
        } else if (piece == PieceType.ROOK) {
            RookCalculator rook = new RookCalculator(board, myPosition);
            possibleMoves = rook.getMoves();
        } else if (piece == PieceType.PAWN) {
            PawnCalculator pawn = new PawnCalculator(board, myPosition);
            possibleMoves = pawn.getMoves();
        } else if (piece == PieceType.KNIGHT) {
            KnightCalculator knight = new KnightCalculator(board, myPosition);
            possibleMoves = knight.getMoves();
        }

        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        ChessPiece other = (ChessPiece) o;
        return ((this.piece.equals(other.getPieceType())) && ((this.color.equals(other.getTeamColor()))));
    }
}
