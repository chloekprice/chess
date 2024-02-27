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
    private final ChessPosition start_position;
    private final int row;
    private final int col;
    ChessGame.TeamColor team;
    public PawnCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.start_position = position;
        this.row = start_position.getRow();
        this.col = start_position.getColumn();
        team = board.getPiece(start_position).getTeamColor();
    }

    public Collection<ChessMove> getMoves() {
        ArrayList<ChessMove> moves = new ArrayList<>();
        HashSet<ChessMove> promotion_moves = new HashSet<>();

        // REG MOVEMENT
            // WHITE
        if (team == ChessGame.TeamColor.WHITE) {
            regWhiteMovement(promotion_moves, moves);
        }
            // BLACK
        if (team == ChessGame.TeamColor.BLACK) {
            regBlackMovement(promotion_moves, moves);
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
                ChessPosition new_position = new ChessPosition((row + 1), (col + 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (row == 7) {
                        addPromotionPieceMove(promotion_moves,new_position);
                    } else {
                        moves.add((createMove(new_position, null)));
                    }
                }
            }
            // UP ONE LEFT ONE
            if (row < 8 && col > 1) {
                ChessPosition new_position = new ChessPosition((row + 1), (col - 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    if (row == 7) {
                        addPromotionPieceMove(promotion_moves,new_position);
                    } else {
                        moves.add((createMove(new_position, null)));
                    }
                }
            }
        }
            // BLACk
        if (team == ChessGame.TeamColor.BLACK) {
            // DOWN ONE RIGHT ONE
            if (row > 1 && col < 8) {
                ChessPosition new_position = new ChessPosition((row - 1), (col + 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (row == 2) {
                        addPromotionPieceMove(promotion_moves,new_position);
                    } else {
                        moves.add((createMove(new_position, null)));
                    }
                }
            }
            // DOWN ONE LEFT ONE
            if (row > 1 && col > 1) {
                ChessPosition new_position = new ChessPosition((row - 1), (col - 1));
                if (board.getPiece(new_position) != null && board.getPiece(new_position).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    if (row == 2) {
                        addPromotionPieceMove(promotion_moves,new_position);
                    } else {
                        moves.add((createMove(new_position, null)));
                    }
                }
            }
        }

        // PROMOTION STUFF
        if (moves.isEmpty()) {
            return promotion_moves;
        }
        return moves;
    }

    private void startingMoveBlack(ArrayList<ChessMove> moves) {
        if (row == 7) {
            ChessPosition new_position = new ChessPosition((row - 1), col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
                new_position = new ChessPosition((row - 2), col);
                add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                }
            }
        }
    }

    private void startingMoveWhite(ArrayList<ChessMove> moves) {
        if (row == 2) {
            ChessPosition new_position = new ChessPosition((row + 1), col);
            ChessMove add_move = new ChessMove(start_position, new_position);
            if (board.getPiece(new_position) == null) {
                moves.add(add_move);
                new_position = new ChessPosition((row + 2), col);
                add_move = new ChessMove(start_position, new_position);
                if (board.getPiece(new_position) == null) {
                    moves.add(add_move);
                }
            }
        }
    }

    private void regBlackMovement(HashSet<ChessMove> promotion_moves, ArrayList<ChessMove> moves) {
        if (row > 1) {
            ChessPosition new_position = new ChessPosition((row - 1), col);
            if (board.getPiece(new_position) == null) {
                if ( row == 2) {
                    addPromotionPieceMove(promotion_moves, new_position);
                } else {
                    moves.add((createMove(new_position, null)));
                }
            }
        }
    }

    private void regWhiteMovement(HashSet<ChessMove> promotion_moves, ArrayList<ChessMove> moves) {
        if (row < 8) {
            ChessPosition new_position = new ChessPosition((row + 1), col);
            if (board.getPiece(new_position) == null) {
                if ( row == 7) {
                    addPromotionPieceMove(promotion_moves, new_position);
                } else {
                    moves.add(createMove(new_position, null));
                }
            }
        }
    }

    private void addPromotionPieceMove(HashSet<ChessMove> promotion_moves, ChessPosition new_position) {
        promotion_moves.add(createMove(new_position, ChessPiece.PieceType.BISHOP));
        promotion_moves.add(createMove(new_position, ChessPiece.PieceType.QUEEN));
        promotion_moves.add(createMove(new_position, ChessPiece.PieceType.ROOK));
        promotion_moves.add(createMove(new_position, ChessPiece.PieceType.KNIGHT));
    }

    ChessMove createMove(ChessPosition endPosition, ChessPiece.PieceType promotionPiece) {
        ChessMove newMove;
        if (promotionPiece == null) {
            newMove = new ChessMove(this.start_position, endPosition);
        } else {
            newMove = new ChessMove(this.start_position, endPosition, promotionPiece);
        }
        return newMove;
    }
}
