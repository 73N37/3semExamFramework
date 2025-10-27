package T3N3T.Factory.Exception;

public class DTOException extends T3N3T.Factory.Exception.ApiException
{
    public DTOException(T3N3T.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }

    public DTOException(T3N3T.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super(errorType, msg, cause);
    }
}
