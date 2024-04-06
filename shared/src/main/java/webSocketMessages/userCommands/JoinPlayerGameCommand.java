package webSocketMessages.userCommands;

public class JoinPlayerGameCommand extends  UserGameCommand{
    public JoinPlayerGameCommand(String authToken) {
        super(authToken);
        commandType = CommandType.JOIN_PLAYER;
    }
}
