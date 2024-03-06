package dataAccessTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChessService;
import dataAccess.DataAccessException;
import server.ResultInfo;
import static org.junit.jupiter.api.Assertions.*;

class MySQLUserDAOTest {
    ChessService serviceTest;
    int actual;
    int expected;
    String user;
    String password;
    String email;
    @BeforeEach
    void reset()  {
        serviceTest = new ChessService();
        user = "urmom";
        password = "ilovemykids!";
        email = "stacysmom8675309@yahoo.com";
    }
    @Test
    void clear() throws DataAccessException {
        expected = 200;
        actual = serviceTest.clearHandler().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void getUser() {
    }

    @Test
    void posInsertUser() throws DataAccessException {
        expected = 200;
        actual = serviceTest.registerHandler(user, password, email).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void negInsertUser() throws DataAccessException {
        expected = 403;
        serviceTest.registerHandler(user, password, email);
        actual = serviceTest.registerHandler(user, "password", "email@email.com").getStatus();
        assertEquals(expected, actual);
    }
}