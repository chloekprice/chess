package server.websockets;

import chess.ChessGame;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.auth.MySQLAuthDAO;
import dataAccess.game.MySQLGameDAO;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import server.ResultInfo;
import service.ChessService;
import webSocketMessages.serverMessages.Error;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.userCommands.*;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;


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
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));        }
    }

    private void joinPlayer(Session session, String message) throws IOException {
        try {
            JoinPlayerGameCommand command = new Gson().fromJson(message, JoinPlayerGameCommand.class);

            MySQLAuthDAO authAccess = new MySQLAuthDAO();
            String user = authAccess.getAuth(command.getAuthString()).getUsername();

            MySQLGameDAO gameAccess = new MySQLGameDAO();
            if (command.getPlayerColor().equals(ChessGame.TeamColor.BLACK)) {
                if (!gameAccess.getGame(command.getID()).getBlackUsername().equals(user)) {
                    throw new DataAccessException("Error: did not successfully join game");
                }
            } else if (command.getPlayerColor().equals(ChessGame.TeamColor.WHITE)) {
                if (!gameAccess.getGame(command.getID()).getWhiteUsername().equals(user)) {
                    throw new DataAccessException("Error: did not successfully join game");
                }
            }

            ChessService service = new ChessService();
            ResultInfo result = service.joinGameHandler(command.getAuthString(), command.getPlayerColor().toString(), command.getID());
            command.setGame(result.getGame());
            command.setMessage(result.getAuthData().getUsername());

            connections.addPlayerConnection(command.getAuthString(), command.getID(), session);
            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
            connections.broadcast(command.getAuthString(), command.getID(), notification);

            result.getGame().setID(command.getID());
            LoadGame load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, result.getGame());
            session.getRemote().sendString(new Gson().toJson(load, LoadGame.class));
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
    private void joinObserver(Session session, String message) throws IOException {
        try {
            ObserveGameCommand command = new Gson().fromJson(message, ObserveGameCommand.class);

            ChessService service = new ChessService();
            ResultInfo result = service.joinGameHandler(command.getAuthString(), null, command.getID());
            command.setGame(result.getGame());
            command.setMessage(result.getAuthData().getUsername());

            connections.addPlayerConnection(command.getAuthString(), command.getID(), session);
            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
            connections.broadcast(command.getAuthString(), command.getID(), notification);
            command.getGame().setID(command.getID());
            LoadGame load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, command.getGame());
            MySQLGameDAO gameDAO = new MySQLGameDAO();
            gameDAO.refresh(load.getID(), load.getServerGame());
            session.getRemote().sendString(new Gson().toJson(load));
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
    private void leaveGame(Session session, String message) throws IOException {
        try {
            LeaveGameCommand command = new Gson().fromJson(message, LeaveGameCommand.class);

            MySQLAuthDAO authAccess = new MySQLAuthDAO();
            String user = authAccess.getAuth(command.getAuthString()).getUsername();
            command.setMessage(user);

            MySQLGameDAO gameAccess = new MySQLGameDAO();
            if (gameAccess.getGame(command.getId()).getWhiteUsername().equals(user)) {
                gameAccess.removePlayer(command.getId(), "WHITE");
            } else if (gameAccess.getGame(command.getId()).getBlackUsername().equals(user)) {
                gameAccess.removePlayer(command.getId(), "BLACK");
            }

            connections.removePlayer(command.getVisitorName(), command.getId());
            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
            connections.broadcast(null, command.getId(), notification);
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
    private void resignGame(Session session, String message) throws IOException {
        ResignGameCommand command = new Gson().fromJson(message, ResignGameCommand.class);
        Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
        MySQLGameDAO gameDAO = new MySQLGameDAO();
        gameDAO.removeGame(command.getId());
        connections.broadcast(command.getAuthString(), command.getId(), notification);
        connections.removeGame(command.getId());
    }
    private void makeMove(Session session, String message) throws IOException {
        try {
            MakeMoveCommand command = new Gson().fromJson(message, MakeMoveCommand.class);

            MySQLAuthDAO authAccess = new MySQLAuthDAO();
            String user = authAccess.getAuth(command.getAuthString()).getUsername();
            MySQLGameDAO gameAccess = new MySQLGameDAO();
            ChessGame game = gameAccess.getGame(command.getID()).getGame();
            ChessGame.TeamColor turn = game.getTeamTurn();
            if (turn.equals(ChessGame.TeamColor.BLACK)) {
                if (!gameAccess.getGame(command.getID()).getBlackUsername().equals(user)) {
                    throw new Exception("Error: not your turn");
                }
            } else if (!gameAccess.getGame(command.getID()).getWhiteUsername().equals(user)) {
                throw new Exception("Error: not your turn");
            }

            game.makeMove(command.getMove());
            game.setID(command.getID());
            gameAccess.refresh(game.getID(), game);

            command.setMessage();
            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
            LoadGame load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, game);
            gameAccess.refresh(load.getID(), load.getServerGame());

            connections.broadcast(command.getAuthString(), command.getID(), notification);
            connections.refresh(null, command.getID(), load);
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
}