package dataAccess.game;

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
    public GameData create(String gameName, int id, ChessGame chessGame) {
        Integer gameID = id;
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(id, null, null, newChessGame, gameName);
        games.put(gameID, newGame);
        return newGame;
    }

    @Override
    public GameData update(int id, String color, String user) {
        GameData updateGame = games.get(id);
        updateGame.updateGame(color, user);
        return updateGame;
    }

    @Override
    public void refresh(int id, ChessGame game) {
        String hey = "whats up";
    }
    @Override
    public void removePlayer(int id, String playerColor) {
        String hey = "whats up";
    }
    @Override
    public void removeGame(int id) {
        String hey = "whats up";
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
