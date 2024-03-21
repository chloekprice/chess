package ui.repls;

import ui.ChessClient;
import static ui.EscapeSequences.*;
import ui.StateOfSystem;


public class GamePlayUI {
    ChessClient client;
    public GamePlayUI(ChessClient client) {
        this.client = client;
        printBoard();

    }
    public void run() {

    }
    public void printBoard() {
        System.out.println(whitePOV());
        System.out.print(SET_BG_COLOR_BLACK);
        System.out.println("                            \n");
        System.out.println(blackPOV());
    }
    private String whitePOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("    a  b  c  d  e  f  g  h   \n");
        chessBoard.append(" 1 ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" R ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" N ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" B ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" K ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" Q ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" B ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" N ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" R ");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 1 \n");
        chessBoard.append(" 2 ");
        for (int i = 0; i < 8; i++) {
            if ((i|1) > i) {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                chessBoard.append(" p ");
            } else {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                chessBoard.append(" p ");
            }
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 2 \n");
        for (int i = 0; i < 4; i++) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(" ");
            chessBoard.append((i+3));
            chessBoard.append(" ");

            if ((i|1) > i) {
                for (int j = 0; j < 8; j++) {
                    if ((j|1) > i) {
                        chessBoard.append(SET_BG_COLOR_DARK_GREY);
                        chessBoard.append("   ");
                    } else {
                        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                        chessBoard.append("   ");
                    }
                }
            } else {
                for (int k = 0; k < 8; k++) {
                    if ((k|1) > i) {
                        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                        chessBoard.append("   ");
                    } else {
                        chessBoard.append(SET_BG_COLOR_DARK_GREY);
                        chessBoard.append("   ");
                    }
                }
            }

            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(" ");
            chessBoard.append((i+3));
            chessBoard.append(" \n");
        }
        chessBoard.append("    a  b  c  d  e  f  g  h   ");
        return String.valueOf(chessBoard);
    }
    private String blackPOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("   h  g  f  e  d  c  b  a   \n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("   h  g  f  e  d  c  b  a   \n");
        return String.valueOf(chessBoard);
    }
}
