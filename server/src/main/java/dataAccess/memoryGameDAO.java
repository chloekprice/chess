package dataAccess;

import java.util.HashMap;
import dataAccess.dataModelClasses.gameData;

public class memoryGameDAO implements gameDAO{
    final private HashMap<String, gameData> games;
    public memoryGameDAO() {
        games = new HashMap<String, gameData>();
    }
    public void clear(){
        if (games != null) {
            games.clear();
        }
    }
}
