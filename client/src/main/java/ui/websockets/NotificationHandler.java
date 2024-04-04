package ui.websockets;

import webSocketMessages.serverMessages.ServerMessage;
import ui.EscapeSequences;
import ui.repls.GamePlayUI;

public class NotificationHandler {
    void notify(ServerMessage notification) {
        System.out.println(EscapeSequences.SET_TEXT_COLOR_RED + ServerMessage.getServerMessage());
        GamePlayUI.inputIndicator();
    }
}
