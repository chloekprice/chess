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
        if (games != null) {
            games.clear();
        }
    }
    public GameData create(String gameName, int ID) {
        Integer gameID = ID;
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(ID, null, null, newChessGame, gameName);
        games.put(gameID, newGame);
        return newGame;
    }
    public GameData create(String gameName, int ID, String whiteUserName, String blackUserName) {
        Integer gameID = ID;
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(ID, whiteUserName, blackUserName, newChessGame, gameName);
        games.put(gameID, newGame);
        return newGame;
    }
    public HashSet<GameData> getGameList() {
        HashSet<GameData> gameList = new HashSet<GameData>();
        games.forEach((key, value)
                -> gameList.add(value));
        return gameList;
    }
    public GameData getGame(int ID) {
        Integer gameID = ID;
        return games.getOrDefault(gameID, null);
    }
}
