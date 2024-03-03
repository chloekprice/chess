package dataAccessTests;

import dataAccess.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChessService;

import static org.junit.jupiter.api.Assertions.*;

class MySQLGameDAOTest {
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
    void create() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void getGameList() {
    }

    @Test
    void getGame() {
    }
}