package dataAccess;

import model.AuthData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MySQLAuthDAO implements AuthDAO {
    public void clear() throws DataAccessException {
        final String[] clearUserDatabase = {
            """
            DROP TABLE IF EXISTS auth
            """
        };

        executeClearStatement(clearUserDatabase);
    }

    @Override
    public AuthData insertAuth(String username, String authToken) {
        String insertAuthDatabase = "INSERT INTO auth (username, authToken) VALUES (?, ?);";
        try {
            return executeInsertStatement(insertAuthDatabase, username, authToken);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String authToken) {

    }

    @Override
    public AuthData getAuth(String authToken) {
        String selectAuthDatabase = "SELECT username FROM auth WHERE authToken = ?);";
        try {
            return executeSelectStatement(selectAuthDatabase, authToken);
        } catch (Exception e) {
            return null;
        }
    }

    private void executeClearStatement(String[] clearUserDatabase) throws DataAccessException {
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

    private AuthData executeInsertStatement(String sql, String username, String authToken) throws DataAccessException {
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, authToken);

            if (stmt.executeUpdate() == 1) {
                return new AuthData(username, authToken);
            } else {
                return new AuthData(username, authToken);
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    private AuthData executeSelectStatement(String sql, String authToken) throws DataAccessException {
        try(PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, authToken);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AuthData(readAuth(rs), authToken);
                }
            }
        } catch(SQLException ex) {
            return null;
        }
        return null;
    }

    private String readAuth(ResultSet rs) throws SQLException {
        return rs.getString("username");
    }
}
