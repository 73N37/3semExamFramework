package T3N3T.Factory.Exception;

public class EntityException extends T3N3T.Factory.Exception.ApiException {
    public EntityException(T3N3T.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }

    public EntityException(T3N3T.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super(errorType, msg, cause);
    }
}
