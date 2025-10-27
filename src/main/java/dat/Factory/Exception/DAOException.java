package dat.Factory.Exception;

public class DAOException extends dat.Factory.Exception.ApiException
{
    public DAOException(dat.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super( errorType, msg, cause);
    }
}
