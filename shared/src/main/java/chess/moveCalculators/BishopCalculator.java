package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class BishopCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;
    public BishopCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
    }
    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();

        // UP ONE RIGHT ONE
        int tempCol = col + 1;
        for (int i = (row + 1); i <= 8; i++) {
            if (tempCol > 8) {
                break;
            }
            CreateNewMove newMove = getNewMove(i, tempCol);
            if (board.getPiece(newMove.newPosition()) == null) {
                moves.add(newMove.addMove());
            } else if (board.getPiece(newMove.newPosition()).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
                moves.add(newMove.addMove());
                break;
            } else {
                break;
            }
            tempCol += 1;
        }
        // DOWN ONE RIGHT ONE
        tempCol = col + 1;
        for (int i = (row - 1); i > 0; i--) {
            if (tempCol > 8) {
                break;
            }
            if (createNewPosition(moves, tempCol, i, startPosition, board)) break;
            tempCol += 1;
        }
        // DOWN ONE LEFT ONE
        tempCol = col - 1;
        for (int i = (row - 1); i > 0; i --) {
            if (tempCol < 1) {
                break;
            }
            ChessPosition newPosition = new ChessPosition(i, tempCol);
            ChessMove addMove = new ChessMove(startPosition, newPosition);
            if (board.getPiece(newPosition) == null) {
                moves.add(addMove);
            } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
                moves.add(addMove);
                break;
            } else {
                break;
            }
            tempCol -= 1;
        }
        // UP ONE LEFT ONE
        tempCol = col - 1;
        for (int i = (row + 1); i <= 8; i++) {
            if (tempCol < 1) {
                break;
            }
            ChessPosition newPosition = new ChessPosition(i, tempCol);
            ChessMove addMove = new ChessMove(startPosition, newPosition);
            if (board.getPiece(newPosition) == null) {
                moves.add(addMove);
            } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
                moves.add(addMove);
                break;
            } else {
                break;
            }
            tempCol -= 1;
        }

        return moves;
    }

    static boolean createNewPosition(ArrayList<ChessMove> moves, int temp_col, int i, ChessPosition startPosition, ChessBoard board) {
        ChessPosition newPosition = new ChessPosition(i, temp_col);
        ChessMove addMove = new ChessMove(startPosition, newPosition);
        if (board.getPiece(newPosition) == null) {
            moves.add(addMove);
        } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
            moves.add(addMove);
            return true;
        } else {
            return true;
        }
        return false;
    }

    private CreateNewMove getNewMove(int i, int temp_col) {
        ChessPosition new_position = new ChessPosition(i, temp_col);
        ChessMove add_move = new ChessMove(startPosition, new_position);
        CreateNewMove newMove = new CreateNewMove(new_position, add_move);
        return newMove;
    }

    private record CreateNewMove(ChessPosition newPosition, ChessMove addMove) {
    }
}
