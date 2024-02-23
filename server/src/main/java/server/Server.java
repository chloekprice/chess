package server;

import com.google.gson.Gson;
import dataAccess.*;
import dataAccess.dataModelClasses.AuthData;
import spark.*;
import server.service.chessService;


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
        ResultInfo result = null;

        HashMap<String, Object> paramsMap = new Gson().fromJson(req.body(), HashMap.class);
        // parse request body
        try {
            String username = paramsMap.get("username").toString();
            String password = paramsMap.get("password").toString();
            String email = paramsMap.get("email").toString();

            result = service.registerHandler(username, password, email);
            res.status(result.getStatus());
            return new Gson().toJson(result);
        } catch (Exception e){
            result = new ResultInfo();
            result.setStatus(400);
            result.setMessage("Error: bad request");
            res.status(result.getStatus());
            return new Gson().toJson(result);
        }
    }
    // login to existing user
    private Object login(Request req, Response res) throws DataAccessException {
        HashMap<String, Object> paramsMap = new Gson().fromJson(req.body(), HashMap.class);
        ResultInfo result = null;
        AuthData authData = null;

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
