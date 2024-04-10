package dataAccess.game;

import chess.ChessGame;
import dataAccess.DataAccessException;
import model.GameData;

import java.util.HashSet;

public interface GameDAO {
    void clear() throws DataAccessException;
    GameData create(String gameName, int id, ChessGame chessGame);
    GameData update(int id, String color, String user);

    GameData refresh(int id, ChessGame game);

    HashSet<GameData> getGameList();
    GameData getGame(int id);
}
