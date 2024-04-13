package ui.websockets;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;
import com.google.gson.Gson;
import exception.ResponseException;
import server.ResultInfo;
import ui.ChessClient;
import ui.display.ChessBoardPrinter;
import ui.display.EscapeSequences;
import ui.requestBody.User;
import webSocketMessages.serverMessages.Error;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.*;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static ui.display.EscapeSequences.SET_TEXT_COLOR_BLACK;
import static ui.display.EscapeSequences.SET_TEXT_COLOR_WHITE;

//need to extend Endpoint for websocket to work properly
public class WebSocketFacade extends Endpoint {

    Session session;
    ChessClient client;

    public WebSocketFacade(String url, ChessClient client) throws ResponseException {
        try {
            this.client = client;
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/connect");

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session.setMaxIdleTimeout(5000);
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage action = new Gson().fromJson(message, ServerMessage.class);
                    try {
                        switch (action.getServerMessageType()) {
                            case ServerMessage.ServerMessageType.NOTIFICATION -> notification(message);
                            case ServerMessage.ServerMessageType.LOAD_GAME -> load(message);
                            case ServerMessage.ServerMessageType.ERROR -> error(message);
                        }
                    } catch (Exception e) {
                        System.out.print("error");
                    }
                    // print input indicator
                    System.out.println();
                    System.out.print(SET_TEXT_COLOR_BLACK);
                    System.out.print("[GAMEPLAY MODE] >>> ");
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    //Endpoint requires this method, but you don't have to do anything
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    public void joinChessGame(String authToken, ChessGame.TeamColor playerColor, int gameID) throws ResponseException {
        try {
            JoinPlayerGameCommand makeCommand = new JoinPlayerGameCommand(authToken, UserGameCommand.CommandType.JOIN_PLAYER, gameID, playerColor);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, ex.getMessage());
            error(error.getServerMessage());
        }
    }
    public void leaveChessGame(String authToken, int gameID) throws ResponseException {
        try {
            LeaveGameCommand makeCommand = new LeaveGameCommand(authToken, UserGameCommand.CommandType.LEAVE, gameID);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void resignChessGame(String authToken, String visitorName, int gameID, ChessGame.TeamColor playerColor) throws ResponseException {
        try {
            ResignGameCommand makeCommand = new ResignGameCommand(authToken, visitorName, gameID, playerColor);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void observeChessGame(String authToken, int gameID) throws ResponseException {
        try {
            ObserveGameCommand makeCommand = new ObserveGameCommand(authToken, UserGameCommand.CommandType.JOIN_OBSERVER, gameID);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void sendUpdatedGame(String authToken, String visitorName, int gameID, ChessMove move) throws ResponseException {
        try {
            MakeMoveCommand makeCommand = new MakeMoveCommand(authToken, UserGameCommand.CommandType.MAKE_MOVE, gameID, move);
            makeCommand.setVisitorName(visitorName);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            Error error = new Error(ServerMessage.ServerMessageType.ERROR, ex.getMessage());
            error(error.getServerMessage());
        }
    }

    private void notification(String message) {
        Notification notification = new Gson().fromJson(message, Notification.class);
        System.out.println(EscapeSequences.SET_TEXT_COLOR_YELLOW);
        System.out.println(notification.getServerMessage());
    }
    private void error(String message) {
        Error error = new Gson().fromJson(message, Error.class);
        System.out.println(EscapeSequences.SET_TEXT_COLOR_RED);
        System.out.println(error.getServerMessage());
    }
    private void load(String message) throws ResponseException {
        LoadGame game = new Gson().fromJson(message, LoadGame.class);
        client.updateGame(game.getServerGame());
        client.redraw();
    }

}
