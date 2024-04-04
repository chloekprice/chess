package model;

import chess.ChessBoard;
import chess.ChessGame;

import java.util.HashSet;

public class GameData {
    private final int gameID;
    private  String whiteUsername;
    private  String blackUsername;
    private final ChessGame game;
    private String gameName;

    public GameData(int gameID, String whiteUsername, String blackUsername, ChessGame game, String gameName) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        if (game == null) {
            ChessBoard newBoard = new ChessBoard();
            newBoard.resetBoard();
            this.game = new ChessGame();
        } else {
            this.game = game;
        }
    }
    // empty constructor
    public GameData() {
        this.gameID = 0;
        this.whiteUsername = "";
        this.blackUsername = "";
        ChessBoard newBoard = new ChessBoard();
        newBoard.resetBoard();
        this.game = new ChessGame();
        this.game.setBoard(newBoard);
    }

    public void updateGame(String color, String user) {
        if (color.equals("BLACK")) {
            this.blackUsername = user;
        } else if (color.equals("WHITE")) {
            this.whiteUsername = user;
        }
    }
    public int getGameID() {
        return this.gameID;
    }
    public String getWhiteUsername() {
        return this.whiteUsername;
    }
    public String getBlackUsername() {
        return this.blackUsername;
    }
    public String getName() {
        return this.gameName;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (o == this) { return true;}
        if (this.getClass() != o.getClass()) { return false;}
        GameData other = (GameData) o;
        if (other.whiteUsername == null && this.whiteUsername == null){
            if (this.blackUsername == null && other.blackUsername == null) {
                return (other.getGameID() == this.getGameID() && other.getName().equals(this.getName()));
            } else if (this.blackUsername != null && other.blackUsername != null) {
                return (other.getGameID() == this.getGameID() && other.getName().equals(this.getName()) && other.getBlackUsername().equals(this.blackUsername));
            }
        } else if (other.whiteUsername != null && this.whiteUsername != null && this.blackUsername != null && other.blackUsername != null) {
            return (other.getGameID() == this.getGameID() && other.getName().equals(this.getName()) && other.whiteUsername.equals(this.whiteUsername) && other.getBlackUsername().equals(this.blackUsername));
        } else if (other.whiteUsername != null && this.whiteUsername != null) {
            return (other.getGameID() == this.getGameID() && other.getName().equals(this.getName()) && other.whiteUsername.equals(this.whiteUsername) );
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.blackUsername != null && this.whiteUsername != null) {
            return (this.whiteUsername.hashCode() * this.blackUsername.hashCode() * this.gameName.hashCode() * gameID);
        } else if (this.blackUsername != null) {
            return (this.blackUsername.hashCode() * this.gameName.hashCode() * gameID);
        } else if (this.whiteUsername != null) {
            return (this.whiteUsername.hashCode()  * this.gameName.hashCode() * gameID);
        }
        return (this.gameName.hashCode() * gameID);
    }
}
