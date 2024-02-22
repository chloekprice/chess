package server.service;

import dataAccess.*;
import dataAccess.dataModelClasses.authData;
import dataAccess.dataModelClasses.userData;

import java.util.UUID;

public class chessService {
    private final MemoryAuthDAO authDataAccess;
    private final MemoryGameDAO gameDataAccess;
    private final UserDAO userDataAccess;

    // empty constructor
    public chessService() {
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
    public authData registerHandler(String username, String password, String email) throws DataAccessException {
        authData authData = null;

        userData userData = getUser(username);
        if (userData == null) {
            createUser(username, password, email);
            authData = createAuth(username);
        } else {
            throw new DataAccessException("Error: user already taken");
        }

        return authData;
    }
    // login handler
    public authData loginHandler(String username, String password) throws DataAccessException {
        authData authData = null;

        userData userData = getUser(username);
        if (userData == null) {
            return authData;
//            throw new DataAccessException("Error: user does not exist");
        } else if (!userData.getPassword().equals(password)) {
            throw new DataAccessException("Error: wrong password");
        } else {
            authData = createAuth(username);
        }
        return authData;
    }
    // helper functions
    private userData getUser(String username) {
        return userDataAccess.getUser(username);
    }
    private void createUser(String username, String password, String email) {
        userDataAccess.insertUser(username, password, email);
    }
    private authData createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
         return authDataAccess.insertAuth(username,authToken);
    }
}
