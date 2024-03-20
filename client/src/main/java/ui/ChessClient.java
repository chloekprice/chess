package ui;

import exception.ResponseException;
import server.ResultInfo;


public class ChessClient {
    private String visitorName = null;
    private final ServerFacade server;
    private String authToken = null;
    private final String serverUrl;
    private StateOfSystem state;

    public ChessClient(String serverUrl) {
        this.server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
        this.state = StateOfSystem.SIGNEDOUT;
    }

    public void setState(StateOfSystem state) {
        this.state = state;
    }

    public StateOfSystem getState() {
        return this.state;
    }

    public String getVisitorName() {
        return visitorName;
    }


    public String register(String username, String password, String email) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.registerUser(username,password,email);
            if (result.getStatus() == 200) {
                this.visitorName = username;
                this.authToken = result.getAuthData().getAuthToken();
                setState(StateOfSystem.SIGNEDIN);
                return "logged in as " + username + "\n";
            } else {
                return (result.getStatus() + ": " + result.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), result.getMessage());
        }
    }
    public String signIn(String username, String password) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.loginUser(username, password);
            if (result.getStatus() == 200) {
                this.visitorName = username;
                this.authToken = result.getAuthData().getAuthToken();
                setState(StateOfSystem.SIGNEDIN);
                return "logged in as " + username;
            } else {
                return (result.getStatus() + ": " + result.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), result.getMessage());
        }
    }
    public String logout() throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.logoutUser(authToken);
            if (result.getStatus() == 200) {
                this.authToken = null;
                setState(StateOfSystem.SIGNEDOUT);
                String user = this.visitorName;
                this.visitorName = null;
                return "logged out " + user + "\n";
            } else {
                return (result.getStatus() + ": " + result.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), result.getMessage());
        }
    }
    public String createGame(String gameName) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.create(gameName, authToken);
            if (result.getStatus() == 200) {
                this.authToken = null;
                setState(StateOfSystem.SIGNEDOUT);
                String user = this.visitorName;
                this.visitorName = null;
                return "logged out " + user + "\n";
            } else {
                return (result.getStatus() + ": " + result.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(result.getStatus(), result.getMessage());
        }
    }

}