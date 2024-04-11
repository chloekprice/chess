package webSocketMessages.userCommands;

public class JoinPlayerGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    int gameID;
    public JoinPlayerGameCommand(String authToken, String visitorName, String playerColor, int gameID) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
        this.visitorName = visitorName;
        this.playerColor = playerColor;
        this.gameID = gameID;

        this.message = visitorName + " has joined the game as " + playerColor;
    }
    public String getMessage() {
        return this.message;
    }
    public String getName() {
        return this.visitorName;
    }
    public int getID() {
        return this.gameID;
    }
}
