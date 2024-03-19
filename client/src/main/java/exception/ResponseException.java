package exception;

public class ResponseException extends Exception {
    final private int statusCode;
    final private String message;

    public ResponseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int StatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}