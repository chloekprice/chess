package dataAccess;

import dataAccess.dataModelClasses.authData;

public interface authDAO {
    public void clear();
    public authData insertAuth(String username, String auth_token);
}
