package dataAccess;

import java.util.HashMap;
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
}
