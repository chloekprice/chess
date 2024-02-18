package server.service;

import dataAccess.*;
import dataAccess.dataModelClasses.authData;
import dataAccess.dataModelClasses.userData;

import java.util.UUID;

public class chessService {
    private final memoryAuthDAO authDataAccess;
    private final memoryGameDAO gameDataAccess;
    private final userDAO userDataAccess;

    // empty constructor
    public chessService() {
        this.authDataAccess = new memoryAuthDAO();
        this.gameDataAccess = new memoryGameDAO();
        this.userDataAccess = new memoryUserDAO();
    }

    // clear handler
    public void clearHandler() throws DataAccessException {
        authDataAccess.clear();
        gameDataAccess.clear();
        userDataAccess.clear();
    }
    // register handlers
    public authData registerHandler(String username, String password, String email) throws DataAccessException {
        authData auth_data = null;

        userData user_data = getUser(username);
        if (user_data == null) {
            createUser(username, password, email);
            auth_data = createAuth(username);
        } else {
            throw new DataAccessException("Error: user already taken");
        }

        return auth_data;
    }
    private userData getUser(String username) {
        return userDataAccess.getUser(username);
    }
    private void createUser(String username, String password, String email) {
        userDataAccess.insertUser(username, password, email);
    }
    private authData createAuth(String username) {
        String auth_token = UUID.randomUUID().toString();
         return authDataAccess.insertAuth(username,auth_token);
    }
}
