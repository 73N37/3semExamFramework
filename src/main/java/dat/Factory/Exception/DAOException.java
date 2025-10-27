package T3N3T.Factory.Exception;

public class DAOException extends T3N3T.Factory.Exception.ApiException
{
    public DAOException(T3N3T.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super( errorType, msg, cause);
    }
}
