package webSocketMessages.userCommands;

public class ObserveGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    public ObserveGameCommand(String authToken, String visitorName) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.visitorName = visitorName;

        this.message = visitorName + " has joined the game as an observer";
    }
    public String getMessage() {
        return this.message;
    }
}
