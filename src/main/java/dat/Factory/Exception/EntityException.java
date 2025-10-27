package dat.Factory.Exception;

public class EntityException extends dat.Factory.Exception.ApiException {
    public EntityException(dat.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }

    public EntityException(dat.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super(errorType, msg, cause);
    }
}
