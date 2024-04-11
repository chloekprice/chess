package server.websockets;


import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<Integer, ConcurrentHashMap<String, Connection>> connections = new ConcurrentHashMap<>();

    private void addGameConnection(String visitorName, int gameID, Session session) {
        Integer id = gameID;
        Connection firstPlayer = new Connection(visitorName, session);
        ConcurrentHashMap<String, Connection> players = new ConcurrentHashMap<>();
        players.put(visitorName, firstPlayer);
        connections.put(id, players);
    }
    public void addPlayerConnection(String visitorName, int gameID, Session session) {
        Integer id = gameID;
        if (connections.containsKey(id)) {
            Connection connection = new Connection(visitorName, session);
            connections.get(id).put(visitorName, connection);
        } else {
            addGameConnection(visitorName, gameID, session);
        }
    }

    public void removePlayer(String visitorName, int gameID) {
        Integer id = gameID;
        connections.get(id).remove(visitorName);
    }

    public void removeGame(int gameID) {
        Integer id = gameID;
        connections.remove(id);
    }

    public void broadcast(String excludeVisitorName, int gameID, Notification message) throws IOException {
        Integer id = gameID;
        var removeList = new ArrayList<Connection>();
        for (var c : connections.keySet()) {
            if (c.equals(id)) {
                for (var p : connections.get(c).keySet()) {
                    if (connections.get(c).get(p).getSession().isOpen()){
                        if (!p.equals(excludeVisitorName)) {
                            connections.get(c).get(p).send(message);
                        }
                    } else{
                        removeList.add(connections.get(c).get(p));
                    }
                }
            }
        }

        // Clean up any connections that were left open.
        for (var c : removeList) {
            connections.get(id).remove(c.visitorName);
        }
    }

    public void refresh(String excludeVisitorName, int gameID, LoadGame message) throws IOException {
        Integer id = gameID;
        var removeList = new ArrayList<Connection>();
        for (var c : connections.keySet()) {
            if (c.equals(id)) {
                for (var p : connections.get(c).keySet()) {
                    if (connections.get(c).get(p).getSession().isOpen()){
                        if (!p.equals(excludeVisitorName)) {
                            connections.get(c).get(p).update(message);
                        }
                    } else{
                        removeList.add(connections.get(c).get(p));
                    }
                }
            }
        }

        // Clean up any connections that were left open.
        for (var c : removeList) {
            connections.get(id).remove(c.visitorName);
        }
    }
}
