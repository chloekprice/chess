package clientTests;

import exception.ResponseException;
import org.junit.jupiter.api.*;
import server.Server;
import ui.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade facade;
    String user1 = "capn";
    String pass1 = "crunch";
    String email1 = "wberries";
    String user2 = "happy";
    String pass2 = "camper";
    String email2 = "van";
    String game1 = "butterFingers";
    String game2 = "mudPie";
    int expected;
    int actual;


    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        String serverUrl = "http://localhost:" + port;
        facade = new ServerFacade(serverUrl);

        System.out.println("Started test HTTP server on " + port);
    }

    @BeforeEach
    public void reset() throws ResponseException {
        facade.clear();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    public void posRegister() throws ResponseException {
        actual = facade.registerUser(user1, pass1, email1).getStatus();
        expected = 200;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negRegister() throws ResponseException {
        try {
            facade.registerUser(user1, pass1, email2);
            facade.registerUser(user1, pass1, email1);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }


    @Test
    public void posLogin() throws ResponseException {
        String authToken = facade.registerUser(user1, pass1, email1).getAuthData().getAuthToken();
        facade.logoutUser(authToken);
        actual = facade.loginUser(user1, pass1).getStatus();
        expected = 200;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negLogin() throws ResponseException {
        try {
            facade.loginUser(user1, pass1);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    public void posLogout() throws ResponseException {
        String authToken = facade.registerUser(user1, pass1, email1).getAuthData().getAuthToken();
        actual = facade.logoutUser(authToken).getStatus();
        expected = 200;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negLogout() throws ResponseException {
        try {
            facade.logoutUser("fakeAuthToken");
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }
    @Test
    public void posCreate() throws ResponseException {
        String authToken = facade.registerUser(user1, pass1, email1).getAuthData().getAuthToken();
        actual = facade.create(game1, authToken).getStatus();
        expected = 200;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void negCreate() throws ResponseException {
        try {
            facade.create(game1, "authToken");
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

}
