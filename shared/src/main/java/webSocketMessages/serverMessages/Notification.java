package webSocketMessages.serverMessages;

public class Notification extends ServerMessage{
    String message;
    public Notification(ServerMessageType type, String message) {
        super(ServerMessageType.NOTIFICATION);
        this.message = message;
    }
    public String getServerMessage() {
        return this.message;
    }
}
