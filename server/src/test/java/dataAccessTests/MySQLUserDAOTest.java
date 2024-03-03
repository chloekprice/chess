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
    void getUser() {
    }

    @Test
    void insertUser() {
    }
}