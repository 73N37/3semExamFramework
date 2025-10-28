package dat.Factory.Information;


//import dat.Factory.Information.Entity.GrandParentEntity;

public final class BaseMapper {
    private static BaseMapper instance;

    public static synchronized BaseMapper getInstance()
    {
        if (instance == null) return new BaseMapper();
        return instance;
    }

    // registry: explicit mappings preferred for reliability
    private static final java.util.concurrent.ConcurrentMap<
            java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>,
            java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>
            >   DTO_TO_ENTITY = new java.util.concurrent.ConcurrentHashMap<>();

    // registry: explicit mappings preferred for reliability
    private static final java.util.concurrent.ConcurrentMap<
            java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>,
            java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>
            > ENTITY_TO_DTO = new java.util.concurrent.ConcurrentHashMap<>();


    private BaseMapper() {}

//TODO=============================================[DTO]================================================================
    public static dat.Factory.Information.DTO.BaseDTO
    toBaseDTO(dat.Factory.Information.Entity.BaseEntity entity)
            throws dat.Factory.Exception.DTOException
    {
        if (entity == null) throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (entity) is NOT allowed to be null (toBaseDTO)");
        try
        {
            if (entity instanceof dat.Factory.Information.Entity.BaseEntity) return new dat.Factory.Information.DTO.BaseDTO(entity);
            throw new RuntimeException();
        } catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST, "Error converting entity to DTO (toBaseDTO)", ex);
        }
    }

    public static java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>
    toBaseDtoClass(java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity> entityClass)
            throws dat.Factory.Exception.DTOException
    {
        if (entityClass == null)
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                    "parameter (entityClass) is NOT allowed to be null (toBaseDtoClass)");

        // check if registry first
        java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO> mapped = ENTITY_TO_DTO.get(entityClass);
        if (mapped != null) return mapped;

        String entityName = entityClass.getName();
        String dtoName = entityName.replace(".Entity.", ".DTO.");
        if (dtoName.endsWith("Entity"))
        {
            dtoName = dtoName.substring(0, dtoName.length() -6) + "DTO";
        }
        try
        {
            Class<?> discovered = Class.forName(dtoName);
            if (!dat.Factory.Information.DTO.BaseDTO.class.isAssignableFrom(discovered))
            {
                throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                        "Resolved class is not BaseDTO: " + dtoName);
            }
            java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO> result =
                    (java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>) discovered;

            // cache for future lookups
            ENTITY_TO_DTO.putIfAbsent(entityClass, result);
            return result;
        } catch (ClassCastException ex)
        {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "DTO class not found for Entity: " + entityClass.getName(), ex);
        } catch (dat.Factory.Exception.DTOException ex)
        {
            throw ex;
        } catch (Exception ex)
        {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Error while resolving dto class for Entity: " + entityClass.getName(), ex);
        }
    }

    public static dat.Factory.Information.DTO.BaseDTO
    instantiateDtoClass(java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO> dtoClass)
            throws dat.Factory.Exception.DTOException
    {
        if (dtoClass == null) throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                "parameter (dtoClass) is NOT allowed to be null (instantiateDtoClass)");
        try {
            java.lang.reflect.Constructor<? extends dat.Factory.Information.DTO.BaseDTO> ctor =
                    dtoClass.getDeclaredConstructor();
            if (!ctor.canAccess(null)) ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (ClassCastException ex) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error happen while casting to DTO class : " + dtoClass.getName(), ex);
        } catch (NoSuchMethodException ex) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "No no-arg constructor available for DTO: " + dtoClass.getName(), ex);
        } catch (ReflectiveOperationException ex) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Failed to instantiate DTO: " + dtoClass.getName(), ex);
        } catch (Exception ex) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Unexpected error while instantiating DTO: " + dtoClass.getName(), ex);
        }
    }

    //TODO=============================================[Entity]=========================================================

    public static dat.Factory.Information.Entity.BaseEntity
    toBaseEntity(dat.Factory.Information.DTO.BaseDTO dto)
            throws dat.Factory.Exception.EntityException
    {
        if (dto == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (dto) is NOT allowed to be null");
        try
        {
            if (dto instanceof dat.Factory.Information.DTO.BaseDTO) return new dat.Factory.Information.Entity.BaseEntity(dto);
            throw new RuntimeException();
        } catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST, "Error converting DTO to Entity (toBaseEntity)", ex);
        }
    }


    public static java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>
    toBaseEntityClass(java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO> dtoClass)
            throws dat.Factory.Exception.EntityException
    {
        if (dtoClass == null)
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                    "parameter (dtoClass) is NOT allowed to be null (toBaseEntityClass)");

        // check registry first
        java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity> mapped = DTO_TO_ENTITY.get(dtoClass);
        if (mapped != null) return mapped;

        // try to derive by package/name convention: dat.Factory.Information.DTO.*DTO -> dat.Factory.Information.Entity.*Entity
        String dtoName = dtoClass.getName();
        String entityName = dtoName.replace(".DTO.", ".Entity.");
        if (entityName.endsWith("DTO")) {
            entityName = entityName.substring(0, entityName.length() - 3) + "Entity";
        }

        try {
            Class<?> discovered = Class.forName(entityName);
            if (!dat.Factory.Information.Entity.BaseEntity.class.isAssignableFrom(discovered)) {
                throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                        "Resolved class is not a BaseEntity: " + entityName);
            }
            java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity> result =
                    (java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>) discovered;

            // cache for future lookups
            DTO_TO_ENTITY.putIfAbsent(dtoClass, result);
            return result;
        } catch (ClassNotFoundException ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "Entity class not found for DTO: " + dtoClass.getName(), ex);
        } catch (dat.Factory.Exception.EntityException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Error resolving entity class for DTO: " + dtoClass.getName(), ex);
        }
    }

    public static dat.Factory.Information.Entity.BaseEntity
    instantiateEntityClass(java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity> entityClass)
            throws dat.Factory.Exception.EntityException
    {
        if (entityClass == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                "parameter (entityClass) is NOT allowed to be null (instantiateEntityClass)");
        try
        {
            java.lang.reflect.Constructor<? extends dat.Factory.Information.Entity.BaseEntity> ctor = entityClass.getDeclaredConstructor();
            if(!ctor.canAccess(null)) ctor.setAccessible(true);
            return ctor.newInstance();
        }
        catch (ClassCastException ex)
        {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "An error while casting Entity class: " + entityClass.getName(),
                    ex);
        }
        catch (NoSuchMethodException ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "No no-arg constructor available for entity: " + entityClass.getName(), ex);
        }
        catch (ReflectiveOperationException ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Failed to instantiate entity: " + entityClass.getName(), ex);
        }
        catch (Exception ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Unexpected error while instantiating entity: " + entityClass.getName(), ex);
        }
    }
}