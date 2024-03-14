import chess.*;
import ui.PreloginUI;
import ui.StateOfSystem;

public class Main {

    static StateOfSystem state = StateOfSystem.SIGNEDOUT;
    public static void main(String[] args) {
        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        PreloginUI gettingStarted = new PreloginUI();
        while(state == StateOfSystem.SIGNEDOUT) {
          gettingStarted.run();
        }
        while (state == StateOfSystem.SIGNEDIN) {
            // TO-DO
            int x = 0;
        }
    }


}