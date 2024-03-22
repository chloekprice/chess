package ui.repls;

import ui.ChessClient;
import ui.StateOfSystem;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static ui.EscapeSequences.*;

public class PostloginUI {
    ChessClient client;
    public PostloginUI(ChessClient client) {
        this.client = client;

        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
        System.out.println("Please type \"help\" to see options.");
    }

    public void run() {
        inputIndicator();
        Scanner scanner = new Scanner(System.in);
        var result = "";
        if (scanner.hasNext()) {
            result = scanner.next();
            switch (result) {
                case "help" -> printPrompt();
                case "logout" -> {
                    try {
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println(client.logout());
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "create" -> {
                    try {
                        var gameName = scanner.next();

                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println(client.createGame(gameName));
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "observe" -> {
                    int gameID = parseInt(scanner.next());
                    try {
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(client.observeGame(gameID));
//                        client.setState(StateOfSystem.GAMEPLAY);
                        GamePlayUI printBoard = new GamePlayUI(client);
                        System.out.println(RESET_BG_COLOR);
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "list" -> {
                    try {
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println("here is the list of current chess games:");
                        System.out.print(SET_TEXT_COLOR_WHITE);
                        System.out.println(client.listGames());
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_RED);
                        System.out.println(e.getMessage());
                    }
                }
                case "join" -> {

                    int gameID = parseInt(scanner.next());
                    String playerColor = null;
                    if (scanner.hasNext()) {
                        playerColor = scanner.next();
                    }

                    try {
                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println(client.joinGame(gameID, playerColor));
//                        client.setState(StateOfSystem.GAMEPLAY);
                        GamePlayUI printBoard = new GamePlayUI(client);
                        System.out.println(RESET_BG_COLOR);
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


    private void printPrompt() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   create <name> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to create a new game");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   list ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to get list of current games");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   join <id> <BLACK|WHITE|empty> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to enter a chess match");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   observe <id> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to watch a game");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   logout ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to end session");


        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   help ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to get a list of possible commands");
    }

    private void inputIndicator() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print("[LOGGED IN AS ");
        System.out.print(client.getVisitorName());
        System.out.print("] >>> ");
    }
}
