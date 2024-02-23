package dataAccess;

import dataAccess.dataModelClasses.AuthData;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {
    HashMap<String, AuthData> auth;
    public MemoryAuthDAO() {
        HashMap<String, AuthData> auth = new HashMap<String, AuthData>();
    }
    public void clear() {
        if (auth != null) {
            auth.clear();
        }
    }
    public AuthData insertAuth(String username, String auth_token) {
        AuthData auth_data = new AuthData(username, auth_token);
        if (auth == null) {
            auth = new HashMap<String, AuthData>();
        }
        auth.put(auth_token, auth_data);
        return auth_data;
    }
    public AuthData getAuth(String authToken) {
        return auth.getOrDefault(authToken, null);
    }
    public void delete(String authToken) {
        auth.remove(authToken);
    }
}
