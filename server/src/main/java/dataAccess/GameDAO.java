package dataAccess;

import chess.ChessGame;
import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id, ChessGame chessGame);
    GameData update(int id, String color, String user);
    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
