package dat.Factory.Exception;

public class RouteException extends dat.Factory.Exception.ApiException
{
    public RouteException(dat.Factory.Exception.ErrorType errorType, String msg, Throwable cause) {
        super(errorType, msg, cause);
    }

    public RouteException(dat.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }
}
