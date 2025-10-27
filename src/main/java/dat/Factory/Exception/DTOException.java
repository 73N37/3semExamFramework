package dat.Factory.Exception;

public class DTOException extends dat.Factory.Exception.ApiException
{
    public DTOException(dat.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }

    public DTOException(dat.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super(errorType, msg, cause);
    }
}
