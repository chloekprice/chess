package dataAccess;

import dataAccess.dataModelClasses.userData;

public interface DAO {
    void clear() throws DataAccessException;
    abstract memoryUserDAO getUser();
    abstract String insertAuth(String username, String auth_token);

}
