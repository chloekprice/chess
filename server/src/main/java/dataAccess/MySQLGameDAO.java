package dataAccess;

import model.GameData;

import java.util.HashSet;

public class MySQLGameDAO implements GameDAO {
    public void clear() throws DataAccessException {

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
}
