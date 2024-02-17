package dataAccess;

import java.util.HashMap;
import dataAccess.dataModelClasses.userData;

public abstract class memoryUserDAO implements DAO{

    final private HashMap<String, userData> users = new HashMap<String, userData>();

    public void clear() throws DataAccessException {
        try {users.clear();} catch (Exception e) {throw new DataAccessException("cannot clear");}
    }
    public userData getUser(String username) {
        return users.getOrDefault(username, null);
    }

}
