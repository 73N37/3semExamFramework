package dat.Factory.DAO;


public abstract class Abstract
{
    protected final static org.slf4j.Logger                                                       log = org.slf4j.LoggerFactory.getLogger(Abstract.class);
    protected static jakarta.persistence.EntityManagerFactory                                     emf;
    protected static java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>         entityClass;
    protected static java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>               dtoClass;
    protected static java.lang.Class<? extends java.io.Serializable>                              idClass;

    protected Abstract( jakarta.persistence.EntityManagerFactory                                _emf,
                        java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>    entityClass,
                        java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>          dtoClass,
                        java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        emf                 =   _emf;
        this.entityClass    =   entityClass;
        this.dtoClass       =   dtoClass;
        this.idClass        =   idClass;
    }


    private static java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>
    getEntityClass() {
        return entityClass;
    }

    private static java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>
    getDtoClass(){
        return dtoClass;
    }

    private static java.lang.Class<? extends java.io.Serializable>
    getIdClass()
    {
        return idClass;
    }

    protected static dat.Factory.Information.Entity.BaseEntity
    get(java.io.Serializable id)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting retrieve an entity from database with ID={}", id);
            dat.Factory.Information.Entity.BaseEntity entity = em.find(getEntityClass(), id);
            log.debug("Successfully retrieved and entity from the database");
            return entity;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve and entity of {} with primary-key= {} from the database", entityClass, id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve an entity from the database", e );
        }
    }

    protected static java.util.Set<dat.Factory.Information.Entity.BaseEntity>
    getAll()
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to retrieve all database entries of {}", entityClass);
            em.getTransaction().begin();
            java.util.List<? extends dat.Factory.Information.Entity.BaseEntity> all = em.createQuery("SELECT e FROM "+ entityClass.getSimpleName() + " e", entityClass).getResultList();
            log.debug("Successfully retrieved all the database entries of {}", entityClass);
            return new java.util.HashSet(all);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve all database entries matching = {}", entityClass);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve all database entries of an entity", e);
        }
    }

    protected static dat.Factory.Information.Entity.BaseEntity
    put(dat.Factory.Information.Entity.BaseEntity entity)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to persist {} into the database", entity);
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            log.debug("Successfully persisted {}", entity);
            return entity;
        } catch (RuntimeException e){
            log.error("An error happen while trying to persist {}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to persist an entity", e);
        }
    }

    protected static dat.Factory.Information.Entity.BaseEntity
    patchEntity(dat.Factory.Information.Entity.BaseEntity entity)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to update an entity to {}", entity);
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
            log.debug("Successfully updated {}", entity);
            return entity;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to update a database entry to ={}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update en entity from database", e);
        }
    }

    protected static dat.Factory.Information.Entity.BaseEntity
    patchId(java.lang.Class<? extends java.io.Serializable> id)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to update a database entry with id={}", id);
            em.getTransaction().begin();
            dat.Factory.Information.Entity.BaseEntity entity = em.merge(get(id));
            em.getTransaction().commit();
            log.debug("Successfully updated a database entry by id={}", id);
            return entity;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to update a database entry with id={}", id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update a database entry by a primary-key",e);
        }
    }


    protected static
    void deleteId(java.lang.Class<? extends java.io.Serializable> id)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to delete a database entry with id={}", id);
            em.getTransaction().begin();
            em.remove(get(id));
            em.getTransaction().commit();
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying tio delete a database entry with id={}", id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete a database entry by id", e);
        }
    }

    protected static
    void deleteEntity(dat.Factory.Information.Entity.BaseEntity entity)
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to delete a database entry that looks like ={}", entity);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            log.debug("Successfully removed a database entry that looked like this = {}", entity);;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to remove a database entry that looks like this = {}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete an entity from database", e);
        }
    }
}
