package ui;


import java.util.Scanner;

import static ui.EscapeSequences.*;

public class PreloginUI {
    ChessClient client;
    public PreloginUI(ChessClient client) {
        this.client = client;

        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.print(SET_TEXT_BOLD);
        System.out.println("♕ Welcome to 240 Chess! ♕");
        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
        System.out.println("Please type \"help\" to begin.");
    }

    public void run() {
        inputIndicator();
        Scanner scanner = new Scanner(System.in);
        var result = "";
        if (scanner.hasNext()) {
            result = scanner.next();
            switch (result) {
                case "help" -> printPrompt();
                case "register" -> {
                    try {
                        var username = scanner.next();
                        var password = scanner.next();
                        var email = scanner.next();

                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println(client.register(username, password, email));
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println(e);
                    }
                }
                case "login" -> {
                    try {
                        var username = scanner.next();
                        var password = scanner.next();

                        System.out.println();
                        System.out.print(SET_TEXT_COLOR_BLUE);
                        System.out.println(client.signIn(username, password));
                    } catch (Exception e) {
                        System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                        System.out.println(e);
                    }
                }
                case "quit" -> {
                    System.out.println();
                    System.out.println(SET_TEXT_COLOR_RED);
                    System.out.print("bye bye bye- bye! bye!");
                    client.setState(StateOfSystem.QUIT);
                }
                default -> {
                    System.out.println();
                    System.out.print(SET_TEXT_COLOR_LIGHT_GREY);
                    System.out.println("Sorry, that was not valid. Try typing \"help\".");
                }
            }
        }
    }


    private void printPrompt() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   register <username> <password> <email> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to create an account");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   login <username> <password> ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to play chess");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   quit ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to stop playing chess");

        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.print(SET_TEXT_BOLD);
        System.out.print("   help ");
        System.out.print(SET_TEXT_COLOR_MAGENTA);
        System.out.println("- to get a list of possible commands");
    }

    private void inputIndicator() {
        System.out.println();
        System.out.print(SET_TEXT_COLOR_BLACK);
        System.out.print("[LOGGED OUT] >>> ");
    }

}
