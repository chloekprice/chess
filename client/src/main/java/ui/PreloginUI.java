package ui;


import java.util.Scanner;

import static ui.EscapeSequences.*;

public class PreloginUI {

    public PreloginUI() {
        System.out.print(SET_TEXT_COLOR_BLUE);
        System.out.print(SET_TEXT_BOLD);
        System.out.println("♕ Welcome to 240 Chess! ♕");
        System.out.print(SET_TEXT_COLOR_WHITE);
        System.out.println("Please type \"Help\" to begin.");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
//                result = client.eval(line);
//                System.out.print(BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }


    private void printPrompt() {
//        System.out.print("\n" + RESET + ">>> " + GREEN);
    }

}
