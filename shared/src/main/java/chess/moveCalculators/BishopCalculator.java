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
            if (checkAndAddMove(i, tempCol, moves)) break;
            tempCol -= 1;
        }
        // UP ONE LEFT ONE
        tempCol = col - 1;
        for (int i = (row + 1); i <= 8; i++) {
            if (tempCol < 1) {
                break;
            }
            if (checkAndAddMove(i, tempCol, moves)) break;
            tempCol -= 1;
        }

        return moves;
    }

    private boolean checkAndAddMove(int i, int tempCol, ArrayList<ChessMove> moves) {
        createPositionAndMove newMove = getPositionAndMove(i, tempCol);
        if (board.getPiece(newMove.newPosition()) == null) {
            moves.add(newMove.addMove());
        } else if (board.getPiece(newMove.newPosition()).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
            moves.add(newMove.addMove());
            return true;
        } else {
            return true;
        }
        return false;
    }

    private createPositionAndMove getPositionAndMove(int i, int tempCol) {
        ChessPosition newPosition = new ChessPosition(i, tempCol);
        ChessMove addMove = new ChessMove(startPosition, newPosition);
        createPositionAndMove newMove = new createPositionAndMove(newPosition, addMove);
        return newMove;
    }

    private record createPositionAndMove(ChessPosition newPosition, ChessMove addMove) {
    }

    static boolean createNewPosition(ArrayList<ChessMove> moves, int tempCol, int i, ChessPosition startPosition, ChessBoard board) {
        ChessPosition newPosition = new ChessPosition(i, tempCol);
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

    private CreateNewMove getNewMove(int i, int tempCol) {
        ChessPosition newPosition = new ChessPosition(i, tempCol);
        ChessMove addMove = new ChessMove(startPosition, newPosition);
        CreateNewMove newMove = new CreateNewMove(newPosition, addMove);
        return newMove;
    }

    private record CreateNewMove(ChessPosition newPosition, ChessMove addMove) {
    }
}
