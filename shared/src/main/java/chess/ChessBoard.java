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

    public void removePiece(ChessPosition position) {
        board[position.getRow() - 1][position.getColumn() - 1] = null;
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
        // WHITE PAWNS
        for ( int i = 1; i < 9; i++) {
            ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(2, i);
            addPiece(position, pawn);
        }
        // BLACK PAWNS
        for ( int i = 1; i < 9; i++) {
            ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(7, i);
            addPiece(position, pawn);
        }
        // WHITE BISHOP
        ChessPiece bishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition position = new ChessPosition(1, 3);
        addPiece(position, bishop);
        bishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        position = new ChessPosition(1, 6);
        addPiece(position, bishop);
        // BLACK BISHOP
        bishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        position = new ChessPosition(8, 3);
        addPiece(position, bishop);
        bishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        position = new ChessPosition(8, 6);
        addPiece(position, bishop);
        // WHITE ROOK
        ChessPiece rook = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        position = new ChessPosition(1, 1);
        addPiece(position, rook);
        rook = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        position = new ChessPosition(1, 8);
        addPiece(position, rook);
        // BLACK ROOK
        rook = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        position = new ChessPosition(8, 1);
        addPiece(position, rook);
        rook = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        position = new ChessPosition(8, 8);
        addPiece(position, rook);
        // WHITE QUEEN
        ChessPiece queen = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        position = new ChessPosition(1, 4);
        addPiece(position, queen);
        // BLACK QUEEN
        queen = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        position = new ChessPosition(8, 4);
        addPiece(position, queen);
        // WHITE KING
        ChessPiece king = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        position = new ChessPosition(1, 5);
        addPiece(position, king);
        // BLACK KING
        king = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        position = new ChessPosition(8, 5);
        addPiece(position, king);
        // WHITE KNIGHT
        ChessPiece knight = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        position = new ChessPosition(1, 2);
        addPiece(position, knight);
        knight = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        position = new ChessPosition(1, 7);
        addPiece(position, knight);
        // BLACK KNIGHT
        knight = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        position = new ChessPosition(8, 2);
        addPiece(position, knight);
        knight = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        position = new ChessPosition(8, 7);
        addPiece(position, knight);
        // SET NULL
        for (int i = 3; i <= 6; i ++) {
            for (int j = 1; j <= 8; j++) {
                position = new ChessPosition(i, j);
                addPiece(position, null);
            }
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        ChessBoard other = (ChessBoard) o;
        return (Arrays.deepEquals(this.board, other.getBoard()));
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.board);
    }
}
