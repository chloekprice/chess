package service;

import dataAccess.*;
import model.AuthData;
import model.GameData;
import model.UserData;
import server.ResultInfo;


import java.util.HashSet;
import java.util.UUID;

public class ChessService {
    private final AuthDAO authDataAccess;
    private final GameDAO gameDataAccess;
    private final UserDAO userDataAccess;

    // empty constructor
    public ChessService() {
        this.authDataAccess = new MemoryAuthDAO();
        this.gameDataAccess = new MemoryGameDAO();
        this.userDataAccess = new MemoryUserDAO();
    }

    // clear handler
    public void clearHandler() throws DataAccessException {
        authDataAccess.clear();
        gameDataAccess.clear();
        userDataAccess.clear();
    }

    // register handler
    public ResultInfo registerHandler(String username, String password, String email) throws DataAccessException {
        AuthData authData = null;
        ResultInfo result = new ResultInfo();

        if (username == null || password == null || email == null) {
            result.setStatus(400);
            result.setMessage("Error: bad request");
            return result;
        }

        UserData userData = getUser(username);
        if (userData == null) {
            userData = createUser(username, password, email);
            authData = createAuth(username);
        } else {
            result.setStatus(403);
            result.setMessage("Error: already taken");
            return result;
        }
        result.setUserData(userData);
        result.setAuthData(authData);
        return result;
    }

    // login handler
    public ResultInfo loginHandler(String username, String password) throws DataAccessException {
        ResultInfo result = new ResultInfo();
        AuthData authData = null;

        UserData userData = getUser(username);
        if (userData == null) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized; user does not exist");
            return result;
        } else if (!userData.getPassword().equals(password)) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized; incorrect password");
            return result;
        } else {
            authData = createAuth(username);
            result.setAuthData(authData);
            result.setUserData(userData);
        }
        return result;
    }

    // logout handler
    public ResultInfo logoutHandler(String authToken) throws DataAccessException {
        ResultInfo result = new ResultInfo();

        if (getAuth(authToken) == null) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized");
        } else {
            deleteAuth(authToken);
        }
        return result;
    }

    // create game handler
    public ResultInfo createGameHandler(String authToken, String gameName) throws DataAccessException {
        ResultInfo result = new ResultInfo();
        // bad request
        if (authToken == null || gameName == null) {
            result.setStatus(400);
            result.setMessage("Error: bad request");
            return result;
        }
        // unauthorized
        AuthData authData = getAuth(authToken);
        if (authData == null) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized");
            return result;
        }
        // valid
        int id = createID();
        GameData newGame = createGame(gameName, id);
        result.setGameData(newGame);

        return result;
    }

    // join game handler
    public ResultInfo joinGameHandler(String authToken, String playerColor, int gameID) throws DataAccessException{
        ResultInfo result = new ResultInfo();

        AuthData authData = getAuth(authToken);
        if (authData == null) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized");
            return result;
        }

        GameData game = getGame(gameID);
        if (playerColor == null) {
            result.setGameData(game);
            return result;
        }

        if (playerColor.equals("BLACK") || playerColor.equals("WHITE")) {
            if (playerColor.equals("BLACK")) {
                if (game.getBlackUsername() != null) {
                    result.setStatus(403);
                    result.setMessage("Error: already taken");
                    return result;
                }
            } else if (game.getWhiteUsername() != null) {
                result.setStatus(403);
                result.setMessage("Error: already taken");
                return result;
            }
            game.updateGame(playerColor, authData.getUsername());
            result.setGameData(game);
            return result;
        } else {
            result.setStatus(500);
            result.setMessage("Error: invalid color");
            return result;
        }
    }

    // list all games
    public ResultInfo listGamesHandler(String authToken) throws DataAccessException {
        ResultInfo result = new ResultInfo();
        // bad request
        if (authToken == null) {
            result.setStatus(400);
            result.setMessage("Error: bad request");
            return result;
        }
        // unauthorized
        AuthData authData = getAuth(authToken);
        if (authData == null) {
            result.setStatus(401);
            result.setMessage("Error: unauthorized");
            return result;
        }
        // valid
        HashSet<GameData> gameList = gameDataAccess.getGameList();
        result.setGames(gameList);

        return result;
    }

    // helper functions
    private UserData getUser(String username) {
        return userDataAccess.getUser(username);
    }
    private UserData createUser(String username, String password, String email) {
        return userDataAccess.insertUser(username, password, email);
    }
    private AuthData createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
         return authDataAccess.insertAuth(username,authToken);
    }
    private AuthData getAuth(String authToken) {
        return authDataAccess.getAuth(authToken);
    }
    private void deleteAuth(String authToken) {
        authDataAccess.delete(authToken);
    }
    private int createID() {
        return ((int)(Math.random() * (10000)));
    }
    private GameData createGame(String gameName, int id) {
        return gameDataAccess.create(gameName, id);
    }
    private GameData getGame(int id) {
        return gameDataAccess.getGame(id);
    }
}
