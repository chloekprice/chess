package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;

import java.util.ArrayList;
import java.util.Collection;

public class KnightCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;
    public KnightCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = start_position.getRow();
        int col = start_position.getColumn();

        // UP TWO RIGHT ONE
        if (row < 7 && col < 8) {
            ChessPosition new_position = new ChessPosition((row + 2), (col + 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // UP TWO LEFT ONE
        if (row < 7 & col > 1) {
            ChessPosition new_position = new ChessPosition((row + 2), (col - 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // RIGHT TWO UP ONE
        if (row < 8 & col < 7) {
            ChessPosition new_position = new ChessPosition((row + 1), (col + 2));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // RIGHT TWO DOWN ONE
        if (row > 1 & col < 7) {
            ChessPosition new_position = new ChessPosition((row - 1), (col + 2));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // LEFT TWO UP ONE
        if (row < 7 & col > 2) {
            ChessPosition new_position = new ChessPosition((row + 1), (col - 2));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // LEFT TWO DOWN ONE
        if (row > 1 & col > 2) {
            ChessPosition new_position = new ChessPosition((row - 1), (col - 2));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // DOWN TWO RIGHT ONE
        if (row > 2 & col < 8) {
            ChessPosition new_position = new ChessPosition((row - 2), (col + 1));
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
            } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(start_position).getTeamColor()) {
                moves.add(add_move);
            }
        }
        // DOWN TWO LEFT ONE
        if (row > 2 & col > 1) {
            ChessPosition new_position = new ChessPosition((row - 2), (col - 1));
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
