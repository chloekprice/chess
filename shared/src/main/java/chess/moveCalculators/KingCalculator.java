package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KingCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;

    public KingCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();

        // UP ONE
        if (row < 8) {
            ChessPosition newPosition = new ChessPosition((row + 1), col);
            checkAndAddMove(newPosition, moves);
        }
        // UP ONE RIGHT ONE
        if (row < 8 && col < 8) {
            ChessPosition newPosition = new ChessPosition((row + 1), (col + 1));
            checkAndAddMove(newPosition, moves);
        }
        // UP ONE LEFT ONE
        if (row < 8 && col > 1) {
            ChessPosition newPosition = new ChessPosition((row + 1), (col - 1));
            checkAndAddMove(newPosition, moves);
        }
        // DOWN ONE
        if (row > 1) {
            ChessPosition newPosition = new ChessPosition((row - 1), col);
            checkAndAddMove(newPosition, moves);
        }
        // DOWN ONE RIGHT ONE
        if (row > 1 && col < 8) {
            ChessPosition newPosition = new ChessPosition((row - 1), (col + 1));
            checkAndAddMove(newPosition, moves);
        }
        //DOWN ONE LEFT ONE
        if (row > 1 && col > 1) {
            ChessPosition newPosition = new ChessPosition((row - 1), (col - 1));
            checkAndAddMove(newPosition, moves);
        }
        // RIGHT ONE
        if (col < 8) {
            ChessPosition newPosition = new ChessPosition(row, (col + 1));
            checkAndAddMove(newPosition, moves);
        }
        // LEFT ONE
        if (col > 1) {
            ChessPosition newPosition = new ChessPosition(row, (col - 1));
            checkAndAddMove(newPosition, moves);
        }

        return moves;
    }

    private void checkAndAddMove(ChessPosition newPosition, ArrayList<ChessMove> moves) {
        ChessMove addMove = new ChessMove(startPosition, newPosition);
        if (board.getPiece(newPosition) == null) {
            moves.add(addMove);
        } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
            moves.add(addMove);
        }
    }
}
