package dataAccess;

import java.util.HashMap;
import dataAccess.dataModelClasses.gameData;

public class memoryGameDAO implements gameDAO{
    final private HashMap<String, gameData> games = new HashMap<String, gameData>();

    public void clear() throws DataAccessException {
        try {games.clear();} catch (Exception e) {throw new DataAccessException("cannot clear");}
    }
}
