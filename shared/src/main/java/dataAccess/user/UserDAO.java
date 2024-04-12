package dataAccess.user;

import dataAccess.DataAccessException;
import model.UserData;

public interface UserDAO {
    void clear() throws DataAccessException;
     UserData getUser(String username);

    UserData insertUser(String username, String password, String email) throws DataAccessException;
}
