package dataAccess;
import java.util.Collection;
import java.util.HashMap;
import dataAccess.dataModelClasses.authData;
public abstract class memoryAuthDAO implements authDAO{
    final private HashMap<String, authData> auth = new HashMap<String, authData>();
    // clear auth data
    public void clear() throws DataAccessException {
        try {auth.clear();} catch (Exception e) {throw new DataAccessException("cannot clear");}
    }
    // insert new auth data
    public String insertAuth(String username, String auth_token) {
        authData auth_data = new authData(username, auth_token);
        auth.put(username, auth_data);
        return auth_data.getAuthToken();
    }
}
