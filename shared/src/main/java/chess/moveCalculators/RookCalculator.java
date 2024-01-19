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
    private final ChessPosition start_position;
    public RookCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
        int row = start_position.getRow();
        int col = start_position.getColumn();

        // UP ONE ONLY

        // DOWN ONE ONLY

        // RIGHT ONE ONLY

        // LEFT ONE ONLY

        return moves;
    }
}
