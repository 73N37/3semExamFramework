package T3N3T.Factory.Controller;

public class ErrorResponse {
    private int status;
    private String message;
    private String errorId;
    private String timestamp;

    public ErrorResponse(int status, String message, String errorId, String timestamp) {
        this.status = status;
        this.message = message;
        this.errorId = errorId;
        this.timestamp = timestamp;
    }

    // Getters (Javalin/Gson/Jackson will use these when serializing)
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public String getErrorId() { return errorId; }
    public String getTimestamp() { return timestamp; }
}