package dat.Factory;


public class Factory
    extends dat.Factory.Operation.Operation
{
    @lombok.Getter
    @lombok.Setter
    protected static org.slf4j.Logger                                                                   log = org.slf4j.LoggerFactory.getLogger(Factory.class);

    @lombok.Getter
    @lombok.Setter
    protected dat.Factory.Factory                                                                       instance;

    @lombok.Getter
    @lombok.Setter
    protected dat.Factory.Operation.DAO.Abstract                                                        dao;

    @lombok.Getter
    @lombok.Setter
    protected dat.Factory.Operation.Controller.Abstract                                                 controller;

    @lombok.Getter
    @lombok.Setter
    protected static dat.Factory.Operation.Route.Abstract                                               route;

    public Factory getInstance
            (
                    @org.jetbrains.annotations.NotNull
                    java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>     entityClass,

                    @org.jetbrains.annotations.NotNull

                    java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>           dtoClass
            )
    {

        if ( instance == null )
        {
            log.debug("Creating a new instance of Factory");
            return new Factory(entityClass, dtoClass);
        }
        log.debug("Returning the existing Factory instance");
        return instance;
    }

    protected Factory() {}
    protected Factory
            (
                    @org.jetbrains.annotations.NotNull
                    java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>                entityClass,

                    @org.jetbrains.annotations.NotNull
                    java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>                      dtoClass
            )
    {
        super(entityClass, dtoClass);
        log.debug("Creating and assigning an instance of DAO based on the 4 previously assigned Fields in Factory");
        this.dao                = new dat.Factory.Operation.DAO.Implementation.DAO();
        log.debug("Creating and assigning an instance of Controller based on the DAO instance");
        this.controller         = new dat.Factory.Operation.Controller.Implementation.Controller();
        log.debug("Creating and assigning an instance of Route based on the Controller instance");
        this.route              = new dat.Factory.Operation.Route.Implementation.Route();
    }
}
