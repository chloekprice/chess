package ui.websockets;

import com.google.gson.Gson;
import exception.ResponseException;
import ui.display.EscapeSequences;
import webSocketMessages.userCommands.JoinPlayerGameCommand;
import webSocketMessages.userCommands.UserGameCommand;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
                    //print notification
                    JoinPlayerGameCommand notification = new Gson().fromJson(message, JoinPlayerGameCommand.class);
                    System.out.println(EscapeSequences.SET_TEXT_COLOR_RED);
                    System.out.println(notification.getMessage());
                    // print input indicator
                    System.out.println();
                    System.out.print(SET_TEXT_COLOR_WHITE);
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
            UserGameCommand makeCommand = new JoinPlayerGameCommand(authToken, visitorName, playerColor);
            session.getBasicRemote().sendText(new Gson().toJson(makeCommand));
//            var action = new JoinPlayerGameCommand(visitorName);
//            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void observeChessGame(String authToken) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken);
//            action.setCommandType(UserGameCommand.CommandType.JOIN_OBSERVER);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }
    public void resignChessGame(String authToken) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken);
//            action.setCommandType(UserGameCommand.CommandType.RESIGN);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
            this.session.close();
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void leaveChessGame(String authToken) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken);
//            action.setCommandType(UserGameCommand.CommandType.LEAVE);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
            this.session.close();
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

}
