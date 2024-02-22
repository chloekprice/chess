package server;

import com.google.gson.Gson;
import dataAccess.*;
import dataAccess.dataModelClasses.authData;
import org.eclipse.jetty.util.ajax.JSON;
import spark.*;
import server.service.chessService;


import javax.xml.crypto.Data;
import java.util.HashMap;

public class Server {
    private final chessService service;
    public Server() {
          service = new chessService();
    }


    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.get("/", this::test);
        // register handler
        Spark.post("/user", this::registerUser);
        // login handler
        Spark.post("/session", this::login);
        // logout handler
        Spark.delete("/session", this::logout);
        // list games handler
        Spark.get("/game", this::listGames);
        // create game handler
        Spark.post("/game", this::createGame);
        // join game handler
        Spark.put("/game", this::joinGame);
        // delete handler
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
    private Object registerUser(Request req, Response res) throws DataAccessException {
        authData authData = null;


        HashMap<String, Object> paramsMap = new Gson().fromJson(req.body(), HashMap.class);

        // parse request body
        String username = paramsMap.get("username").toString();
        String password = paramsMap.get("password").toString();
        String email = paramsMap.get("email").toString();

        authData = service.registerHandler(username, password, email);
        res.status(200);


        if (authData == null) {
            res.status(401);
        }

        return new Gson().toJson(authData);
    }
    // login to existing user
    private Object login(Request req, Response res) throws DataAccessException {
        HashMap<String, Object> paramsMap = new Gson().fromJson(req.body(), HashMap.class);
        ResultInfo result = null;
        authData authData = null;

        // parse request body
        String username = paramsMap.get("username").toString();
        String password = paramsMap.get("password").toString();

        result = service.loginHandler(username, password);
        res.status(result.getStatus());
        return new Gson().toJson(result);
    }
    // logout of account
    private Object logout(Request req, Response res) throws DataAccessException {
        res.status(200);
        return "";
    }
    // create a new game
    private Object createGame(Request req, Response res) throws DataAccessException {
        res.status(200);
        return "";
    }
    // join an existing game
    private Object joinGame(Request req, Response res) throws DataAccessException {
        res.status(200);
        return "";
    }
    // list all available games
    private Object listGames(Request req, Response res) throws DataAccessException {
        res.status(200);
        return "";
    }
    // clear databases
    private Object clear(Request req, Response res) throws DataAccessException {

        service.clearHandler();
        res.status(200);

        return "";
    }
}
