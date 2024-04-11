package ui.websockets;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import com.google.gson.Gson;
import exception.ResponseException;
import ui.ChessClient;
import ui.display.ChessBoardPrinter;
import ui.display.EscapeSequences;
import ui.requestBody.User;
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

    public void joinChessGame(String authToken, String visitorName, ChessGame.TeamColor playerColor, int gameID, ChessGame game) throws ResponseException {
        try {
            UserGameCommand cmd = new UserGameCommand(authToken);
            JoinPlayerGameCommand makeCommand = new JoinPlayerGameCommand(authToken, gameID, playerColor);
            makeCommand.setGame(game);
            makeCommand.setMessage(visitorName);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void leaveChessGame(String authToken, String visitorName, int gameID, ChessGame.TeamColor playerColor) throws ResponseException {
        try {
            LeaveGameCommand makeCommand = new LeaveGameCommand(authToken, visitorName, gameID, playerColor);
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
    public void observeChessGame(String authToken, String visitorName, int gameID) throws ResponseException {
        try {
            ObserveGameCommand makeCommand = new ObserveGameCommand(authToken, visitorName, gameID);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void sendUpdatedGame(String authToken, String visitorName, int gameID, ChessGame game, ChessPiece piece) throws ResponseException {
        try {
            MakeMoveCommand makeCommand = new MakeMoveCommand(authToken, visitorName, piece, game, gameID);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    private void notification(String message) {
        Notification notification = new Gson().fromJson(message, Notification.class);
        System.out.println(EscapeSequences.SET_TEXT_COLOR_YELLOW);
        System.out.println(notification.getServerMessage());
    }
    private void load(String message) throws ResponseException {
        LoadGame game = new Gson().fromJson(message, LoadGame.class);
        client.updateGame(game.getServerGame());
        client.redraw();
    }

}
