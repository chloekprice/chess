package webSocketMessages.userCommands;

public class JoinPlayerGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    public JoinPlayerGameCommand(String authToken, String visitorName, String playerColor) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.visitorName = visitorName;
        this.playerColor = playerColor;

        this.message = visitorName + " has joined the game as " + playerColor;
    }
    public String getMessage() {
        return this.message;
    }
}
