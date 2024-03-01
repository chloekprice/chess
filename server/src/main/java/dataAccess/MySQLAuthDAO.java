package dataAccess;

import model.AuthData;

public class MySQLAuthDAO implements AuthDAO {
    public void clear() {

    }

    @Override
    public AuthData insertAuth(String username, String auth_token) {
        return null;
    }

    @Override
    public void delete(String authToken) {

    }

    @Override
    public AuthData getAuth(String authToken) {
        return null;
    }
}
