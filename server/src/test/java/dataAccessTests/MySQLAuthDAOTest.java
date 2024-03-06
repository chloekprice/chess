package dataAccessTests;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChessService;

import static org.junit.jupiter.api.Assertions.*;

class MySQLAuthDAOTest {
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
    void posInsertAuth() throws DataAccessException {
        expected = 200;
        actual = serviceTest.registerHandler(user, password, email).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void negInsertAuth() throws DataAccessException {
        expected = 403;
        serviceTest.registerHandler(user, password, email);
        actual = serviceTest.registerHandler(user, "password", "email@email.com").getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
    }

    @Test
    void getAuth() {
    }
}