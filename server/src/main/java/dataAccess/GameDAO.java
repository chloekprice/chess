package dataAccess;

import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id);
    GameData update(int id, String color, String user);
    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
