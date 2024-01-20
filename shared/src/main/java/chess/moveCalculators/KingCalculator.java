package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KingCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;

    public KingCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = start_position.getRow();
        int col = start_position.getColumn();

        // UP ONE
        if (row < 8) {
            ChessPosition new_position = new ChessPosition((row + 1), col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // UP ONE RIGHT ONE
        if (row < 8 && col < 8) {
            ChessPosition new_position = new ChessPosition((row + 1), (col + 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // UP ONE LEFT ONE
        if (row < 8 && col > 1) {
            ChessPosition new_position = new ChessPosition((row + 1), (col - 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // DOWN ONE
        if (row > 1) {
            ChessPosition new_position = new ChessPosition((row - 1), col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // DOWN ONE RIGHT ONE
        if (row > 1 && col < 8) {
            ChessPosition new_position = new ChessPosition((row - 1), (col + 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        //DOWN ONE LEFT ONE
        if (row > 1 && col > 1) {
            ChessPosition new_position = new ChessPosition((row - 1), (col - 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // RIGHT ONE
        if (col < 8) {
            ChessPosition new_position = new ChessPosition(row, (col + 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // LEFT ONE
        if (col > 1) {
            ChessPosition new_position = new ChessPosition(row, (col - 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }

        return moves;
    }
}
