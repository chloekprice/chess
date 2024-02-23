package server.service;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passoffTests.obfuscatedTestClasses.TestServerFacade;
import server.Server;

import static org.junit.jupiter.api.Assertions.*;

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
}