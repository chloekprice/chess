package server;

import dataAccess.DataAccessException;
import spark.*;
import server.service.chessService;
import dataAccess.DAO;

import javax.xml.crypto.Data;

public class Server {
    private final chessService service;

    public Server(DAO dataAccess) {
        service = new chessService(dataAccess);
    }
    // empty constructor
    public Server() {
        service = null;
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("/web");

        // Register your endpoints and handle exceptions here.

        //register handler
        Spark.post("/user", this::register);
        //delete handler
        Spark.delete("/db", this::clear);

        Spark.awaitInitialization();
        return Spark.port();
    }

    public int port() {
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private Object register(Request req, Response res) throws DataAccessException {
        res.status(200);
        return res.body();
    }
    private Object clear(Request req, Response res) throws DataAccessException {
        if (service != null) {
            service.clearHandler();
        } else {
            throw new DataAccessException("service is null");
        }
        res.status(200);
        return "";
    }
}
