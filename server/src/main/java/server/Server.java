package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import spark.*;
import server.service.chessService;
import dataAccess.DAO;

import javax.xml.crypto.Data;

public class Server {
    private final chessService service;

    //constructor
    public Server(DAO dataAccess) {
        service = new chessService(dataAccess);
    }

    //empty constructor
    public Server() {
        service = null;
        System.out.println("constructed empty server");
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("java/resources");

        // Register your endpoints and handle exceptions here.
        Spark.get("/", this::test);
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

    // test
    private Object test(Request req, Response res) throws DataAccessException {
        res.status(200);
        return "CS 240 Chess Server Web API";
    }

    // register new user
    private Object register(Request req, Response res) throws DataAccessException {
        String auth_token = null;
        var username = req.params(":username");
        var password = req.params(":password");
        var email = req.params(":email");

        if (service != null) {
            auth_token = service.registerHandler(username, password, email);
            res.status(200);
        } else {
            res.status(200);
//            throw new DataAccessException("Error: service is null");
        }

        return new Gson().toJson(auth_token);
    }

    // clear databases
    private Object clear(Request req, Response res) throws DataAccessException {
        if (service != null) {
            service.clearHandler();
            res.status(200);
        } else {
            res.status(200);
//            throw new DataAccessException("Error: service is null");
        }
        return "";
    }
}
