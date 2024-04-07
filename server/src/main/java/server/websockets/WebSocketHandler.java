package server.websockets;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.security.core.userdetails.User;
import server.exception.ResponseException;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.userCommands.JoinPlayerGameCommand;
import webSocketMessages.userCommands.LeaveGameCommand;
import webSocketMessages.userCommands.UserGameCommand;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.Timer;


@WebSocket
public class WebSocketHandler {

    private final ConnectionManager connections = new ConnectionManager();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        UserGameCommand command = new Gson().fromJson(message, UserGameCommand.class);
        try {
            switch (command.getCommandType()) {
                case UserGameCommand.CommandType.JOIN_PLAYER -> joinPlayer(session, message);
                case UserGameCommand.CommandType.LEAVE -> leaveGame(session, message);
//                case UserGameCommand.CommandType.RESIGN -> resignGame("lol", "lol");
//                case UserGameCommand.CommandType.JOIN_OBSERVER -> joinObserver("lol", "lol");
//                case UserGameCommand.CommandType.MAKE_MOVE -> makeMove("lol", "lol");
            }
        } catch (Exception e) {
            System.out.print("error");
        }
    }

    private void joinPlayer(Session session, String message) throws IOException {
        JoinPlayerGameCommand command = new Gson().fromJson(message, JoinPlayerGameCommand.class);
        Notification notification = new Notification(command.getMessage());
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
    }

    private void leaveGame(Session session, String message) throws IOException {
        LeaveGameCommand command = new Gson().fromJson(message, LeaveGameCommand.class);
        Notification notification = new Notification(message);
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
    }
    public void broadcastMessage(int gameID, String message, String exceptThisAuthToken) {
        int game = gameID;
    }
}