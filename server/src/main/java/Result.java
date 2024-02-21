import dataAccess.dataModelClasses.authData;
import dataAccess.dataModelClasses.gameData;
import dataAccess.dataModelClasses.userData;

public class Result {
    private authData auth_data;
    private gameData game_data;
    private userData user_data;
    private String message;
    private int status;
    public Result() {
        this.auth_data = null;
        this.game_data = null;
        this.user_data = null;
        this.message = null;
        this.status = 200;
    }

    // setters
    public void setAuthData(authData auth_data) {
        this.auth_data = auth_data;
    }

    public void setGameData(gameData game_data) {
        this.game_data = game_data;
    }
    public void seUserData(userData user_data) {
        this.user_data = user_data;
    }

    public void serMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status_code) {
        this.status = status_code;
    }

    // getters
    public authData getAuth_data() {
        return auth_data;
    }

    public gameData getGame_data() {
        return game_data;
    }

    public userData getUser_data() {
        return user_data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
