package server.service;

import dataAccess.DataAccessException;
import dataAccess.DAO;
import dataAccess.dataModelClasses.*;
import dataAccess.memoryUserDAO;

import javax.xml.crypto.Data;
import java.util.UUID;

public class chessService {
    private final DAO dataAccess;

    public chessService(DAO dataAccess) {
        this.dataAccess = dataAccess;
    }
    // empty constructor
    public chessService() {
        this.dataAccess = null;
    }

    // clear handler
    public void clearHandler() throws DataAccessException {
        if (dataAccess != null) {
            dataAccess.clear();
        } else {
            throw new DataAccessException(("data access is null"));
        }
    }
    // register handlers
    public String registerHandler(String username, String password, String email) throws DataAccessException {
        String auth_token = null;

        if (dataAccess != null) {
            memoryUserDAO user_data = getUser(username);
            if (user_data == null) {
                createUser(username, password, email);
                auth_token = createAuth(username);
            } else {
                throw new DataAccessException("Error: user already taken");
            }
        } else {
            throw new DataAccessException("Error: data access is null");
        }

        return auth_token;
    }
    private memoryUserDAO getUser(String username) {
        return dataAccess.getUser();
    }
    private void createUser(String username, String password, String email) {
        return;
    }
    private String createAuth(String username) {
        String auth_token = String.valueOf(UUID.fromString(username));
        return dataAccess.insertAuth(username,auth_token);
    }
}
