package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class BishopCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;
    public BishopCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }
    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = start_position.getRow();
        int col = start_position.getColumn();

        // UP ONE RIGHT ONE
        int temp_col = col + 1;
        for (int i = (row + 1); i <= 8; i++) {
            if (temp_col > 8) {
                break;
            }
            ChessPosition new_position = new ChessPosition(i, temp_col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
            temp_col += 1;
        }
        // DOWN ONE RIGHT ONE
        temp_col = col + 1;
        for (int i = (row - 1); i > 0; i--) {
            if (temp_col > 8) {
                break;
            }
            ChessPosition new_position = new ChessPosition(i, temp_col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
            temp_col += 1;
        }
        // DOWN ONE LEFT ONE
        temp_col = col - 1;
        for (int i = (row - 1); i > 0; i --) {
            if (temp_col < 1) {
                break;
            }
            ChessPosition new_position = new ChessPosition(i, temp_col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
            temp_col -= 1;
        }
        // UP ONE LEFT ONE
        temp_col = col - 1;
        for (int i = (row + 1); i <= 8; i++) {
            if (temp_col < 1) {
                break;
            }
            ChessPosition new_position = new ChessPosition(i, temp_col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
                break;
            } else {
                break;
            }
            temp_col -= 1;
        }
        return moves;
    }
}
