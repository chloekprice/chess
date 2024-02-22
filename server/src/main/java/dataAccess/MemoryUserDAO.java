package dataAccess;

import java.util.HashMap;
import dataAccess.dataModelClasses.userData;

public class MemoryUserDAO implements UserDAO {

    final private HashMap<String, userData> users;
    public MemoryUserDAO() {
        users = new HashMap<String, userData>();
    }

    public void clear() {
        if (users != null) {
            users.clear();
        }
    }
    public userData getUser(String username) {
        return users.getOrDefault(username, null);
    }

    public void insertUser(String username, String password, String email) {
        userData user_data = new userData(username, password, email);
        users.put(username, user_data);
    }

}
