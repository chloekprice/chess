package dataAccess.auth;

import dataAccess.DataAccessException;
import model.AuthData;

public interface AuthDAO {
    void clear() throws DataAccessException;
    AuthData insertAuth(String username, String auth_token);
    void delete(String authToken);
    AuthData getAuth(String authToken);
}
