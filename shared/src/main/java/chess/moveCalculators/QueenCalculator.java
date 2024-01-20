package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;
import chess.moveCalculators.RookCalculator;
import chess.moveCalculators.BishopCalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;


public class QueenCalculator {
    private final ChessBoard board;
    private final ChessPosition start_position;
    public QueenCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        BishopCalculator diagonal = new BishopCalculator(board, start_position);
        Collection<ChessMove> moves = diagonal.getMoves();
        RookCalculator straight = new RookCalculator(board, start_position);
        moves.addAll(straight.getMoves());
        return moves;
    }
}
