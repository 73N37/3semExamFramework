package dat.Factory.Operation.Controller.Implementation;


import dat.Factory.Operation.Operation;

public abstract class Controller<   Entity  extends dat.Factory.Information.Entity.BaseEntity,
                                    DTO     extends dat.Factory.Information.DTO.BaseDTO,
                                    ID      extends java.io.Serializable>
    extends Operation
{
    protected final dat.Factory.Operation.DAO.Abstract dao;
    
    public Controller()
    {
        jakarta.persistence.EntityManagerFactory emf = dat.config.HibernateConfig.getEntityManagerFactory();
        this.dao = dat.Factory.Operation.DAO.Implementation.DAO.getInstance(emf, getEntityClass(), getDtoClass(), getIdClass());
    }
    

    public void get(io.javalin.http.Context ctx) {
        
    }


    public void getAll(io.javalin.http.Context ctx) {

    }


    public void put(io.javalin.http.Context ctx) {

    }


    public void patch(io.javalin.http.Context ctx) {

    }


    public boolean validatePrimaryKey(ID id) {
        return false;
    }


    public DTO validateEntity(io.javalin.http.Context ctx) {
        return null;
    }
}
