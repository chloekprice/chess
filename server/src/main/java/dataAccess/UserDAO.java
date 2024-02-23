package dataAccess;

import dataAccess.dataModelClasses.UserData;

public interface UserDAO {
    void clear() throws DataAccessException;
     UserData getUser(String username);

    UserData insertUser(String username, String password, String email);
}
