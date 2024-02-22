package dataAccess;

import java.util.HashMap;
import dataAccess.dataModelClasses.gameData;

public class MemoryGameDAO implements GameDAO {
    final private HashMap<String, gameData> games;
    public MemoryGameDAO() {
        games = new HashMap<String, gameData>();
    }
    public void clear(){
        if (games != null) {
            games.clear();
        }
    }
}
