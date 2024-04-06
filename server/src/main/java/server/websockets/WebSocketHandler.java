package server.websockets;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.security.core.userdetails.User;
import server.exception.ResponseException;
import webSocketMessages.userCommands.JoinPlayerGameCommand;
import webSocketMessages.userCommands.UserGameCommand;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.Timer;


@WebSocket
public class WebSocketHandler {

    private final ConnectionManager connections = new ConnectionManager();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
//        session.getRemote().sendString(message);
//        JoinPlayerGameCommand command = new Gson().fromJson(message, JoinPlayerGameCommand.class);
        session.getRemote().sendString(message);
//        UserGameCommand command = new Gson().fromJson(message, UserGameCommand.class);
//        try {
//            switch (command.getCommandType()) {
//                case UserGameCommand.CommandType.JOIN_PLAYER -> joinPlayer(command.getAuthString(), session);
//                case UserGameCommand.CommandType.LEAVE -> leaveGame(command.getAuthString());
//                case UserGameCommand.CommandType.RESIGN -> resignGame("lol", "lol");
//                case UserGameCommand.CommandType.JOIN_OBSERVER -> joinObserver("lol", "lol");
//                case UserGameCommand.CommandType.MAKE_MOVE -> makeMove("lol", "lol");
//            }
//        } catch (Exception e) {
//            System.out.print("error");
//        }
    }

    private void joinPlayer(String authString, Session session) throws IOException {
//        connections.add(authString, session);
//        var message = String.format("%s entered the game", authString);
//        var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
//        connections.broadcast(authString, notification);
        session.getRemote().sendString("test message");
    }

    private void leaveGame(String visitorName) throws IOException {
        connections.remove(visitorName);
        var message = String.format("%s left the shop", visitorName);
        var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
        connections.broadcast(visitorName, notification);
    }

    public void resignGame(String petName, String sound) throws ResponseException {
        try {
            var message = String.format("%s says %s", petName, sound);
            var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
            connections.broadcast("", notification);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void joinObserver(String petName, String sound) throws ResponseException {
        try {
            var message = String.format("%s says %s", petName, sound);
            var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
            connections.broadcast("", notification);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void makeMove(String petName, String sound) throws ResponseException {
        try {
            var message = String.format("%s says %s", petName, sound);
            var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
            connections.broadcast("", notification);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void sendMessage(int gameID, String message, String authToken) {
        String exceptThisAuthToken = authToken;
        broadcastMessage(gameID, message, exceptThisAuthToken);
    }
    public void broadcastMessage(int gameID, String message, String exceptThisAuthToken) {
        int game = gameID;
    }
}