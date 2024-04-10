package webSocketMessages.serverMessages;


import chess.ChessGame;

public class LoadGame extends ServerMessage {
    ChessGame game;
    int gameID;
    public LoadGame(ChessGame game, int gameID) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
        this.gameID = gameID;
    }
    public ChessGame getServerGame() {
        return this.game;
    }
    public ServerMessageType serverMessageType() {
        return this.getServerMessageType();
    }
    public int getID() {
        return this.gameID;
    }

}