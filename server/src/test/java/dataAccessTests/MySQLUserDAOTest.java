package dataAccessTests;

import dataAccess.DatabaseManager;
import dataAccess.MySQLUserDAO;
import model.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataAccess.DataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MySQLUserDAOTest {
    MySQLUserDAO test;
    String user;
    String password;
    String email;
    @BeforeEach
    void reset() throws DataAccessException {
        test = new MySQLUserDAO();
        test.clear();
        configureDatabase();
        user = "urmom";
        password = "ilovemykids!";
        email = "stacysmom8675309@yahoo.com";
    }
    @Test
    void clear() throws DataAccessException {
        test.insertUser(user, password, email);
        test.clear();
        UserData actual = test.getUser(user);
        UserData expected = null;
        assertEquals(expected, actual);
    }

    @Test
    void posGetUser() throws DataAccessException {
        test.insertUser(user, password, email);
        UserData actual = test.getUser(user);
        assertEquals(new UserData(user, password, email), actual);
    }

    @Test
    void posInsertUser() throws DataAccessException {

    }

    @Test
    void negInsertUser() throws DataAccessException {

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