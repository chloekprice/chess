package dataAccess;

import dataAccess.dataModelClasses.AuthData;

public interface AuthDAO {
    public void clear();
    public AuthData insertAuth(String username, String auth_token);
}
