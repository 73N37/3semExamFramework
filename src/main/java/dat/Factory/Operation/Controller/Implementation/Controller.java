package dat.Factory.Operation.Controller.Implementation;

import dat.Factory.Factory;

public class Controller
    extends dat.Factory.Operation.Controller.Abstract
{
    protected static org.slf4j.Logger   log = org.slf4j.LoggerFactory.getLogger(Controller.class);

    public Controller()
    {
        super(getEntity(), getDto());
        log.debug("Instantiated dat.Factory.Operation.Controller.Implementation.Controller");
    }
}
