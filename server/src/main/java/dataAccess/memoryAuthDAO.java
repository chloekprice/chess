package dataAccess;
import java.util.Collection;
import java.util.HashMap;
import dataAccess.dataModelClasses.authData;
public class memoryAuthDAO implements authDAO{
    final private HashMap<String, authData> auth = new HashMap<String, authData>();
    // clear auth data
    public void clear() throws DataAccessException {
        try {auth.clear();} catch (Exception e) {throw new DataAccessException("cannot clear");}
    }
}
