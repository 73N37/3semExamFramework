package dat.Factory.Exception;

public enum ErrorType {
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized Access"),
    FORBIDDEN(403, "Forbidden Access"),
    NOT_FOUND(404, "Resource not found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    ALREADY_EXISTS(409, "Resource already exists"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    SERVER_ERROR(500, "Internal server error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    LOOP_DETECTED(508, "Loop Detected"),
    NOT_EXTENDED(509, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(510, "Network Authentication Required");

    private final int errorCode;
    private final String errorMessage;

    ErrorType(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMessage = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public static ErrorType getType(int errorCode) {
        for (ErrorType type : ErrorType.values()) {
            if (type.getErrorCode() == errorCode) {
                return type;
            }
        }
        return null;//  or throw an exception if no match found
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}