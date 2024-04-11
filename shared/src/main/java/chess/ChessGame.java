package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard game;
    private TeamColor currentTeam;
    private int id;
    public ChessGame() {
        currentTeam = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.currentTeam;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.currentTeam = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> moves = this.game.getPiece(startPosition).pieceMoves(this.game,startPosition);
        Collection<ChessMove> validMoves = new HashSet<>();
        ChessPiece captured = null;

        for (ChessMove move : moves) {
            try {
                captured = this.verifyMove(move);
                if(!this.isInCheck(this.getBoard().getPiece(move.getEndPosition()).getTeamColor()) && !this.isInCheckmate(this.getBoard().getPiece(move.getEndPosition()).getTeamColor())) {
                    validMoves.add(move);
                }
            } catch (Exception e){
                System.out.print("uh-oh, you can't do that\n");
            } finally {
                this.undoMove(move, captured);
            }
        }

        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */

    public ChessPiece verifyMove(ChessMove move) throws InvalidMoveException {
        ChessPiece captured = null;
        ChessPiece movePiece = this.game.getPiece(move.getStartPosition());

        if (movePiece != null) {
            // MOVE NOT PART OF VALID MOVES
            Collection<ChessMove> moves = movePiece.pieceMoves(this.game, move.getStartPosition());
            if (!moves.contains(move)) {
                throw new InvalidMoveException();
            }
            TeamColor color = movePiece.getTeamColor();
            // SAVE CAPTURED PIECE
            if (this.getBoard().getPiece(move.getEndPosition()) != null) {
                captured = this.getBoard().getPiece(move.getEndPosition());
            }
            // MAKE THE MOVE
            if (move.getPromotionPiece() != null) {
                ChessPiece addPromotion = new ChessPiece(color, move.getPromotionPiece());
                this.game.addPiece(move.getEndPosition(), addPromotion);
                this.game.removePiece(move.getStartPosition());
            } else {
                this.game.addPiece(move.getEndPosition(), movePiece);
                this.game.removePiece(move.getStartPosition());
            }
        }
        return captured;
    }

    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece captured = this.verifyMove(move);
        if (this.isInCheck(this.getTeamTurn())) {
            this.undoMove(move, captured);
            throw new InvalidMoveException();
        }
        // MAKE SURE IT IS THEIR TURN
        if (this.getBoard().getPiece(move.getEndPosition()) != null) {
            if (this.getBoard().getPiece(move.getEndPosition()).getTeamColor() != this.getTeamTurn()) {
                this.undoMove(move, captured);
                throw new InvalidMoveException();
            }
        }
        // SET CURRENT TEAM
        if (this.getTeamTurn() == TeamColor.WHITE) {
            setTeamTurn(TeamColor.BLACK);
        } else {
            setTeamTurn(TeamColor.WHITE);
        }
    }

    public void undoMove(ChessMove move, ChessPiece captured) {
        this.getBoard().addPiece(move.getStartPosition(), this.getBoard().getPiece(move.getEndPosition()));
        this.getBoard().addPiece(move.getEndPosition(), captured);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public ChessPosition getKingPosition(TeamColor teamColor) {
        ChessPosition kingPosition = null;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition testPosition = new ChessPosition(i, j);
                if (this.game.getPiece(testPosition) != null) {
                    if (this.game.getPiece(testPosition).getPieceType() == ChessPiece.PieceType.KING && this.game.getPiece(testPosition).getTeamColor() == teamColor) {
                        kingPosition = new ChessPosition(i, j);
                    }
                }
            }
        }
        return kingPosition;
    }
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = this.getKingPosition(teamColor);
        if (kingPosition != null) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    ChessPosition testPosition = new ChessPosition(i, j);
                    if (this.game.getPiece(testPosition) != null) {
                        if (this.game.getPiece(testPosition).getTeamColor() != teamColor) {
                            Collection<ChessMove> testCheck = this.game.getPiece(testPosition).pieceMoves(this.game, testPosition);
                            for (ChessMove testMove : testCheck) {
                                if (testMove.getEndPosition().equals(kingPosition)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPosition kingPosition = this.getKingPosition(teamColor);
        ChessPiece captured = null;

        if (kingPosition != null) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    ChessPosition testPosition = new ChessPosition(i, j);
                    if (this.game.getPiece(testPosition) != null) {
                        if (this.game.getPiece(testPosition).getTeamColor() == teamColor) {
                            Collection<ChessMove> testCheckMoves = this.game.getPiece(testPosition).pieceMoves(this.game, testPosition);
                            for (ChessMove testMove : testCheckMoves) {
                                try {
                                    captured = this.verifyMove(testMove);
                                    if (!this.isInCheck(teamColor)) {
                                        return false;
                                    }
                                } catch (Exception e) {
                                    System.out.print("you can't do that\n");
                                } finally {
                                    this.undoMove(testMove,captured);
                                }

                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        Collection<ChessMove> officialMoves = new HashSet<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition position = new ChessPosition(i, j);
                if (this.getBoard().getPiece(position) != null) {
                    if (this.getBoard().getPiece(position).getTeamColor() == teamColor) {
                        Collection<ChessMove> validMoves = this.getBoard().getPiece(position).pieceMoves(this.game, position);
                        for (ChessMove move : validMoves) {
                            ChessPiece captured = null;
                            if (this.getBoard().getPiece(move.getEndPosition()) != null) {
                                captured = this.getBoard().getPiece(move.getEndPosition());
                            }
                            this.game.addPiece(move.getEndPosition(), this.getBoard().getPiece(move.getStartPosition()));
                            this.game.removePiece(move.getStartPosition());

                            if (!this.isInCheckmate(teamColor) && !this.isInCheck(teamColor)) {
                                officialMoves.add(move);
                            }

                            this.getBoard().addPiece(move.getStartPosition(), this.getBoard().getPiece(move.getEndPosition()));
                            this.getBoard().addPiece(move.getEndPosition(), captured);
                        }
                    }
                }
            }
        }
        return (officialMoves.isEmpty());
    }


    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.game = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.game;
    }
    public void setID(int gameID) {
        this.id = gameID;
    }
    public int getID() {
        return this.id;
    }
}
