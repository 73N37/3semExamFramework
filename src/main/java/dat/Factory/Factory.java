package T3N3T.Factory;

import T3N3T.Factory.Information.DTO.ChildDTO;
import T3N3T.Factory.Information.Entity.GrandParentEntity;

public class Factory
{
    private static org.slf4j.Logger                                                         log = org.slf4j.LoggerFactory.getLogger(Factory.class);
    protected java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>        entityClass;
    protected java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>              dtoClass;
    protected java.lang.Class<? extends java.io.Serializable>                               idClass;
    protected T3N3T.Factory.Factory                                                         instance;
    protected jakarta.persistence.EntityManagerFactory                                      emf;
    protected T3N3T.Factory.DAO.Implementation.DAO                                          dao;
    protected T3N3T.Factory.Controller.Implementation.Controller                            controller;
    protected T3N3T.Factory.Route.Implementation.Route                                      route;

    public Factory getInstance(jakarta.persistence.EntityManagerFactory                                _emf,
                               java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>  entityClass,
                               java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>        dtoClass,
                               java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        if ( instance == null )
        {
            return new Factory(_emf, entityClass, dtoClass, idClass);
        }
        return instance;
    }

    private Factory(jakarta.persistence.EntityManagerFactory                                _emf,
                    java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>  entityClass,
                    java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>        dtoClass,
                    java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        this.emf                = _emf;
        this.entityClass        = entityClass;
        this.dtoClass           = dtoClass;
        this.idClass            = idClass;
        this.dao                = new T3N3T.Factory.DAO.Implementation.DAO.Access().getInstance(_emf, entityClass, dtoClass, idClass);
        this.controller         = new T3N3T.Factory.Controller.Implementation.Controller(dao);
        this.route              = new T3N3T.Factory.Route.Implementation.Route(controller);
    }

//TODO===========================================[DAO methods]==========================================================
    protected T3N3T.Factory.DAO.Implementation.DAO
    getDAO()
    {
        return this.dao;
    }

    protected void
    setDAO(T3N3T.Factory.DAO.Implementation.DAO dao){this.dao = dao;}

//TODO===========================================[Controller methods]===================================================
    protected T3N3T.Factory.Controller.Implementation.Controller
    getController()
    {
        return this.controller;
    }

    protected void
    setController(T3N3T.Factory.Controller.Implementation.Controller controller){this.controller = controller;}

//TODO===========================================[Route methods]========================================================
    protected T3N3T.Factory.Route.Implementation.Route
    getRoute()
    {
        return this.route;
    }

    protected void
    setRoute(T3N3T.Factory.Route.Implementation.Route route) {this.route = route;}

//TODO===========================================[Class methods]==========================================================
    protected java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>
    getEntityClass()
    {
        return this.entityClass;
    }

    protected java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>
    getDtoClass()
    {
        return this.dtoClass;
    }

    protected java.lang.Class<? extends java.io.Serializable>
    getIdClass()
    {
        return this.idClass;
    }
}
