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
import java.util.Objects;


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
            session.getRemote().sendString(new Gson().toJson(error));
        }
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
            if (Objects.equals(gameAccess.getGame(command.getId()).getWhiteUsername(), user)) {
                gameAccess.removePlayer(command.getId(), "WHITE");
            } else if (Objects.equals(gameAccess.getGame(command.getId()).getBlackUsername(), user)) {
                gameAccess.removePlayer(command.getId(), "BLACK");
            }

            connections.removePlayer(command.getAuthString(), command.getId());
            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
            connections.broadcast(command.getAuthString(), command.getId(), notification);
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
    private void resignGame(Session session, String message) throws IOException {
        try {
            ResignGameCommand command = new Gson().fromJson(message, ResignGameCommand.class);
            MySQLGameDAO gameAccess = new MySQLGameDAO();
            if (gameAccess.getGame(command.getId()) != null) {
                MySQLAuthDAO authAccess = new MySQLAuthDAO();
                String user = authAccess.getAuth(command.getAuthString()).getUsername();


                if (Objects.equals(gameAccess.getGame(command.getId()).getBlackUsername(), user)) {
                    command.setMessage(user, ChessGame.TeamColor.BLACK);
                } else if (Objects.equals(gameAccess.getGame(command.getId()).getWhiteUsername(), user)) {
                    command.setMessage(user, ChessGame.TeamColor.WHITE);
                } else {
                    throw new DataAccessException("Error: cannot resign game");
                }

                Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, command.getMessage());
                gameAccess.removeGame(command.getId());
                connections.broadcast(null, command.getId(), notification);
                connections.removeGame(command.getId());
            } else {
                throw new DataAccessException("Error: invalid resignation");
            }
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
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
            command.setMessage(game.getBoard().getPiece(command.getMove().getEndPosition()).getPieceType());

            String realMessage;
            if (game.isInCheckmate(ChessGame.TeamColor.BLACK)) {
                realMessage = command.getMessage() + "\n BLACK is in checkmate";
            } else if (game.isInCheckmate(ChessGame.TeamColor.WHITE)) {
                realMessage = command.getMessage() + "\n WHITE is in checkmate";
            } else if (game.isInCheck(ChessGame.TeamColor.WHITE)) {
                realMessage = command.getMessage() + "\n WHITE is in check";
            } else if (game.isInCheck(ChessGame.TeamColor.BLACK)) {
                realMessage = command.getMessage() + "\n BLACK is in check";
            } else {
                realMessage = command.getMessage();
            }

            Notification notification = new Notification(ServerMessage.ServerMessageType.NOTIFICATION, realMessage);
            LoadGame load = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, game);
            gameAccess.refresh(load.getID(), load.getServerGame());

            if (realMessage.equals(command.getMessage())) {
                connections.broadcast(command.getAuthString(), command.getID(), notification);
            } else {
                connections.broadcast(null, command.getID(), notification);
            }
            connections.refresh(null, command.getID(), load);
        } catch (Exception e) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, e.getMessage());
            session.getRemote().sendString(new Gson().toJson(error));
        }
    }
}