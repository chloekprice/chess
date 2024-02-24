package serviceTests;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.chessService;


import static org.junit.jupiter.api.Assertions.assertEquals;

class chessServiceTest {
    chessService serviceTest;
    @BeforeEach
    void reset() {
         serviceTest = new chessService();
    }
    @Test
    void clearHandler() {
    }

    @Test
    void registerHandler() throws DataAccessException {
        String user = "urmom";
        String password = null;
        String email = "stacysmom8675309@yahoo.com";

        // BAD
        int actual = serviceTest.registerHandler(user, password, email).getStatus();
        int expected = 400;
        assertEquals(expected, actual);

        // GOOD
        password = "good";
        actual = serviceTest.registerHandler(user, password, email).getStatus();
        expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    void loginHandler() throws DataAccessException {
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";

        // BAD
        int expected = 401;
        int actual = serviceTest.loginHandler(user, password).getStatus();

        assertEquals(expected, actual);

        // GOOD
        expected = 200;
        serviceTest.registerHandler(user, password, email);
        actual = serviceTest.loginHandler(user, password).getStatus();

        assertEquals(expected, actual);
    }

    @Test
    void logoutHandler() throws DataAccessException {
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

        // GOOD
        expected = 200;
        actual = serviceTest.logoutHandler(authToken).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void createGameHandler() throws DataAccessException{
        String user = "urmom";
        String password = "ilovemykids!";
        String email = "stacysmom8675309@yahoo.com";
        String authToken = serviceTest.registerHandler(user, password, email).getAuthData().getAuthToken();

        // BAD
        int expected = 401;
        int actual = serviceTest.createGameHandler("beluga", "monopoly").getStatus();
        assertEquals(expected, actual);

        // GOOD
        actual = serviceTest.createGameHandler(authToken, "monopoly").getStatus();
        expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    public void listGamesHandler() throws DataAccessException{
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
}