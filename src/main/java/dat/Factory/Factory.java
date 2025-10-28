package dat.Factory;

import dat.Factory.Information.dat.Factory.Information.DTO;

public class Factory
{
    private static org.slf4j.Logger                                                                     log = org.slf4j.LoggerFactory.getLogger(Factory.class);
    protected java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>                      entityClass;
    protected java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>                            dtoClass;
    protected java.lang.Class<? extends java.io.Serializable>                                           idClass;
    protected dat.Factory.Factory                                                                       instance;
    protected jakarta.persistence.EntityManagerFactory                                                  emf;
    protected dat.Factory.Operation.DAO.Implementation.DAO                                                        dao;
    protected dat.Factory.Operation.Controller.Implementation.Controller                                          controller;
    protected dat.Factory.Operation.Route.Implementation.Route                                                    route;

    public Factory getInstance(jakarta.persistence.EntityManagerFactory                                 _emf,
                               java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>     entityClass,
                               java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>           dtoClass,
                               java.lang.Class<? extends java.io.Serializable>                          idClass)
    {

        if ( instance == null )
        {
            log.debug("Creating a new instance of Factory");
            return new Factory(_emf, entityClass, dtoClass, idClass);
        }
        log.debug("Returning the existing Factory instance");
        return instance;
    }

    private Factory(jakarta.persistence.EntityManagerFactory                                            _emf,
                    java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>                entityClass,
                    java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>                      dtoClass,
                    java.lang.Class<? extends java.io.Serializable>                                     idClass)
    {
        log.debug("Assigning EntityMangerFactory to Factory");
        this.emf                = _emf;
        log.debug("Assigning entityClass to Factory");
        this.entityClass        = entityClass;
        log.debug("Assigning dtoClass to Factory");
        this.dtoClass           = dtoClass;
        log.debug("Assigning idClass to Factory");
        this.idClass            = idClass;
        log.debug("Creating and assigning an instance of DAO based on the 4 previously assigned Fields in Factory");
        this.dao                = new dat.Factory.Operation.DAO.Implementation.DAO.Access().getInstance(_emf, entityClass, dtoClass, idClass);
        log.debug("Creating and assigning an instance of Controller based on the DAO instance");
        this.controller         = new dat.Factory.Operation.Controller.Implementation.Controller(dao);
        log.debug("Creating and assigning an instance of Route based on the Controller instance");
        this.route              = new dat.Factory.Operation.Route.Implementation.Route(controller);
    }

//TODO===========================================[DAO methods]==========================================================
    protected dat.Factory.Operation.DAO.Implementation.DAO
    getDAO()
    {
        return this.dao;
    }

    protected void
    setDAO(dat.Factory.Operation.DAO.Implementation.DAO dao){this.dao = dao;}

//TODO===========================================[Controller methods]===================================================
    protected dat.Factory.Operation.Controller.Implementation.Controller
    getController()
    {
        return this.controller;
    }

    protected void
    setController(dat.Factory.Operation.Controller.Implementation.Controller controller){this.controller = controller;}

//TODO===========================================[Route methods]========================================================
    protected dat.Factory.Operation.Route.Implementation.Route
    getRoute()
    {
        return this.route;
    }

    protected void
    setRoute(dat.Factory.Operation.Route.Implementation.Route route) {this.route = route;}

//TODO===========================================[Class methods]==========================================================
    protected java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>
    getEntityClass()
    {
        return this.entityClass;
    }

    protected java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>
    getDtoClass()
    {
        return this.dtoClass;
    }

    protected java.lang.Class<? extends java.io.Serializable>
    getIdClass()
    {
        return this.idClass;
    }

    protected dat.Factory.Factory
    getFactory()
    {
        return this;
    }
}
