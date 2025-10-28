package dat.Factory.Operation.DAO;


//import dat.Factory.Operation.DAO.Implementation.DAO;

public abstract class Abstract
        extends dat.Factory.Operation.Operation
{
    protected final static org.slf4j.Logger                     log = org.slf4j.LoggerFactory.getLogger(Abstract.class);
    private static Abstract                                     instance;
    protected Abstract
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity    entityClass,

                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.DTO.BaseDTO          dtoClass
            )
    {
        super(entityClass, dtoClass);
    }

    public static
    Abstract getInstance
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity    entityClass,

                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.DTO.BaseDTO          dtoClass
            )
    {
        if (instance == null) {
            instance = new  dat.Factory.Operation.DAO.Implementation.DAO();
        }
        return instance;
    }


    protected static dat.Factory.Information.DTO.BaseDTO
    get
            (
                    @org.jetbrains.annotations.NotNull
                    java.io.Serializable id
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting retrieve an entity from database with ID={}", id);
            dat.Factory.Information.Entity.BaseEntity entity = em.find(getEntityClass(), id);
            log.debug("Successfully retrieved and entity from the database");
            return mapper.toBaseDTO(entity);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve and entity of {} with primary-key= {} from the database", entityClass, id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve an entity from the database", e );
        }
    }




    protected static java.util.Set<dat.Factory.Information.DTO.BaseDTO>
    getAll
            ()
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to retrieve all database entries of {}", entityClass);
            em.getTransaction().begin();
            java.util.List<? extends dat.Factory.Information.Entity.BaseEntity> all = em.createQuery("SELECT e FROM "+ entityClass.getSimpleName() + " e", entityClass).getResultList();
            java.util.HashSet<dat.Factory.Information.Entity.BaseEntity> result = new java.util.HashSet(all);
            log.debug("Successfully retrieved all the database entries of {}", entityClass);
            return mapper.toBaseDTOSet(result);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to retrieve all database entries matching = {}", entityClass);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to retrieve all database entries of an entity", e);
        }
    }




    protected static dat.Factory.Information.DTO.BaseDTO
    put
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity entity
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to persist {} into the database", entity);
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            log.debug("Successfully persisted {}", entity);
            return mapper.toBaseDTO(entity);
        } catch (RuntimeException e){
            log.error("An error happen while trying to persist {}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to persist an entity", e);
        }
    }




    protected static dat.Factory.Information.DTO.BaseDTO
    patch
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity   entity,

                    @org.jetbrains.annotations.NotNull
                    java.io.Serializable                        id
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to update an entity to {}", entity);
            em.getTransaction().begin();
            dat.Factory.Information.Entity.BaseEntity find = em.find(getEntityClass(), id);
            if (find == null) throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND, "Was not able to find an entity with id="+ id +" in the database (DAO.patch)");
            entity = em.merge(entity);
            em.getTransaction().commit();
            log.debug("Successfully updated {}", entity);
            return mapper.toBaseDTO(entity);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to update a database entry to ={}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update en entity from database", e);
        }
    }




    protected static dat.Factory.Information.DTO.BaseDTO
    patchId
            (
                    @org.jetbrains.annotations.NotNull
                    java.io.Serializable id
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to update a database entry with id={}", id);
            em.getTransaction().begin();
            dat.Factory.Information.Entity.BaseEntity entity = em.merge(em.find(getEntityClass(), id));
            em.getTransaction().commit();
            log.debug("Successfully updated a database entry by id={}", id);
            return mapper.toBaseDTO(entity);
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to update a database entry with id={}", id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to update a database entry by a primary-key",e);
        }
    }




    protected static
    void deleteId
            (
                    @org.jetbrains.annotations.NotNull
                    java.io.Serializable id
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to delete a database entry with id={}", id);
            em.getTransaction().begin();
            dat.Factory.Information.Entity.BaseEntity remove = em.find(getEntityClass(), id);
            if (remove != null){
                em.remove(remove);
                em.getTransaction().commit();
                log.debug("Successfully removed a database entry with ID= {}", id);;
                return;
            }
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Was not able to find any database entry with the provided id as a parameter (DAO.deleteId)");
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying tio delete a database entry with id={}", id);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete a database entry by id (DAO.deleteId)", e);
        }
    }




    protected static
    void deleteEntity
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity entity
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            log.debug("Attempting to delete a database entry that looks like ={}", entity);
            em.getTransaction().begin();
            dat.Factory.Information.Entity.BaseEntity remove = em.find(getEntityClass(), entity.getId());
            if (remove != null)
            {
                em.remove(entity);
                em.getTransaction().commit();
                log.debug("Successfully removed a database entry that looked like this = {}", entity);;
                return;
            }
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                "Was not able to find any database entry with the provided entity as a parameter (DAO.deleteId)");
        } catch (RuntimeException e)
        {
            log.error("An error happen while trying to remove a database entry that looks like this = {}", entity);
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while trying to delete an entity from database", e);
        }
    }



    protected static
    boolean validatePrimaryKey
            (
                    @org.jetbrains.annotations.NotNull
                    java.io.Serializable id
            )
            throws dat.Factory.Exception.DAOException
    {
        try (jakarta.persistence.EntityManager em = emf.createEntityManager())
        {
            return em.find(getEntityClass(), id) != null ? true : false;
        }
        catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.DAOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "Was unable to find a database entry with the provided id="+id);
        }
    }




    public static class Access
    {
        public Access(){}




        public dat.Factory.Operation.DAO.Abstract
        getInstance
                ()
        {
            return Abstract.getInstance(getEntity(), getDto());
        }




        public dat.Factory.Information.DTO.BaseDTO
        get
                (
                        @org.jetbrains.annotations.NotNull
                        java.io.Serializable id
                )
        {
            return  dat.Factory.Operation.DAO.Implementation.DAO.get(id);
        }




        public java.util.Set<dat.Factory.Information.DTO.BaseDTO>
        getAllEntities()
        {
            return  dat.Factory.Operation.DAO.Implementation.DAO.getAll();
        }




        public dat.Factory.Information.DTO.BaseDTO
        put
                (
                        @org.jetbrains.annotations.NotNull
                        dat.Factory.Information.Entity.BaseEntity entity
                )
        {
            return  dat.Factory.Operation.DAO.Implementation.DAO.put(entity);
        }





        public dat.Factory.Information.DTO.BaseDTO
        patch
                (
                        @org.jetbrains.annotations.NotNull
                        dat.Factory.Information.Entity.BaseEntity   entity,

                        @org.jetbrains.annotations.NotNull
                        java.io.Serializable                        id
                )
        {
            return  dat.Factory.Operation.DAO.Implementation.DAO.patch(entity, id);
        }





        public void
        deleteId
                (
                        @org.jetbrains.annotations.NotNull
                        java.io.Serializable id
                )
        {
            dat.Factory.Operation.DAO.Implementation.DAO.deleteId(id);
        }





        public void
        deleteEntity
                (
                        @org.jetbrains.annotations.NotNull
                        dat.Factory.Information.Entity.BaseEntity entity
                )
        {
            dat.Factory.Operation.DAO.Implementation.DAO.deleteEntity(entity);
        }




        public boolean
        validatePrimaryKey
                (
                        @org.jetbrains.annotations.NotNull
                        java.io.Serializable id
                )
        {
            return dat.Factory.Operation.DAO.Implementation.DAO.validatePrimaryKey(id);
        }
    }
}
