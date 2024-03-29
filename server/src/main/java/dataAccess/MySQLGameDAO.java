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
        if (gameName==null) {
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
    public GameData update(int id, String color, String user) {
        String updateGameDatabase;
        if (color.equals("BLACK")) {
            updateGameDatabase = "UPDATE games SET blackUsername=? WHERE gameID=?;";
        } else {
            updateGameDatabase = "UPDATE games SET whiteUsername=? WHERE gameID=?;";
        }
        try {
            executeUpdateStatement(updateGameDatabase, user, id);
            return getGame(id);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public HashSet<GameData> getGameList() {
        String selectTableDatabase = "SELECT gameID, whiteUsername, blackUsername, gameName FROM games;";
        try {
            return executeListStatement(selectTableDatabase);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GameData getGame(int id) {
        String selectAuthDatabase = "SELECT whiteUsername, blackUsername, gameName FROM games WHERE gameID = ?;";
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

    private void executeUpdateStatement(String sql, String user, int gameID) throws DataAccessException {
        try (PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setInt(2, gameID);

            if (stmt.executeUpdate() == 1) {
                return;
            }
        } catch (SQLException ex) {
            return;
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
    private HashSet<GameData> executeListStatement(String sql) throws DataAccessException {
        HashSet<GameData> gamesList = new HashSet<GameData>();
        try(PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gamesList.add(readList(rs));
                }
                return gamesList;
            }
        } catch(SQLException ex) {
            return null;
        }
    }
    private GameData readGame(ResultSet rs, int id) throws SQLException {
        String gameName = rs.getString("gameName");
        String black = rs.getString("blackUsername");
        String white = rs.getString("whiteUsername");
        return new GameData(id, white, black, null, gameName);
    }
    private GameData readList(ResultSet rs) throws SQLException {
        int id = rs.getInt("gameID");
        String gameName = rs.getString("gameName");
        String black = rs.getString("blackUsername");
        String white = rs.getString("whiteUsername");
        return new GameData(id, white, black, null, gameName);
    }
}
