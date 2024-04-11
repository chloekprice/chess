package dataAccess.game;

import chess.ChessGame;
import dataAccess.DataAccessException;
import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id);
    GameData update(int id, String color, String user);
    void refresh(int id, ChessGame game);
    void removePlayer(int id, String playerColor);
    void removeGame(int id);


    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
