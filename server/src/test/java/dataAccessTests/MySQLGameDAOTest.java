package dataAccessTests;

import dataAccess.DataAccessException;
import dataAccess.DatabaseManager;
import dataAccess.MySQLAuthDAO;
import dataAccess.MySQLGameDAO;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChessService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySQLGameDAOTest {
    MySQLGameDAO test;
    String userWhite;
    String userBlack;
    String gameName;
    int gameID;
    String authToken;
    @BeforeEach
    void reset() throws DataAccessException {
        test = new MySQLGameDAO();
        test.clear();
        configureDatabase();
        userWhite = "urmom";
        userBlack = "darkknight";
        gameName = "epicbattle";
        gameID = 1097;
    }
    @Test
    void clear() throws DataAccessException {
        test.create(gameName, gameID);
        test.clear();
        GameData actual = test.getGame(gameID);
        assertNull(actual);
    }

    @Test
    void posCreate() {
        GameData actual = test.create(gameName, gameID);
        assertEquals(new GameData(gameID, null, null, null, gameName), actual);
    }

    @Test
    void negCreate() {
        GameData actual = test.create(null, gameID, userWhite, userBlack);
        assertNull(actual);
    }


    @Test
    void posGetGameList() {
    }
    @Test
    void negGetGameList() {
    }

    @Test
    void posGetGame() {
        test.create(gameName, gameID);
        GameData actual = test.getGame(gameID);
        assertEquals(new GameData(gameID, null, null, null, gameName), actual);
    }
    @Test
    void negGetGame() {
    }


    private void configureDatabase() throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : instantiateTables) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Unable to configure database: %s", ex.getMessage()));
        }
    }
    private final String[] instantiateTables = {
            """
            CREATE TABLE IF NOT EXISTS  users (
              `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
              `username` varchar(256) NOT NULL,
              `email` varchar(256) NOT NULL,
              `password` varchar(256) NOT NULL,
              INDEX(username),
              INDEX(password),
              INDEX(email)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """,
            """
            CREATE TABLE IF NOT EXISTS auth (
                `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                `username` varchar(256) NOT NULL,
                `authToken` varchar(256) NOT NULL,
                INDEX(username),
                INDEX(authToken)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """,
            """
            CREATE TABLE IF NOT EXISTS games (
                `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                `gameID` int not null,
                `whiteUsername` varchar(256),
                `blackUsername` varchar(246),
                `gameName` varchar(256) not null,
                INDEX(gameID),
                INDEX(whiteUsername),
                INDEX(blackUsername),
                INDEX(gameName)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };
}