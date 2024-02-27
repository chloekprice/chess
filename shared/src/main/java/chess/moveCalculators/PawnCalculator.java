package chess.moveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class PawnCalculator {
    private final ChessBoard board;
    private final ChessPosition startPosition;
    private final int row;
    private final int col;
    ChessGame.TeamColor team;
    public PawnCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.startPosition = position;
        this.row = startPosition.getRow();
        this.col = startPosition.getColumn();
        team = board.getPiece(startPosition).getTeamColor();
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        HashSet<ChessMove> promotionMoves = new HashSet<>();

        // REG MOVEMENT
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            regWhiteMovement(promotionMoves, moves);
        }
            // BLACK
        if (team == ChessGame.TeamColor.BLACK) {
            regBlackMovement(promotionMoves, moves);
        }

        // STARTING MOVE
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            startingMoveWhite(moves);
        }
            // BLACK
        if (team == ChessGame.TeamColor.BLACK) {
            startingMoveBlack(moves);
        }

        // CAPTURING
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            // UP ONE RIGHT ONE
            if (row < 8 && col < 8) {
                ChessPosition newPosition = new ChessPosition((row + 1), (col + 1));
                if (board.getPiece(newPosition) != null && board.getPiece(newPosition).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (row == 7) {
                        addPromotionPieceMove(promotionMoves,newPosition);
                    } else {
                        moves.add((createMove(newPosition, null)));
                    }
                }
            }
            // UP ONE LEFT ONE
            if (row < 8 && col > 1) {
                ChessPosition newPosition = new ChessPosition((row + 1), (col - 1));
                if (board.getPiece(newPosition) != null && board.getPiece(newPosition).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (row == 7) {
                        addPromotionPieceMove(promotionMoves,newPosition);
                    } else {
                        moves.add((createMove(newPosition, null)));
                    }
                }
            }
        }
            // BLACk
        if (team == ChessGame.TeamColor.BLACK) {
            // DOWN ONE RIGHT ONE
            if (row > 1 && col < 8) {
                ChessPosition newPosition = new ChessPosition((row - 1), (col + 1));
                if (board.getPiece(newPosition) != null && board.getPiece(newPosition).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (row == 2) {
                        addPromotionPieceMove(promotionMoves,newPosition);
                    } else {
                        moves.add((createMove(newPosition, null)));
                    }
                }
            }
            // DOWN ONE LEFT ONE
            if (row > 1 && col > 1) {
                ChessPosition newPosition = new ChessPosition((row - 1), (col - 1));
                if (board.getPiece(newPosition) != null && board.getPiece(newPosition).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (row == 2) {
                        addPromotionPieceMove(promotionMoves,newPosition);
                    } else {
                        moves.add((createMove(newPosition, null)));
                    }
                }
            }
        }

        // PROMOTION STUFF
        if (moves.isEmpty()) {
            return promotionMoves;
        }
        return moves;
    }

    private void startingMoveBlack(ArrayList<ChessMove> moves) {
        if (row == 7) {
            ChessPosition newPosition = new ChessPosition((row - 1), col);
            ChessMove addMove = new ChessMove(startPosition, newPosition);
            if (board.getPiece(newPosition) == null) {
                moves.add(addMove);
                newPosition = new ChessPosition((row - 2), col);
                addMove = new ChessMove(startPosition, newPosition);
                if (board.getPiece(newPosition) == null) {
                    moves.add(addMove);
                }
            }
        }
    }

    private void startingMoveWhite(ArrayList<ChessMove> moves) {
        if (row == 2) {
            ChessPosition newPosition = new ChessPosition((row + 1), col);
            ChessMove addMove = new ChessMove(startPosition, newPosition);
            if (board.getPiece(newPosition) == null) {
                moves.add(addMove);
                newPosition = new ChessPosition((row + 2), col);
                addMove = new ChessMove(startPosition, newPosition);
                if (board.getPiece(newPosition) == null) {
                    moves.add(addMove);
                }
            }
        }
    }

    private void regBlackMovement(HashSet<ChessMove> promotionMoves, ArrayList<ChessMove> moves) {
        if (row > 1) {
            ChessPosition newPosition = new ChessPosition((row - 1), col);
            if (board.getPiece(newPosition) == null) {
                if ( row == 2) {
                    addPromotionPieceMove(promotionMoves, newPosition);
                } else {
                    moves.add((createMove(newPosition, null)));
                }
            }
        }
    }

    private void regWhiteMovement(HashSet<ChessMove> promotionMoves, ArrayList<ChessMove> moves) {
        if (row < 8) {
            ChessPosition newPosition = new ChessPosition((row + 1), col);
            if (board.getPiece(newPosition) == null) {
                if ( row == 7) {
                    addPromotionPieceMove(promotionMoves, newPosition);
                } else {
                    moves.add(createMove(newPosition, null));
                }
            }
        }
    }

    private void addPromotionPieceMove(HashSet<ChessMove> promotionMoves, ChessPosition newPosition) {
        promotionMoves.add(createMove(newPosition, ChessPiece.PieceType.BISHOP));
        promotionMoves.add(createMove(newPosition, ChessPiece.PieceType.QUEEN));
        promotionMoves.add(createMove(newPosition, ChessPiece.PieceType.ROOK));
        promotionMoves.add(createMove(newPosition, ChessPiece.PieceType.KNIGHT));
    }

    ChessMove createMove(ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        ChessMove newMove;
        if (promotionPiece == null) {
            newMove = new ChessMove(this.startPosition, endPosition);
        } else {
            newMove = new ChessMove(this.startPosition, endPosition, promotionPiece);
        }
        return newMove;
    }
}
