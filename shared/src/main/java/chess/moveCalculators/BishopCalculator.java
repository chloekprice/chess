package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class BishopCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;
    public BishopCalculator(ChessBoard board, ChessPosition position) {
        this.board  = board;
        this.startPosition = position;
    }
    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();

        for (int i = (row + 1); i <= 8; i++) {
            for (int j = (col + 1); j <= 8; j++) {
                ChessPosition endPosition = new ChessPosition(i, j);
                ChessMove addMove = new ChessMove(startPosition, endPosition);
                moves.add(addMove);
            }
            for (int j = (col - 1); j > 0; j--) {
                ChessPosition endPosition = new ChessPosition(i, j);
                ChessMove addMove = new ChessMove(startPosition, endPosition);
                moves.add(addMove);
            }
        }
        for (int i = (row - 1); i > 0; i--) {
            for (int j = (col + 1); j <= 8; j++) {
                ChessPosition endPosition = new ChessPosition(i, j);
                ChessMove addMove = new ChessMove(startPosition, endPosition);
                moves.add(addMove);
            }
            for (int j = (col - 1); j > 0; j--) {
                ChessPosition endPosition = new ChessPosition(i, j);
                ChessMove addMove = new ChessMove(startPosition, endPosition);
                moves.add(addMove);
            }
        }

        return moves;
    }
}
