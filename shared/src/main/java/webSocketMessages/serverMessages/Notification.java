package webSocketMessages.serverMessages;

public class Notification extends ServerMessage{
    String message;
    public Notification(String message) {
        super(ServerMessageType.NOTIFICATION);
        this.message = message;
    }
    public String getServerMessage() {
        return this.message;
    }
    public ServerMessageType serverMessageType() {
        return this.serverMessageType;
    }
}
