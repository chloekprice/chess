package ui.websockets;

import chess.ChessBoard;
import chess.ChessGame;
import com.google.gson.Gson;
import exception.ResponseException;
import ui.display.EscapeSequences;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.JoinPlayerGameCommand;
import webSocketMessages.userCommands.LeaveGameCommand;
import webSocketMessages.userCommands.UserGameCommand;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static ui.display.EscapeSequences.SET_TEXT_COLOR_BLACK;
import static ui.display.EscapeSequences.SET_TEXT_COLOR_WHITE;

//need to extend Endpoint for websocket to work properly
public class WebSocketFacade extends Endpoint {

    Session session;

    public WebSocketFacade(String url) throws ResponseException {
        try {
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
                        }
                    } catch (Exception e) {
                        System.out.print("error");
                    }
                    //print notification
//                    JoinPlayerGameCommand notification = new Gson().fromJson(message, JoinPlayerGameCommand.class);
//                    System.out.println(notification.getMessage());
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

    public void joinChessGame(String authToken, String visitorName, String playerColor) throws ResponseException {
        try {
            JoinPlayerGameCommand makeCommand = new JoinPlayerGameCommand(authToken, visitorName, playerColor);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void leaveChessGame(String authToken, String visitorName, int gameID) throws ResponseException {
        try {
            LeaveGameCommand makeCommand = new LeaveGameCommand(authToken, visitorName, gameID);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    private void notification(String message) {
        Notification notification = new Gson().fromJson(message, Notification.class);
        System.out.println(EscapeSequences.SET_TEXT_COLOR_RED);
        System.out.println(notification.getServerMessage());
    }

}
