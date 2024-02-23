package dataAccess;

import dataAccess.dataModelClasses.AuthData;

public interface AuthDAO {
    void clear();
    AuthData insertAuth(String username, String auth_token);
    void delete(String authToken);
}
