package ui;

import chess.ChessGame;
import com.google.gson.Gson;
import exception.ResponseException;
import server.ResultInfo;
import ui.requestBody.Game;
import ui.requestBody.User;

import java.io.*;
import java.net.*;

public class ServerFacade {

    private final String serverUrl;
    private String authToken;

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public ResultInfo registerUser(String username, String password, String email) throws ResponseException {
        var path = "/user";
        return this.makeRequest("POST", path, new User(username, password, email));
    }

    public ResultInfo loginUser(String username, String password) throws ResponseException{
        var path = "/session";
        ResultInfo test = this.makeRequest("POST", path, new User(username, password));
        return test;
    }

    public ResultInfo logoutUser(String authToken) throws ResponseException{
        var path = "/session";
        this.authToken = authToken;
        return this.makeRequest("DELETE", path, null);
    }
    public ResultInfo create(String gameName, String authToken) throws ResponseException{
        var path = "/game";
        this.authToken = authToken;
        return this.makeRequest("POST", path, new Game(gameName));
    }
    public ResultInfo list(String authToken) throws ResponseException{
        var path = "/game";
        this.authToken = authToken;
        return this.makeRequest("GET", path, null);
    }
    public ResultInfo join(int gameID, String playerColor, String authToken) throws ResponseException{
        var path = "/game";
        this.authToken = authToken;
        return this.makeRequest("PUT", path, new Game(null, gameID, playerColor));
    }
    public ResultInfo clear() throws ResponseException{
        var path = "/db";
        this.authToken = authToken;
        return this.makeRequest("DELETE", path, null);
    }

    private <T> T makeRequest(String method, String path, Object request) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            http.addRequestProperty("authorization", authToken);

            if (request != null) {
                writeBody(request, http);
            }
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, (Class<T>) ResultInfo.class);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            if (status == 400) {
                throw new ResponseException(status, "Error " + status + ": bad request");
            } else if (status == 401) {
                throw new ResponseException(status, "Error " + status + ": unauthorized");
            } else if (status == 403) {
                throw new ResponseException(status, "Error " + status + ": already taken");
            } else if (status == 500) {
                throw new ResponseException(status, "Error " + status + ": something went wrong");
            }
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}
