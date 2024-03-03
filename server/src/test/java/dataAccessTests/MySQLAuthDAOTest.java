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
    @BeforeEach
    void reset()  {
        serviceTest = new ChessService();
    }
    @Test
    void clear() throws DataAccessException {
        expected = 200;
        actual = serviceTest.clearHandler().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    void insertAuth() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAuth() {
    }
}