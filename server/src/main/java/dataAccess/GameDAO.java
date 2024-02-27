package dataAccess;

import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id);
    GameData create(String gameName, int id, String whiteUsername, String blackUsername);
    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
