package dataAccess;

import model.AuthData;

public interface AuthDAO {
    void clear();
    AuthData insertAuth(String username, String auth_token);
    void delete(String authToken);
    AuthData getAuth(String authToken);
}
