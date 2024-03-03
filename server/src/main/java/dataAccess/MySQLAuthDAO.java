package dataAccess;

import model.AuthData;

import java.sql.SQLException;

public class MySQLAuthDAO implements AuthDAO {
    public void clear() throws DataAccessException {
        final String[] clearUserDatabase = {
            """
            DROP TABLE IF EXISTS auth
            """
        };

        executeStatement(clearUserDatabase);
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

    private void executeStatement(String[] clearUserDatabase) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : clearUserDatabase) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Unable to clear user: %s", ex.getMessage()));
        }
    }
}
