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
    private final ChessPosition startPosition;
    public RookCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();

        // UP ONE ONLY
        for (int i = (row + 1); i <= 8; i++) {
            if (BishopCalculator.createNewPosition(moves, col, i, startPosition, board)) break;
//            System.out.printf("%d,%d ", i, col);
        }

        // DOWN ONE ONLY
        for (int i = (row - 1); i > 0; i--) {
            if (BishopCalculator.createNewPosition(moves, col, i, startPosition, board)) break;
//            System.out.printf("%d,%d ", i, col);
        }
        // RIGHT ONE ONLY
        for (int j = (col + 1); j <= 8; j++) {
            if (BishopCalculator.createNewPosition(moves, j, row, startPosition, board)) break;
//            System.out.printf("%d,%d ", row, j);
        }

        // LEFT ONE ONLY
        for (int j = (col - 1); j > 0; j--) {
            if (BishopCalculator.createNewPosition(moves, j, row, startPosition, board)) break;
//            System.out.printf("%d,%d ", row, j);
        }
        return moves;
    }
}
