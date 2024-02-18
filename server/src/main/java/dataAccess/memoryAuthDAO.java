package dataAccess;

import dataAccess.dataModelClasses.authData;

import java.util.HashMap;

public class memoryAuthDAO implements authDAO{
    HashMap<String, authData> auth;
    public memoryAuthDAO() {
        HashMap<String, authData> auth = new HashMap<String, authData>();
    }
    public void clear() {
        if (auth != null) {
            auth.clear();
        }
    }
    public authData insertAuth(String username, String auth_token) {
        authData auth_data = new authData(username, auth_token);
        if (auth == null) {
            auth = new HashMap<String, authData>();
        }
        auth.put(auth_token, auth_data);
        return auth_data;
    }
}
