package chess;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    private final ChessPosition start;
    private final ChessPosition end;
    private final ChessPiece.PieceType promotion;
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.start = startPosition;
        this.end = endPosition;
        this.promotion = promotionPiece;
    }
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition) {
        this.start = startPosition;
        this.end = endPosition;
        this.promotion = null;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.start;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.end;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        ChessMove other = (ChessMove) o;
        if (this.promotion != null && other.getPromotionPiece() == null) { return false;}
        if (this.promotion == null && other.getPromotionPiece() != null) { return false;}
        if (this.promotion != null) {
            return ((this.start.equals(other.getStartPosition())) && ((this.end.equals(other.getEndPosition()))) && (this.promotion.equals(other.getPromotionPiece())));
        } else {
            return ((this.start.equals(other.getStartPosition())) && ((this.end.equals(other.getEndPosition()))));
        }
    }

    @Override
    public int hashCode() {
        if( this.promotion != null) {
            return (this.start.hashCode() * this.end.hashCode() * this.promotion.hashCode());
        } else {
            return (this.start.hashCode() * this.end.hashCode());
        }
    }
}
