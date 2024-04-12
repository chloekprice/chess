package webSocketMessages.serverMessages;

public class Error extends ServerMessage{
    String errorMessage;
    public Error(ServerMessageType type, String errorMessage) {
        super(ServerMessageType.ERROR);
        this.errorMessage = errorMessage;
    }
    public String getServerMessage() {
        return this.errorMessage;
    }
    public ServerMessageType serverMessageType() {
        return this.serverMessageType;
    }
}