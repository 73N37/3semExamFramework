package dat.Factory.Information;


//import dat.Factory.Information.Entity.GrandParentEntity;

public final class BaseMapper {
    private static BaseMapper instance;

    public static synchronized BaseMapper getInstance()
    {
        if (instance == null) return new BaseMapper();
        return instance;
    }

    private BaseMapper() {}

    // --- Entity -> DTO ---

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
}