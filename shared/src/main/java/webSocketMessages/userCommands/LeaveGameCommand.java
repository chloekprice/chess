package webSocketMessages.userCommands;

public class LeaveGameCommand extends  UserGameCommand{
    String message;
    int id;
    String visitorName;
    String playerColor;
    public LeaveGameCommand(String authToken, String visitorName, int gameID, String playerColor) {
        super(authToken);
        commandType = CommandType.LEAVE;
        id = gameID;
        this.visitorName = visitorName;
        this.playerColor = playerColor;

        this.message = this.visitorName + " has left the game";
    }
    public String getMessage() {
        return this.message;
    }
    public String getPlayerColor() {
        return this.playerColor;
    }
    public int getId() {
        return this.id;
    }
    public String getVisitorName() {
        return this.visitorName;
    }
}
