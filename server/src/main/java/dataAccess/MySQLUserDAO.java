package dataAccess;

import model.UserData;

public class MySQLUserDAO implements UserDAO {
    public void clear() throws DataAccessException {

    }

    @Override
    public UserData getUser(String username) {
        return null;
    }

    @Override
    public UserData insertUser(String username, String password, String email) {
        return null;
    }
}
