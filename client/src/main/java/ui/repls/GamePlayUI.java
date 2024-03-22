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
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(whitePOV());
        System.out.println(RESET_BG_COLOR);
        System.out.println("                              ");
        System.out.println(RESET_BG_COLOR);
        System.out.println(blackPOV());
        System.out.print(RESET_BG_COLOR);
    }
    private String whitePOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 1 ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" r ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" n ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" b ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" k ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" q ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" b ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" n ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" r ");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 1 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 2 ");
        for (int i = 0; i < 8; i++) {
            if ((i|1) > i) {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                chessBoard.append(" P ");
            } else {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                chessBoard.append(" P ");
            }
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 2 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        for (int i = 0; i < 4; i++) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(" ");
            chessBoard.append((i+3));
            chessBoard.append(" ");

            if ((i|1) > i) {
                for (int j = 0; j < 8; j++) {
                    if ((j|1) > j) {
                        chessBoard.append(SET_BG_COLOR_DARK_GREY);
                        chessBoard.append("   ");
                    } else {
                        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                        chessBoard.append("   ");
                    }
                }
            } else {
                for (int k = 0; k < 8; k++) {
                    if ((k|1) > k) {
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
            chessBoard.append(" ");
            chessBoard.append(RESET_BG_COLOR);
            chessBoard.append("\n");

        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 7 ");
        chessBoard.append(SET_TEXT_COLOR_BLACK);
        for (int i = 0; i < 8; i++) {
            if ((i|1) > i) {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                chessBoard.append(" p ");
            } else {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                chessBoard.append(" p ");
            }
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 7 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 8 ");
        chessBoard.append(SET_TEXT_COLOR_BLACK);
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" R ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" N ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" B ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" K ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" Q ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" B ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" N ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" R ");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 8 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("    a  b  c  d  e  f  g  h    ");
        return String.valueOf(chessBoard);
    }
    private String blackPOV() {
        StringBuilder chessBoard = new StringBuilder();
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 8 ");
        chessBoard.append(SET_TEXT_COLOR_BLACK);
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
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 8 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 7 ");
        chessBoard.append(SET_TEXT_COLOR_BLACK);
        for (int i = 0; i < 8; i++) {
            if ((i|1) > i) {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                chessBoard.append(" P ");
            } else {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                chessBoard.append(" P ");
            }
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 7 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        for (int i = 0; i < 4; i++) {
            chessBoard.append(SET_BG_COLOR_MAGENTA);
            chessBoard.append(" ");
            chessBoard.append((i+3));
            chessBoard.append(" ");

            if ((i|1) > i) {
                for (int j = 0; j < 8; j++) {
                    if ((j|1) > j) {
                        chessBoard.append(SET_BG_COLOR_DARK_GREY);
                        chessBoard.append("   ");
                    } else {
                        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                        chessBoard.append("   ");
                    }
                }
            } else {
                for (int k = 0; k < 8; k++) {
                    if ((k|1) > k) {
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
            chessBoard.append((6-i));
            chessBoard.append(" ");
            chessBoard.append(RESET_BG_COLOR);
            chessBoard.append("\n");

        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 2 ");
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        for (int i = 0; i < 8; i++) {
            if ((i|1) > i) {
                chessBoard.append(SET_BG_COLOR_DARK_GREY);
                chessBoard.append(" p ");
            } else {
                chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
                chessBoard.append(" p ");
            }
        }
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 2 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(" 1 ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" r ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" n ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" b ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" k ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" q ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" b ");
        chessBoard.append(SET_BG_COLOR_LIGHT_GREY);
        chessBoard.append(" n ");
        chessBoard.append(SET_BG_COLOR_DARK_GREY);
        chessBoard.append(" r ");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append(SET_TEXT_COLOR_WHITE);
        chessBoard.append(" 1 ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        chessBoard.append(SET_BG_COLOR_MAGENTA);
        chessBoard.append("    h  g  f  e  d  c  b  a    ");
        chessBoard.append(RESET_BG_COLOR);
        chessBoard.append("\n");
        return String.valueOf(chessBoard);
    }
}
