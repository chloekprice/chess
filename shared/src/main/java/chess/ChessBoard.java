package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board;
    public ChessBoard() {
        this.board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[(position.getRow() - 1)][(position.getColumn() - 1)] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[(position.getRow() - 1)][(position.getColumn() - 1)];
    }

    public ChessPiece[][] getBoard() {
        return this.board;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // WHITE
            // PAWNS
        ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        ChessPosition start = new ChessPosition(2, 1);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 2);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 3);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 4);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 5);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 6);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 7);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(2, 8);
        addPiece(start, pawn);
            // BISHOPS
        ChessPiece bishop = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        start = new ChessPosition(1, 3);
        addPiece(start, bishop);
        bishop = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP);
        start = new ChessPosition(1, 6);
        addPiece(start, bishop);
            // ROOKS
        ChessPiece rook = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        start = new ChessPosition(1, 1);
        addPiece(start, rook);
        rook = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK);
        start = new ChessPosition(1, 8);
        addPiece(start, rook);
            // KNIGHTS
        ChessPiece knight = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        start = new ChessPosition(1, 2);
        addPiece(start, knight);
        knight = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT);
        start = new ChessPosition(1, 7);
        addPiece(start, knight);
            // QUEEN
        ChessPiece queen = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN);
        start = new ChessPosition(1, 4);
        addPiece(start, queen);
            // KING
        ChessPiece king = new ChessPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING);
        start = new ChessPosition(1, 5);
        addPiece(start, king);
        // BLACK
            // PAWNS
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 1);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 2);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 3);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 4);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 5);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 6);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 7);
        addPiece(start, pawn);
        pawn = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN);
        start = new ChessPosition(7, 8);
        addPiece(start, pawn);
            // BISHOPS
        bishop = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        start = new ChessPosition(8, 3);
        addPiece(start, bishop);
        bishop = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP);
        start = new ChessPosition(8, 6);
        addPiece(start, bishop);
            // ROOKS
        rook = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        start = new ChessPosition(8, 1);
        addPiece(start, rook);
        rook = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK);
        start = new ChessPosition(8, 8);
        addPiece(start, rook);
            // KNIGHTS
        knight = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        start = new ChessPosition(8, 2);
        addPiece(start, knight);
        knight = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT);
        start = new ChessPosition(8, 7);
        addPiece(start, knight);
            // QUEEN
        queen = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN);
        start = new ChessPosition(8, 4);
        addPiece(start, queen);
            // KING
        king = new ChessPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING);
        start = new ChessPosition(8, 5);
        addPiece(start, king);
    }

    @Override
    public boolean equals(Object o){
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        ChessBoard other = (ChessBoard) o;
        return (Arrays.deepEquals(this.board, other.getBoard()));
    }
}
