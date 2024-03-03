package dataAccess;

import model.UserData;

import java.sql.SQLException;

public class MySQLUserDAO implements UserDAO {
    public void clear() throws DataAccessException {

        final String[] clearUserDatabase = {
            """
            DROP TABLE IF EXISTS users
            """
        };

        executeStatement(clearUserDatabase);
    }

    @Override
    public UserData getUser(String username) {
        return null;
    }

    @Override
    public UserData insertUser(String username, String password, String email) {
        return null;
    }

    private static void executeStatement(String[] clearUserDatabase) throws DataAccessException {
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
