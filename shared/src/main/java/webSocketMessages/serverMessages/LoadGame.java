package webSocketMessages.serverMessages;


import chess.ChessGame;

public class LoadGame extends ServerMessage {
    ChessGame game;
    public LoadGame(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
    }
    public ChessGame getServerGame() {
        return this.game;
    }
    public ServerMessageType serverMessageType() {
        return this.getServerMessageType();
    }
}