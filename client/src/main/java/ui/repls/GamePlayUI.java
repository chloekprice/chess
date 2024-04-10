package ui.repls;

import chess.ChessBoard;
import chess.ChessGame;
import ui.ChessClient;

import static java.lang.Integer.parseInt;
import static ui.display.EscapeSequences.*;
import ui.StateOfSystem;
import ui.display.ChessBoardPrinter;

import java.util.Scanner;


public class GamePlayUI {
    ChessClient client;
    ChessBoardPrinter printer;
    ChessGame game;
    public GamePlayUI(ChessClient client) {
        printer = new ChessBoardPrinter();
        this.client = client;
        try {
            client.redraw();
        } catch (Exception e) {
            System.out.print(SET_TEXT_COLOR_RED);
            System.out.println("Error: cannot print chess board");
        }
    }

    public void run() {
        inputIndicator();
        Scanner scanner = new Scanner(System.in);
        var result = "";
        if (scanner.hasNext()) {
            result = scanner.next();
            switch (result) {
                case "help" -> printPrompt();
                case "redraw" -> {
                    try {
                        client.redraw();
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "leave" -> {
                    try {
                        client.setState(StateOfSystem.SIGNEDIN);
                        client.leaveGame();
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println("leaving the game...");
                        client.setState(StateOfSystem.SIGNEDIN);
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "move" -> {
                    var column = scanner.next();
                    int origRow = parseInt(scanner.next());
                    scanner.next();
                    var nextColumn = scanner.next();
                    int newRow = parseInt(scanner.next());

                    int origColumn = 0;
                    int newColumn = 0;
                    // determine col #
                    origColumn = getNum(origColumn, column);
                    newColumn = getNum(newColumn, nextColumn);

                    try {
                        System.out.println();
                        System.out.println(RESET);
                        client.makeMove(origColumn, origRow, newColumn, newRow, client.getData().getGameID());
                        game = client.getData().getGame();
                        printer.print(game.getBoard());
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "resign" -> {
                    try {
                        System.out.println();
                        client.setState(StateOfSystem.SIGNEDIN);
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println("leaving the game...");
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "legal-moves" -> {
                    var column = scanner.next();
                    int row = parseInt(scanner.next());

                    try {
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);

                        System.out.println(RESET);
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                default -> {
                    System.out.println();
                    System.out.print(SET_TEXT_COLOR_RED);
                    System.out.println("Sorry, that was not valid. Try typing \"help\".");
                }
            }
        }

    }

    private static int getNum(int orig, String next) {
        orig = switch (next) {
            case "A" -> 8;
            case "B" -> 7;
            case "C" -> 6;
            case "D" -> 5;
            case "E" -> 4;
            case "F" -> 3;
            case "G" -> 2;
            case "H" -> 1;
            default -> orig;
        };
        return orig;
    }


    public static void inputIndicator() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLACK);
        System.out.print("[GAMEPLAY MODE] >>> ");
    }
    public static void printPrompt() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   redraw ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to have the chess board redrawn");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   leave ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to leave the game");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   move <ABC> <123> to <ABC> <123> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to make a move");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   resign ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to forfeit the game");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   legal-moves <ABC> <123> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to highlight the legal moves");


        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   help ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to get a list of possible commands");
    }
}
