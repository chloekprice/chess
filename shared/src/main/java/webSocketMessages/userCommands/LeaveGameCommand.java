package webSocketMessages.userCommands;

public class LeaveGameCommand extends  UserGameCommand{
    String message;
    int id;
    String visitorName;
    public LeaveGameCommand(String authToken, String visitorName, int gameID) {
        super(authToken);
        commandType = CommandType.LEAVE;
        id = gameID;
        this.visitorName = visitorName;

        this.message = this.visitorName + " has left the game";
    }
    public String getMessage() {
        return this.message;
    }
}
