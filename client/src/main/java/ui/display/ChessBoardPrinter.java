package ui.display;

import chess.*;

import java.util.Collection;
import java.util.HashSet;

import static ui.display.EscapeSequences.*;
import static ui.display.EscapeSequences.SET_BG_COLOR_LIGHT_GREY;

public class ChessBoardPrinter {
    ChessBoard board;
    ChessPosition startPosition;
    Collection<ChessMove> validMoves;
    public ChessBoardPrinter() {
    }
    public void print(ChessBoard board, ChessGame.TeamColor color) {
        this.board = board;
        System.out.println();
        if (color == ChessGame.TeamColor.BLACK) {
            System.out.println(blackPOV());

        } else {
            System.out.println(whitePOV());
        }
    }
    public void highlight(ChessGame game, int column, int row, ChessGame.TeamColor color) {
        this.board = game.getBoard();
        this.startPosition = new ChessPosition(row, column);
        this.validMoves = game.validMoves(startPosition);
        System.out.println();
        if (color == ChessGame.TeamColor.BLACK) {
            System.out.println(blackHighlight());

        } else {
            System.out.println(whiteHighlight());
        }
    }

    private String blackPOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        for (int i = 1; i <= 8; i++) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            for (int j = 1; j <= 8; j++) {
                checkSquareColor(chessBoard, i, j);
            }
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            chessBoard.append(RESET);
            chessBoard.append("\n");
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);

        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        return String.valueOf(chessBoard);
    }

    private void checkSquareColor(StringBuilder chessBoard, int i, int j) {
        if ((j|1) > j) {
            if ((i | 1) > i) {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                setBoardSquare(chessBoard, i, j);
            } else {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                setBoardSquare(chessBoard, i, j);
            }
        } else {
            if ((i | 1) > i) {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                setBoardSquare(chessBoard, i, j);
            } else {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                setBoardSquare(chessBoard, i, j);
            }
        }
    }

    private String whitePOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        for (int i = 8; i >= 1; i--) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            for (int j = 8; j >= 1; j--) {
                checkSquareColor(chessBoard, i, j);
            }
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            chessBoard.append(RESET);
            chessBoard.append("\n");
        }

        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");
        return String.valueOf(chessBoard);
    }
    private String getSquareValues(int r, int c) {
        try {
            ChessPosition position = new ChessPosition(r, c);
            ChessPiece piece = board.getPiece(position);
            return getPiece(piece);
        } catch (Exception e) {
            return " ";
        }
    }

    private String getPiece(ChessPiece piece) {
        switch (piece.getPieceType()) {
            case ChessPiece.PieceType.ROOK -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "r");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "R");
                }
            }
            case ChessPiece.PieceType.QUEEN -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "q");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "Q");
                }
            }
            case ChessPiece.PieceType.KNIGHT -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "n");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "N");
                }
            }
            case ChessPiece.PieceType.KING -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "k");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "K");
                }
            }
            case ChessPiece.PieceType.PAWN -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "p");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "P");
                }
            }
            case ChessPiece.PieceType.BISHOP -> {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    return (SET_TEXT_COLOR_WHITE + "b");
                }
                else {
                    return (SET_TEXT_COLOR_BLACK + "B");
                }
            }
        }
        return " ";
    }
    private String blackHighlight() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        for (int i = 1; i <= 8; i++) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            for (int j = 1; j <= 8; j++) {
                checkValidMovePosition(chessBoard, i, j);
            }
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            chessBoard.append(RESET);
            chessBoard.append("\n");
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);

        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        return String.valueOf(chessBoard);
    }

    private void setBoardSquare(StringBuilder chessBoard, int i, int j) {
        chessBoard.append(" ");
        chessBoard.append(getSquareValues(i, j));
        chessBoard.append(" ");
    }

    private String whiteHighlight() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");

        for (int i = 8; i >= 1; i--) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            for (int j = 8; j >= 1; j--) {
                checkValidMovePosition(chessBoard, i, j);
            }
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(SET_TEXT_COLOR_WHITE);
            chessBoard.append(" ");
            chessBoard.append(i);
            chessBoard.append(" ");
            chessBoard.append(RESET);
            chessBoard.append("\n");
        }

        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        chessBoard.append(RESET);
        chessBoard.append("\n");
        return String.valueOf(chessBoard);
    }

    private void checkValidMovePosition(StringBuilder chessBoard, int i, int j) {
        ChessPosition checkPosition = new ChessPosition(i, j);
        ChessMove chessMove = new ChessMove(startPosition, checkPosition);
        if (validMoves.contains(chessMove)) {
            chessBoard.append(SET_BG_COLOR_GREEN);
            setBoardSquare(chessBoard, i, j);
        } else {
            checkSquareColor(chessBoard, i, j);
        }
    }
}
