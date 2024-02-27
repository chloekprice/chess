package serviceTests;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ResultInfo;
import service.ChessService;


import static org.junit.jupiter.api.Assertions.assertEquals;

class chessServiceTest {
    ChessService serviceTest;
    @BeforeEach
    void reset() {
         serviceTest = new ChessService();
    }
    @Test
    void clearHandler() throws DataAccessException {
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        int actual;
        int expected = 401;

        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();
        int gameID = serviceTest.createGameHandler(authToken, "space jam").getGameID();

        serviceTest.clearHandler();
        // GOOD
        actual = serviceTest.joinGameHandler(authToken, "WHITE", gameID).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void negRegisterHandler() throws DataAccessException {
        String user = "urmom";
        String password = null;
        String email = "stacysmom8675309@yahoo.com";

        // BAD
        int actual = serviceTest.registerHandler(user, password, email).getStatus();
        int expected = 400;
        assertEquals(expected, actual);

    }

    @Test
    void posRegisterHandler() throws DataAccessException {
        String user = "urmom";
        String password = null;
        String email = "stacysmom8675309@yahoo.com";

        // GOOD
        password = "good";
        int actual = serviceTest.registerHandler(user, password, email).getStatus();
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    void negLoginHandler() throws DataAccessException {
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";

        // BAD
        int expected = 401;
        int actual = serviceTest.loginHandler(user, password).getStatus();

        assertEquals(expected, actual);

    }
    @Test
    void posLoginHandler() throws DataAccessException {
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";

        // GOOD
        int expected = 200;
        serviceTest.registerHandler(user, password, email);
        int actual = serviceTest.loginHandler(user, password).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void negLogoutHandler() throws DataAccessException {
        int expected;
        int actual;

        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // BAD
        expected = 401;
        actual = serviceTest.logoutHandler("abcdefg").getStatus();
        assertEquals(expected, actual);

    }

    @Test
    void posLogoutHandler() throws DataAccessException {
        int expected;
        int actual;

        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // GOOD
        expected = 200;
        actual = serviceTest.logoutHandler(authToken).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void negCreateGameHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // BAD
        int expected = 401;
        int actual = serviceTest.createGameHandler("beluga", "monopoly").getStatus();
        assertEquals(expected, actual);

    }

    @Test
    public void posCreateGameHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();


        // GOOD
        int actual = serviceTest.createGameHandler(authToken, "monopoly").getStatus();
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    public void negListGamesHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // GOOD- empty list
        int expected = 200;
        int actual = serviceTest.listGamesHandler(authToken).getStatus();
        assertEquals(expected, actual);

        // BAD- bad request
        serviceTest.createGameHandler(authToken, "monopoly");
        serviceTest.createGameHandler(authToken, "risk");
        expected = 401;
        actual = serviceTest.listGamesHandler("jimmy").getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void posListGamesHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // GOOD- empty list
        int expected = 200;
        int actual = serviceTest.listGamesHandler(authToken).getStatus();
        assertEquals(expected, actual);

    }
    @Test
    public void negJoinGameHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // BAD
        int expected = 401;
        int actual = serviceTest.joinGameHandler("jimmy", "BLACK", 5064).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void posJoinGameHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();


        // GOOD
        serviceTest.createGameHandler(authToken, "monopoly");
        ResultInfo test = serviceTest.createGameHandler(authToken, "risk");
        int gameID = test.getGameID();
        int expected = 200;
        int actual = serviceTest.joinGameHandler(authToken, "BLACK", gameID).getStatus();
        assertEquals(expected, actual);
    }

}