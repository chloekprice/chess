package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String selectAuthDatabase = "SELECT email, password FROM users WHERE username = ?;";
        try {
            return executeSelectStatement(selectAuthDatabase, username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserData insertUser(String username, String password, String email) throws DataAccessException {
        UserData userData = new UserData(username, password, email);

        final String[] addUserDatabase = {
            "INSERT INTO users (username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "');"
        };

        executeStatement(addUserDatabase);

        return userData;
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

    private UserData executeSelectStatement(String sql, String username) throws DataAccessException {
        try(PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return readUser(rs, username);
                }
            }
        } catch(SQLException ex) {
            return null;
        }
        return null;
    }

    private UserData readUser(ResultSet rs, String username) throws SQLException {
        var email = rs.getString("email");
        var password = rs.getString("password");
        return new UserData(username, password, email);
    }
}
