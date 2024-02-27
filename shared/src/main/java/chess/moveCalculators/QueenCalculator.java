package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;

import java.util.Collection;


public class QueenCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;
    public QueenCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
    }

    public Collection<ChessMove> getMoves() {
        BishopCalculator diagonal = new BishopCalculator(board, startPosition);
        Collection<ChessMove> moves = diagonal.getMoves();
        RookCalculator straight = new RookCalculator(board, startPosition);
        moves.addAll(straight.getMoves());
        return moves;
    }
}
