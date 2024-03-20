package ui;

import java.util.Scanner;

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
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println(client.logout());
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println("Please provide all of required information.");
                    }
                }
                case "create" -> {
                    try {
                        var username = scanner.next();
                        var password = scanner.next();

                        client.setState(StateOfSystem.SIGNEDIN);
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println("Please provide all of required information.");
                    }
                }
                case "join" -> {
                    client.setState(StateOfSystem.QUIT);
                    System.out.println(SET_TEXT_COLOR_RED);
                    System.out.print("bye bye bye- bye! bye!");
                }
                case "quit" -> {
                    client.setState(StateOfSystem.QUIT);
                    System.out.println(SET_TEXT_COLOR_RED);
                    System.out.print("bye bye bye- bye! bye!");
                }
                default -> {
                    System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                    System.out.println("Sorry, that was not valid. Try typing \"help\".");
                    inputIndicator();
                }
            }
        }
    }


    private void printPrompt() {
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
        System.out.print("   quit ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to end playing chess");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   help ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to get a list of possible commands");
    }

    private void inputIndicator() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLACK);
        System.out.print("[LOGGED IN AS ");
        System.out.print(client.getVisitorName());
        System.out.print("] >>> ");
    }
}
