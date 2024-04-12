package server.websockets;

import com.google.gson.Gson;
import dataAccess.game.MySQLGameDAO;
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
                case UserGameCommand.CommandType.RESIGN -> resignGame(session, message);
                case UserGameCommand.CommandType.JOIN_OBSERVER -> joinObserver(session, message);
                case UserGameCommand.CommandType.MAKE_MOVE -> makeMove(session, message);
            }
        } catch (Exception e) {
            System.out.print("error");
        }
    }

    private void joinPlayer(Session session, String message) throws IOException {
        JoinPlayerGameCommand command = new Gson().fromJson(message, JoinPlayerGameCommand.class);
        connections.addPlayerConnection(command.getName(), command.getID(), session);
        Notification notification = new Notification(command.getMessage());
        connections.broadcast(command.getName(), command.getID(), notification);
        command.getGame().setID(command.getID());
        LoadGame load = new LoadGame(command.getGame());
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        gameDAO.refresh(load.getID(), load.getServerGame());
        session.getRemote().sendString(new Gson().toJson(load));
    }
    private void joinObserver(Session session, String message) throws IOException {
        ObserveGameCommand command = new Gson().fromJson(message, ObserveGameCommand.class);
        connections.addPlayerConnection(command.getVisitorName(), command.getID(), session);
        Notification notification = new Notification(command.getMessage());
        connections.broadcast(command.getVisitorName(), command.getID(), notification);
        command.getGame().setID(command.getID());
        LoadGame load = new LoadGame(command.getGame());
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        gameDAO.refresh(load.getID(), load.getServerGame());
        session.getRemote().sendString(new Gson().toJson(load));
    }
    private void leaveGame(Session session, String message) throws IOException {
        LeaveGameCommand command = new Gson().fromJson(message, LeaveGameCommand.class);
        Notification notification = new Notification(command.getMessage());
        connections.broadcast(command.getVisitorName(), command.getId(), notification);
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        if (!command.getPlayerColor().equals("observer")) {
            gameDAO.removePlayer(command.getId(), command.getPlayerColor());
        }
        connections.removePlayer(command.getVisitorName(), command.getId());
    }
    private void resignGame(Session session, String message) throws IOException {
        ResignGameCommand command = new Gson().fromJson(message, ResignGameCommand.class);
        Notification notification = new Notification(command.getMessage());
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        gameDAO.removeGame(command.getId());
        connections.broadcast(command.getVisitorName(), command.getId(), notification);
        connections.removeGame(command.getId());
    }
    private void makeMove(Session session, String message) throws IOException {
        MakeMoveCommand command = new Gson().fromJson(message, MakeMoveCommand.class);
        Notification notification = new Notification(command.getMessage());
        command.getGame().setID(command.getID());
        LoadGame load = new LoadGame(command.getGame());
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        gameDAO.refresh(load.getID(), load.getServerGame());
        connections.broadcast(command.getVisitorName(), command.getID(), notification);
        connections.refresh(command.getVisitorName(), command.getID(), load);
    }
}