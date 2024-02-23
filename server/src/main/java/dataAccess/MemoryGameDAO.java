package dataAccess;

import java.util.HashMap;

import chess.ChessGame;
import dataAccess.dataModelClasses.GameData;

public class MemoryGameDAO implements GameDAO {
    final private HashMap<String, GameData> games;
    public MemoryGameDAO() {
        games = new HashMap<String, GameData>();
    }
    public void clear(){
        if (games != null) {
            games.clear();
        }
    }
    public GameData create(String gameName, int ID) {
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(ID, null, null, newChessGame);
        games.put(gameName, newGame);
        return newGame;
    }
    public GameData create(String gameName, int ID, String whiteUserName, String blackUserName) {
        ChessGame newChessGame = new ChessGame();
        GameData newGame = new GameData(ID, whiteUserName, blackUserName, newChessGame);
        games.put(gameName, newGame);
        return newGame;
    }
}
