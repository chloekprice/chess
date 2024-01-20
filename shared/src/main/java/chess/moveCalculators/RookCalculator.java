package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class RookCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;
    public RookCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = start_position.getRow();
        int col = start_position.getColumn();

        // UP ONE ONLY
        for (int i = (row + 1); i <= 8; i++) {
            ChessPosition new_position = new ChessPosition(i, col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
//            System.out.printf("%d,%d ", i, col);
        }

        // DOWN ONE ONLY
        for (int i = (row - 1); i > 0; i--) {
            ChessPosition new_position = new ChessPosition(i, col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
//            System.out.printf("%d,%d ", i, col);
        }
        // RIGHT ONE ONLY
        for (int j = (col + 1); j <= 8; j++) {
            ChessPosition new_position = new ChessPosition(row, j);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
//            System.out.printf("%d,%d ", row, j);
        }

        // LEFT ONE ONLY
        for (int j = (col - 1); j > 0; j--) {
            ChessPosition new_position = new ChessPosition(row, j);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
//            System.out.printf("%d,%d ", row, j);
        }
        return moves;
    }
}
