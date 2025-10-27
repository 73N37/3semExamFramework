package T3N3T.Factory.Controller;


import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.util.UUID;
import T3N3T.Factory.Exception.ApiException;

public class ExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    public void apiExceptionHandler(ApiException e, Context ctx) {
        String requestInfo = attributeOrDash(ctx, "requestInfo");
        int status = safeStatus(e.getStatusCode());
        String message = safeMessage(e.getMessage());
        String errorId = UUID.randomUUID().toString();
        // Log without full stacktrace (it's an expected, handled error)
        LOGGER.warn("{} {} {} - errorId={}", requestInfo, status, message, errorId);
        ctx.res().setContentType("application/json");
        ctx.status(status);
        ctx.json(new ErrorResponse(status, message, errorId, Instant.now().toString()));
    }

    public void exceptionHandler(Exception e, Context ctx) {
        String requestInfo = attributeOrDash(ctx, "requestInfo");
        int status = 500;
        String message = safeMessage(e.getMessage(), "Internal Server Error");
        String errorId = UUID.randomUUID().toString();
        // Unexpected errors: include stacktrace in logs for debugging
        LOGGER.error("{} {} {} - errorId={}", requestInfo, status, message, errorId, e);
        ctx.res().setContentType("application/json");
        ctx.status(status);
        ctx.json(new ErrorResponse(status, message, errorId, Instant.now().toString()));
    }

    private String attributeOrDash(Context ctx, String name) {
        Object attr = ctx.attribute(name);
        return attr != null ? attr.toString() : "-";
    }

    private int safeStatus(int code) {
        return (code >= 100 && code < 600) ? code : 500;
    }

    private String safeMessage(String msg) {
        return safeMessage(msg, "Unexpected error");
    }

    private String safeMessage(String msg, String fallback) {
        return (msg != null && !msg.trim().isEmpty()) ? msg : fallback;
    }
}