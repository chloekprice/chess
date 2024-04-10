package webSocketMessages.userCommands;

public class ResignGameCommand extends  UserGameCommand {
    String message;
    int id;
    String visitorName;
    String playerColor;
    String notPlayerColor = "BLACK";

    public ResignGameCommand(String authToken, String visitorName, int gameID, String playerColor) {
        super(authToken);
        commandType = CommandType.RESIGN;
        id = gameID;
        this.visitorName = visitorName;
        this.playerColor = playerColor;
        if (playerColor.equals("BLACK")) {
            notPlayerColor = "WHITE";
        }

        this.message = this.visitorName + " has resigned.\n" + notPlayerColor + " WINS THE GAME!!";
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
}