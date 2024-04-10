package dataAccess.game;

import chess.ChessGame;
import dataAccess.DataAccessException;
import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id, ChessGame chessGame);
    GameData update(int id, String color, String user);
    void refresh(int id, ChessGame game);
    void remove(int id, String playerColor);


    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
