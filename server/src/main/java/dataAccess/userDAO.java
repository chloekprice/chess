package dataAccess;

import dataAccess.dataModelClasses.userData;

public interface userDAO{
    void clear() throws DataAccessException;
    public userData getUser(String username);

    void insertUser(String username, String password, String email);
}
