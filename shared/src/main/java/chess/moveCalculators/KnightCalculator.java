package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;

import java.util.ArrayList;
import java.util.Collection;

public class KnightCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;
    public KnightCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();

        // UP TWO RIGHT ONE
        if (row < 7 && col < 8) {
            ChessPosition newPosition = new ChessPosition((row + 2), (col + 1));
            checkAndAddMove(newPosition, moves);
        }
        // UP TWO LEFT ONE
        if (row < 7 & col > 1) {
            ChessPosition newPosition = new ChessPosition((row + 2), (col - 1));
            checkAndAddMove(newPosition, moves);
        }
        // RIGHT TWO UP ONE
        if (row < 8 & col < 7) {
            ChessPosition newPosition = new ChessPosition((row + 1), (col + 2));
            checkAndAddMove(newPosition, moves);
        }
        // RIGHT TWO DOWN ONE
        if (row > 1 & col < 7) {
            ChessPosition newPosition = new ChessPosition((row - 1), (col + 2));
            checkAndAddMove(newPosition, moves);
        }
        // LEFT TWO UP ONE
        if (row < 7 & col > 2) {
            ChessPosition newPosition = new ChessPosition((row + 1), (col - 2));
            checkAndAddMove(newPosition, moves);
        }
        // LEFT TWO DOWN ONE
        if (row > 1 & col > 2) {
            ChessPosition newPosition = new ChessPosition((row - 1), (col - 2));
            checkAndAddMove(newPosition, moves);
        }
        // DOWN TWO RIGHT ONE
        if (row > 2 & col < 8) {
            ChessPosition newPosition = new ChessPosition((row - 2), (col + 1));
            checkAndAddMove(newPosition, moves);
        }
        // DOWN TWO LEFT ONE
        if (row > 2 & col > 1) {
            ChessPosition newPosition = new ChessPosition((row - 2), (col - 1));
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
