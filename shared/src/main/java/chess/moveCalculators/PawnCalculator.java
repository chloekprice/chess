package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessGame;

import java.util.ArrayList;
import java.util.Collection;

public class PawnCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;
    public PawnCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = start_position.getRow();
        int col = start_position.getColumn();
        ChessGame.TeamColor team = board.getPiece(start_position).getTeamColor();

        // REG MOVEMENT
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            if (row < 8) {
                ChessPosition new_position = new ChessPosition((row + 1), col);
                ChessMove add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                }
            }
        }
            // BLACK
        if (team == ChessGame.TeamColor.BLACK) {
            if (row > 1) {
                ChessPosition new_position = new ChessPosition((row - 1), col);
                ChessMove add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                }
            }
        }

        // STARTING MOVE
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            if (row == 2) {
                ChessPosition new_position = new ChessPosition((row + 1), col);
                ChessMove add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                    new_position = new ChessPosition((row + 2), col);
                    add_move = new ChessMove(start_position, new_position);
                    if (board.getPiece(new_position) == null) {
                        moves.add(add_move);
                    }
                }
            }
        }
            // BLACK
        if (team == ChessGame.TeamColor.BLACK) {
            if (row == 7) {
                ChessPosition new_position = new ChessPosition((row - 1), col);
                ChessMove add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                    new_position = new ChessPosition((row - 2), col);
                    add_move = new ChessMove(start_position, new_position);
                    if (board.getPiece(new_position) == null) {
                        moves.add(add_move);
                    }
                }
            }
        }

        // CAPTURING
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            // UP ONE RIGHT ONE
            if (row < 8 && col < 8) {
                ChessPosition new_position = new ChessPosition((row + 1), (col + 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    ChessMove add_move = new ChessMove(start_position, new_position);
                    moves.add(add_move);
                }
            }
            // UP ONE LEFT ONE
            if (row < 8 && col > 1) {
                ChessPosition new_position = new ChessPosition((row + 1), (col - 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    ChessMove add_move = new ChessMove(start_position, new_position);
                    moves.add(add_move);
                }
            }
        }
            // BLACk
        if (team == ChessGame.TeamColor.BLACK) {
            // DOWN ONE RIGHT ONE
            if (row > 1 && col < 8) {
                ChessPosition new_position = new ChessPosition((row - 1), (col + 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    ChessMove add_move = new ChessMove(start_position, new_position);
                    moves.add(add_move);
                }
            }
            // DOWN ONE LEFT ONE
            if (row > 1 && col > 1) {
                ChessPosition new_position = new ChessPosition((row - 1), (col - 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    ChessMove add_move = new ChessMove(start_position, new_position);
                    moves.add(add_move);
                }
            }
        }

        // PROMOTION STUFF

        return moves;
    }
}
