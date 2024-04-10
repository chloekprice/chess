package server.websockets;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.security.core.userdetails.User;
import server.exception.ResponseException;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.userCommands.*;
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
                case UserGameCommand.CommandType.JOIN_OBSERVER -> joinObserver(session, message);
                case UserGameCommand.CommandType.MAKE_MOVE -> makeMove(session, message);
            }
        } catch (Exception e) {
            System.out.print("error");
        }
    }

    private void joinPlayer(Session session, String message) throws IOException {
        JoinPlayerGameCommand command = new Gson().fromJson(message, JoinPlayerGameCommand.class);
        connections.add(command.getName(), session);
        Notification notification = new Notification(command.getMessage());
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
    }
    private void joinObserver(Session session, String message) throws IOException {
        ObserveGameCommand command = new Gson().fromJson(message, ObserveGameCommand.class);
        Notification notification = new Notification(command.getMessage());
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
    }
    private void leaveGame(Session session, String message) throws IOException {
        LeaveGameCommand command = new Gson().fromJson(message, LeaveGameCommand.class);
        Notification notification = new Notification(command.getMessage());
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
    }
    private void makeMove(Session session, String message) throws IOException {
        MakeMoveCommand command = new Gson().fromJson(message, MakeMoveCommand.class);
        Notification notification = new Notification(command.getMessage());
        session.getRemote().sendString(new Gson().toJson(notification, notification.getClass()));
        LoadGame load = new LoadGame(command.getGame());
        session.getRemote().sendString(new Gson().toJson(load, load.getClass()));
    }
    public void broadcastMessage(int gameID, String message, String exceptThisAuthToken) {
        int game = gameID;
    }
}