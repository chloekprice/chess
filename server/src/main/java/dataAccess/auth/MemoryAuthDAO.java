package dataAccess.auth;

import dataAccess.auth.AuthDAO;
import model.AuthData;

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
    public AuthData insertAuth(String username, String authToken) {
        AuthData authData = new AuthData(username, authToken);
        if (auth == null) {
            auth = new HashMap<String, AuthData>();
        }
        auth.put(authToken, authData);
        return authData;
    }
    public AuthData getAuth(String authToken) {
        return auth.getOrDefault(authToken, null);
    }
    public void delete(String authToken) {
        auth.remove(authToken);
    }
}
