package dataAccess;

import java.util.HashMap;
import model.UserData;

public class MemoryUserDAO implements UserDAO {

    final private HashMap<String, UserData> users;
    public MemoryUserDAO() {
        users = new HashMap<String, UserData>();
    }

    public void clear() {
        users.clear();
    }
    public UserData getUser(String username) {
        return users.getOrDefault(username, null);
    }

    public UserData insertUser(String username, String password, String email) {
        UserData userData = new UserData(username, password, email);
        users.put(username, userData);
        return userData;
    }

}
