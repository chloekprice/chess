package dataAccess;

import model.AuthData;
import model.GameData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class MySQLGameDAO implements GameDAO {
    public void clear() throws DataAccessException {
        final String[] clearUserDatabase = {
            """
            DROP TABLE IF EXISTS games
            """
        };

        executeStatement(clearUserDatabase);
    }

    @Override
    public GameData create(String gameName, int id) {
        if (gameName==null || id ==0) {
            return null;
        }

        String insertAuthDatabase = "INSERT INTO games (gameName, gameID) VALUES (?, ?);";
        try {
            return executeInsertStatement(insertAuthDatabase, gameName, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GameData create(String gameName, int id, String whiteUsername, String blackUsername) {
        if (gameName==null || id ==0) {
            return null;
        }

        String insertAuthDatabase = "INSERT INTO games (gameName, whiteUsername, blackUsername, gameID) VALUES (?, ?, ?, ?);";
        try {
            return executeFullInsertStatement(insertAuthDatabase, gameName, whiteUsername, blackUsername, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HashSet<GameData> getGameList() {
        return null;
    }

    @Override
    public GameData getGame(int id) {
        String selectAuthDatabase = "SELECT (whiteUsername, blackUsername, gameName) FROM games WHERE gameID = ?;";
        try {
            return executeSelectStatement(selectAuthDatabase, id);
        } catch (Exception e) {
            return null;
        }
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

    private GameData executeInsertStatement(String sql, String gameName, int gameID) throws DataAccessException {
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, gameName);
            stmt.setInt(2, gameID);

            if (stmt.executeUpdate() == 1) {
                return new GameData(gameID, null, null, null, gameName);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }
    private GameData executeFullInsertStatement(String sql, String gameName, String whiteUser, String blackUser, int gameID) throws DataAccessException {
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, gameName);
            stmt.setString(2, whiteUser);
            stmt.setString(3, blackUser);
            stmt.setInt(4, gameID);

            if (stmt.executeUpdate() == 1) {
                return new GameData(gameID, whiteUser, blackUser, null, gameName);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }

    }
    private GameData executeSelectStatement(String sql, int gameID) throws DataAccessException {
        try(PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, gameID);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return readGame(rs, gameID);
                }
            }
        } catch(SQLException ex) {
            return null;
        }
        return null;
    }
    private GameData readGame(ResultSet rs, int id) throws SQLException {
        if (true) {
            String white = rs.getString("whiteUsername");
            String black = rs.getString("blackUsername");
            String gameName = rs.getString("gameName");
            return new GameData(id, white, black, null, gameName);
        } else {
            String gameName = rs.getString("gameName");
            return new GameData(id, null, null, null, gameName);
        }
    }
}
