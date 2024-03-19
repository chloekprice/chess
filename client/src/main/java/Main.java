import ui.ChessClient;
import ui.PostloginUI;
import ui.PreloginUI;
import ui.StateOfSystem;

public class Main {

    public static void main(String[] args) {
        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        ChessClient client = new ChessClient(serverUrl);
        PreloginUI gettingStarted = new PreloginUI(client);

        do {
            while (client.getState() == StateOfSystem.SIGNEDOUT) {
                gettingStarted.run();
                if (client.getState() == StateOfSystem.QUIT) {
                    return;
                }
            }
            PostloginUI doingStuff = new PostloginUI(client);
            while (client.getState() == StateOfSystem.SIGNEDIN) {
                doingStuff.run();
            }
            if (client.getState() == StateOfSystem.QUIT) {
                return;
            }
        } while (client.getState() == StateOfSystem.SIGNEDOUT);

    }

}