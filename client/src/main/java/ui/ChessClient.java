package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import exception.ResponseException;
import model.GameData;
import server.ResultInfo;
import ui.display.ChessBoardPrinter;
import ui.websockets.WebSocketFacade;


public class ChessClient {
    private String visitorName = null;
    private final ServerFacade server;
    private String authToken = null;
    private final String serverUrl;
    private StateOfSystem state;
    private ResultInfo data = new ResultInfo();
    private WebSocketFacade ws;
    private ChessGame game;
    private ChessGame.TeamColor color;

    public ChessClient(String serverUrl) {
        this.server = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
        this.game = null;
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

    public ResultInfo getData() {
        return this.data;
    }

    public String register(String username, String password, String email) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.registerUser(username, password, email);
            this.data = result;
            if (result.getStatus() == 200) {
                this.visitorName = username;
                this.authToken = result.getAuthData().getAuthToken();
                setState(StateOfSystem.SIGNEDIN);
                ws = new WebSocketFacade(serverUrl, this);
                return "logged in as " + username + "\n";
            } else {
                return (data.getStatus() + ": " + data.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String signIn(String username, String password) throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.loginUser(username, password);
            this.data = result;
            if (result.getStatus() == 200) {
                this.visitorName = username;
                this.authToken = result.getAuthData().getAuthToken();
                setState(StateOfSystem.SIGNEDIN);
                ws = new WebSocketFacade(serverUrl, this);
                return "logged in as " + username;
            } else {
                return (data.getStatus() + ": " + data.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String logout() throws ResponseException {
        ResultInfo result = new ResultInfo();
        try {
            result = server.logoutUser(authToken);
            this.data = result;
            if (result.getStatus() == 200) {
                this.authToken = null;
                setState(StateOfSystem.SIGNEDOUT);
                String user = this.visitorName;
                this.visitorName = null;
                return "logged out " + user + "\n";
            } else {
                return (data.getStatus() + ": " + data.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String createGame(String gameName) throws ResponseException {
        ResultInfo result;
        try {
            result = server.create(gameName, authToken);
            this.data = result;
            if (result.getStatus() == 200) {
                return "the chess game, " + result.getGameName() + ", has been created\n";
            } else {
                return (data.getStatus() + ": " + data.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String listGames() throws ResponseException {
        ResultInfo result;
        try {
            result = server.list(authToken);
            this.data = result;
            if (result.getStatus() == 200 && result.getGamesList() != null) {

                StringBuilder games = new StringBuilder("\n");
                int num = 1;

                for (GameData game : result.getGamesList()) {
                    games.append(num);
                    games.append(". ");
                    games.append(game.getGameID()).append(": ").append(game.getName()).append("-- ");
                    games.append("WHITE USER: ").append(game.getWhiteUsername()).append(" ");
                    games.append("BLACK USER: ").append(game.getBlackUsername()).append(" ");
                    games.append("\n");

                    num += 1;
                }
                return String.valueOf(games);
            } else {
                throw new ResponseException(500, "no games have been created, yet");
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String joinGame(int gameID, String playerColor) throws ResponseException {
        ResultInfo result;
        try {
            if (playerColor == null) {
                this.color = null;
                ws.joinChessGame(authToken, color, gameID);
                return observeGame(gameID);
            }
//            result = server.join(gameID, playerColor, authToken);
//            this.data = result;
//            if (result.getStatus() == 200) {
//                game = data.getGame();
//                if (playerColor.equals("WHITE")) {
//                    this.color = ChessGame.TeamColor.WHITE;
//                } else {
//                    this.color = ChessGame.TeamColor.BLACK;
//                }
//                ws.joinChessGame(authToken, visitorName, color, gameID, game);
//                return "joining " + result.getGameName() + "...\n";
//            } else {
//                return (data.getStatus() + ": " + data.getMessage());
//            }
            if (playerColor.equals("WHITE")) {
                this.color = ChessGame.TeamColor.WHITE;
            } else {
                this.color = ChessGame.TeamColor.BLACK;
            }
            ws.joinChessGame(authToken, color, gameID);
            return "joining " + data.getGameName() + "...\n";
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }

    public String observeGame(int gameID) throws ResponseException {
        ResultInfo result;
        try {
            this.color = null;
            result = server.join(gameID, null, authToken);
            this.data = result;
            if (result.getStatus() == 200) {
                game = data.getGame();
                ws.observeChessGame(authToken, gameID);
                return "now observing " + result.getGameName() + "...\n";
            } else {
                return (data.getStatus() + ": " + data.getMessage());
            }
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }
    public void makeMove(int col, int row, int newCol, int newRow, int gameID) throws ResponseException {
        try {
            ChessPosition startPosition = new ChessPosition(row, col);
            ChessPosition endPosition = new ChessPosition(newRow, newCol);
            ChessMove move = new ChessMove(startPosition, endPosition);
            ws.sendUpdatedGame(authToken, visitorName, gameID, move);
        } catch (Exception e) {
            throw new ResponseException(500, e.getMessage());
        }
    }
    public void leaveGame() {
        try {
            ws.leaveChessGame(authToken, visitorName, data.getGameID(), this.color);
        } catch (Exception e) {
            System.out.println("Error: cannot leave game.\n YOU ARE STUCK HERE FOR EVERRRRR");
        }
    }
    public void resign() {
        try {
            if (color != null) {
                ws.resignChessGame(authToken, visitorName, data.getGameID(), this.color);
            }
        } catch (Exception e) {
            System.out.println("Error: cannot leave game.\n YOU ARE STUCK HERE FOR EVERRRRR");
        }
    }

    public void redraw() throws ResponseException {
        ChessBoardPrinter printer = new ChessBoardPrinter();
        if (color == ChessGame.TeamColor.BLACK) {
            printer.print(game.getBoard(), color);
        } else {
            printer.print(game.getBoard(), ChessGame.TeamColor.WHITE);
        }
    }

    public void updateGame(ChessGame game) {
        this.game = game;
        data.updateGame(game);
    }
    public ChessGame.TeamColor getColor() {
        return this.color;
    }
}