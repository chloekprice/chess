package webSocketMessages.userCommands;

public class ObserveGameCommand extends  UserGameCommand{
    String visitorName;
    String message;
    String playerColor;
    int gameID;
    public ObserveGameCommand(String authToken, String visitorName, int gameID) {
        super(authToken);
        commandType = CommandType.JOIN_OBSERVER;
        this.visitorName = visitorName;
        this.gameID = gameID;

        this.message = visitorName + " has joined the game as an observer";
    }
    public String getMessage() {
        return this.message;
    }
    public int getID() {
        return this.gameID;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
}
