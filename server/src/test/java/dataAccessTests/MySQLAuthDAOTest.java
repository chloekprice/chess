package dataAccessTests;

import dataAccess.DataAccessException;
import dataAccess.DatabaseManager;
import dataAccess.MySQLAuthDAO;
import dataAccess.MySQLUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ChessService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySQLAuthDAOTest {
    MySQLAuthDAO test;
    String user;
    String password;
    String email;
    String authToken;
    @BeforeEach
    void reset() throws DataAccessException {
        test = new MySQLAuthDAO();
        test.clear();
        configureDatabase();
        user = "urmom";
        password = "ilovemykids!";
        email = "stacysmom8675309@yahoo.com";
        authToken = "137893bleblahblo782";
    }
    @Test
    void clear() throws DataAccessException {
        test.insertAuth(user, authToken);
        test.clear();
        AuthData actual = test.getAuth(authToken);
        assertNull(actual);
    }

    @Test
    void posGetAuth() throws DataAccessException {
        test.insertAuth(user, authToken);
        AuthData actual = test.getAuth(authToken);
        assertEquals(new AuthData(user, authToken), actual);
    }

    @Test
    void negGetAuth() throws DataAccessException {
        AuthData actual = test.getAuth("butter");
        assertNull(actual);
    }

    @Test
    void posInsertAuth() throws DataAccessException {
        AuthData actual = test.insertAuth(user, authToken);
        assertEquals(new AuthData(user, authToken), actual);
    }

    @Test
    void negInsertAuth() throws DataAccessException {
        AuthData actual = test.insertAuth(null, authToken);
        assertNull(actual);    }

    @Test
    void posDeleteAuth() throws DataAccessException {
        test.insertAuth(user, authToken);
        test.delete(authToken);
        AuthData actual = test.getAuth(authToken);
        assertNull(actual);
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