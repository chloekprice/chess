package server.websockets;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;

public class Connection {
    public String authToken;
    public Session session;

    public Connection(String authToken, Session session) {
        this.authToken = authToken;
        this.session = session;
    }

    public void send(Notification msg) throws IOException {
        String notification = new Gson().toJson(msg, Notification.class);
        session.getRemote().sendString(notification);
    }
    public void update(LoadGame msg) throws IOException {
        String notification = new Gson().toJson(msg, LoadGame.class);
        session.getRemote().sendString(notification);
    }
    public Session getSession() {
        return this.session;
    }
}
