package T3N3T.Factory.DAO.Implementation;

public class DAO
{
    private final static org.slf4j.Logger                                                   log = org.slf4j.LoggerFactory.getLogger(DAO.class);
    private static DAO                                                                      instance;

    private static jakarta.persistence.EntityManagerFactory                                 emf;
    private static java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>   entityClass;
    private static java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>         dtoClass;
    private static java.lang.Class<? extends java.io.Serializable>                          idClass;

    private DAO(    jakarta.persistence.EntityManagerFactory                                _emf,
                    java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>  entityClass,
                    java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>        dtoClass,
                    java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        emf                 =   _emf;
        this.entityClass    =   entityClass;
        this.dtoClass       =   dtoClass;
        this.idClass        =   idClass;
    }

    private static
    DAO getInstance(jakarta.persistence.EntityManagerFactory                                _emf,
                    java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>  entityClass,
                    java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>        dtoClass,
                    java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        if (instance == null) {
            instance = new DAO(_emf, entityClass, dtoClass, idClass);
        }
        return instance;
    }

    private static java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>
    getEntityClass() {
        return entityClass;
    }

    private static java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>
    getDtoClass(){
        return dtoClass;
    }

    private static java.lang.Class<? extends java.io.Serializable>
    getIdClass()
    {
        return idClass;
    }

    private static T3N3T.Factory.Information.Entity.BaseEntity
    get(java.io.Serializable id)
            throws T3N3T.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting retrieve an entity from database with ID={}", id);
            T3N3T.Factory.Information.Entity.BaseEntity entity = em.find(entityClass, id);
            log.debug("Successfully retrieved and entity from the database");
            return entity;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve and entity of {} with primary-key= {} from the database", entityClass, id);
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve an entity from the database", e );
        }
    }

    private static java.util.Set<T3N3T.Factory.Information.Entity.BaseEntity>
    getAll()
            throws T3N3T.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to retrieve all database entries of {}", entityClass);
            em.getTransaction().begin();
            java.util.List<? extends T3N3T.Factory.Information.Entity.BaseEntity> all = em.createQuery("SELECT e FROM "+ entityClass.getSimpleName() + " e", entityClass).getResultList();
            log.debug("Successfully retrieved all the database entries of {}", entityClass);
            return new java.util.HashSet(all);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve all database entries matching = {}", entityClass);
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve all database entries of an entity", e);
        }
    }

    private static T3N3T.Factory.Information.Entity.BaseEntity
    put(T3N3T.Factory.Information.Entity.BaseEntity entity)
            throws T3N3T.Factory.Exception.DAOException
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
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to persist an entity", e);
        }
    }

    private static T3N3T.Factory.Information.Entity.BaseEntity
    patchEntity(T3N3T.Factory.Information.Entity.BaseEntity entity)
            throws T3N3T.Factory.Exception.DAOException
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
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update en entity from database", e);
        }
    }

    private static T3N3T.Factory.Information.Entity.BaseEntity
    patchId(java.lang.Class<? extends java.io.Serializable> id)
            throws T3N3T.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to update a database entry with id={}", id);
            em.getTransaction().begin();
            T3N3T.Factory.Information.Entity.BaseEntity entity = em.merge(get(id));
            em.getTransaction().commit();
            log.debug("Successfully updated a database entry by id={}", id);
            return entity;
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to update a database entry with id={}", id);
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update a database entry by a primary-key",e);
        }
    }


    private static
    void deleteId(java.lang.Class<? extends java.io.Serializable> id)
            throws T3N3T.Factory.Exception.DAOException
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
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete a database entry by id", e);
        }
    }

    private static
    void deleteEntity(T3N3T.Factory.Information.Entity.BaseEntity entity)
            throws T3N3T.Factory.Exception.DAOException
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
            throw new T3N3T.Factory.Exception.DAOException(T3N3T.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete an entity from database", e);
        }
    }

    public static class Access{
        public Access(){}

        public T3N3T.Factory.DAO.Implementation.DAO
        getInstance(    jakarta.persistence.EntityManagerFactory                                emf,
                        java.lang.Class<? extends T3N3T.Factory.Information.Entity.BaseEntity>  entityClass,
                        java.lang.Class<? extends T3N3T.Factory.Information.DTO.BaseDTO>        dtoClass,
                        java.lang.Class<? extends java.io.Serializable>                         idClass)
        {
            return DAO.getInstance(emf, entityClass, dtoClass, idClass);
        }

        public T3N3T.Factory.Information.Entity.BaseEntity
        getEntity(java.lang.Class<? extends java.io.Serializable> id)
        {
            return DAO.get(id);
        }

        public java.util.Set<T3N3T.Factory.Information.Entity.BaseEntity>
        getAllEntities()
        {
            return DAO.getAll();
        }

        public T3N3T.Factory.Information.Entity.BaseEntity
        putEntity(T3N3T.Factory.Information.Entity.BaseEntity entity)
        {
            return DAO.put(entity);
        }

        public T3N3T.Factory.Information.Entity.BaseEntity
        patchEntity(T3N3T.Factory.Information.Entity.BaseEntity entity)
        {
            return DAO.patchEntity(entity);
        }

        public T3N3T.Factory.Information.Entity.BaseEntity
        patchId(java.lang.Class<? extends java.io.Serializable> id)
        {
            return DAO.patchId(id);
        }

        public void
        deleteId(java.lang.Class<? extends java.io.Serializable> id)
        {
            DAO.deleteId(id);
        }

        public void
        deleteEntity(T3N3T.Factory.Information.Entity.BaseEntity entity){
            DAO.deleteEntity(entity);
        }
    }
}
