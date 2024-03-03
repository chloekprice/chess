package dataAccess;

import model.GameData;

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
        return null;
    }

    @Override
    public GameData create(String gameName, int id, String whiteUsername, String blackUsername) {
        return null;
    }

    @Override
    public HashSet<GameData> getGameList() {
        return null;
    }

    @Override
    public GameData getGame(int id) {
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
