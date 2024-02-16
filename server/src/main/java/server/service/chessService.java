package server.service;

import dataAccess.DataAccessException;
import dataAccess.DAO;

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
}
