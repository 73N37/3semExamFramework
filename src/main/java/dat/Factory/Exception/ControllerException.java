package dat.Factory.Exception;

public class ControllerException extends dat.Factory.Exception.ApiException
{
    public ControllerException(dat.Factory.Exception.ErrorType errorType, String msg, Throwable cause)
    {
        super(errorType, msg, cause);
    }

    public ControllerException(dat.Factory.Exception.ErrorType errorType, String msg)
    {
        super(errorType, msg);
    }
}
