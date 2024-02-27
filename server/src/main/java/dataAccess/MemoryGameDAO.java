package dataAccess;

import java.util.HashMap;
import java.util.HashSet;

import chess.ChessGame;
import model.GameData;

public class MemoryGameDAO implements GameDAO {
    final private HashMap<Integer, GameData> games;
    public MemoryGameDAO() {
        games = new HashMap<Integer, GameData>();
    }
    public void clear(){
        games.clear();
    }
    public GameData create(String gameName, int id) {
        Integer gameID = id;
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(id, null, null, newChessGame, gameName);
        games.put(gameID, newGame);
        return newGame;
    }
    public GameData create(String gameName, int id, String whiteUserName, String blackUserName) {
        Integer gameID = id;
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(id, whiteUserName, blackUserName, newChessGame, gameName);
        games.put(gameID, newGame);
        return newGame;
    }
    public HashSet<GameData> getGameList() {
        HashSet<GameData> gameList = new HashSet<GameData>();
        games.forEach((key, value)
                -> gameList.add(value));
        return gameList;
    }
    public GameData getGame(int id) {
        Integer gameID = id;
        return games.getOrDefault(gameID, null);
    }
}
