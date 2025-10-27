package dat.Factory.Information;


import dat.Factory.Information.DTO.GrandParentDTO;
import dat.Factory.Information.Entity.GrandParentEntity;

public final class BaseMapper {
    private BaseMapper() {}

    // --- Entity -> DTO ---

    public static dat.Factory.Information.DTO.BaseDTO
    toBaseDTO(dat.Factory.Information.Entity.BaseEntity entity)
            throws dat.Factory.Exception.EntityException {
        if (entity == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (entity) is NOT allowed to be null (toBaseDTO)");
        try {
            if (entity instanceof dat.Factory.Information.Entity.ChildEntity) return new dat.Factory.Information.DTO.ChildDTO((dat.Factory.Information.Entity.ChildEntity) entity);
            if (entity instanceof dat.Factory.Information.Entity.ParentEntity) return new dat.Factory.Information.DTO.ParentDTO((dat.Factory.Information.Entity.ParentEntity) entity);
            if (entity instanceof dat.Factory.Information.Entity.GrandParentEntity) return new dat.Factory.Information.DTO.GrandParentDTO((dat.Factory.Information.Entity.GrandParentEntity) entity);
            return null;
        } catch (RuntimeException ex) {
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST, "Error converting entity to DTO (toBaseDTO)", ex);
        }
    }

    public static java.util.Set<dat.Factory.Information.DTO.ChildDTO>
    toChildSetDTO(java.util.Set<dat.Factory.Information.Entity.ChildEntity> set)
            throws dat.Factory.Exception.EntityException
    {
        if (set == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (set) is NOT allowed to be null (toChildSetDTO)");
        try
        {
            return set.stream()
                    .filter(java.util.Objects::nonNull)
                    .map(be -> {
                        try { return new dat.Factory.Information.DTO.ChildDTO(be); }
                        catch (dat.Factory.Exception.DTOException de) { throw new RuntimeException(de); }
                    })
                    .filter(java.util.Objects::nonNull)
                    .collect(java.util.stream.Collectors.toSet());
        } catch (RuntimeException ex)
        {
            if (ex.getCause() instanceof dat.Factory.Exception.DTOException)
            {
                throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                        "DTO conversion failed",
                        ex.getCause());
            }
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "Error converting Set<ChildEntity> to Set<ChildDTO>",
                    ex);
        }
    }

    // --- DTO -> Entity (example for Parent/Child) ---

    public static dat.Factory.Information.Entity.ParentEntity
    toParentEntity( dat.Factory.Information.DTO.ParentDTO dto)
            throws  dat.Factory.Exception.EntityException
    {
        if (dto == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (dto) is NOT allowed to be null (toParentEntity)");
        try
        {
            dat.Factory.Information.Entity.GrandParentEntity grandParent = (dto.getGrandParent() != null) ? toGrandParentEntity(dto.getGrandParent()) : null;
            if (grandParent == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_ACCEPTABLE, "Parent->Entity conversion requires grandParent DTO");
            dat.Factory.Information.Entity.ParentEntity parent = new dat.Factory.Information.Entity.ParentEntity(grandParent, java.util.Collections.emptySet());
            // copy scalar fields defensively if needed (setField1 etc.)
            if (dto.getChildSet() != null)
            {
                parent.setChildSet(toChildSetEntity(dto.getChildSet(), parent));
            }
            return parent;
        } catch (RuntimeException ex)
        {
            throw (ex instanceof dat.Factory.Exception.EntityException) ?
                    (dat.Factory.Exception.EntityException) ex
                    : new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.BAD_REQUEST, "Error converting ParentDTO to ParentEntity", ex);
        }
    }

    public static dat.Factory.Information.DTO.ParentDTO
    toParentDTO(dat.Factory.Information.Entity.ParentEntity entity)
        throws dat.Factory.Exception.DTOException
    {
        dat.Factory.Information.DTO.ParentDTO result = null;
        if (entity == null) throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (entity) is NOT allowed to be null (toParentDTO)");
        try
        {
            dat.Factory.Information.Entity.GrandParentEntity grandParent = entity.getGrandParent();
            if (grandParent != null)
            {
                result = new dat.Factory.Information.DTO.ParentDTO(grandParent.getId(), toGrandParentDTO(grandParent));
            }
            if (result == null) {
                throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                                                            "return value (result) is NOT allowed to be null (toParentDTO)");
            }
            return result;
        } catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                                                        "An error happen while trying to convert (toParentDTO)",
                                                        ex);
        }
    }

    private static java.util.Set<dat.Factory.Information.Entity.ChildEntity>
    toChildSetEntity(   java.util.Set<dat.Factory.Information.DTO.ChildDTO> dtos,
                        dat.Factory.Information.Entity.ParentEntity         parent)
            throws dat.Factory.Exception.EntityException
    {
        if (dtos == null) return new java.util.HashSet<>();
        try
        {
            return dtos.stream()
                    .filter(java.util.Objects::nonNull)
                    .map(dto -> {
                        try
                        {
                            dat.Factory.Information.Entity.ChildEntity child = new dat.Factory.Information.Entity.ChildEntity(parent);
                            // copy child fields if present: child.setField1(dto.getField1());
                            return child;
                        } catch (RuntimeException e)
                        {
                            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                                                                            "An error happen while trying to access dto.getField1",
                                                                            e);
                        }
                    })
                    .collect(java.util.stream.Collectors.toSet());
        } catch (RuntimeException ex) {
            throw new dat.Factory.Exception.EntityException(  dat.Factory.Exception.ErrorType.BAD_REQUEST,
                                        "Error converting Set<ChildDTO> to Set<ChildEntity>",
                                        ex);
        }
    }

    public static dat.Factory.Information.Entity.GrandParentEntity
    toGrandParentEntity(dat.Factory.Information.DTO.GrandParentDTO dto)
            throws dat.Factory.Exception.EntityException {
        if (dto == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (dto) is NOT allowed to be null (toGrandParentEntity)");
        try
        {
            dat.Factory.Information.Entity.GrandParentEntity grandParent = new dat.Factory.Information.Entity.GrandParentEntity(new java.util.HashSet<>());
            // set scalar fields...
            if (dto.getParentSet() != null)
            {
                for (dat.Factory.Information.DTO.ParentDTO parentDTO : dto.getParentSet())
                {
                    dat.Factory.Information.Entity.ParentEntity parent = toParentEntity(parentDTO);
                    grandParent.getParentSet().add(parent);
                }
            }
            return grandParent;
        } catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.EntityException(  dat.Factory.Exception.ErrorType.BAD_REQUEST,
                                        "Error converting GrandParentDTO to GrandParentEntity",
                                        ex);
        }
    }

    public static GrandParentDTO
    toGrandParentDTO(GrandParentEntity entity)
            throws dat.Factory.Exception.DTOException
    {
        if (entity == null) throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.FORBIDDEN, "parameter (entity) is NOT allowed to be null (toGrandParentDTO)");
        try
        {
            dat.Factory.Information.DTO.GrandParentDTO grandParent = new dat.Factory.Information.DTO.GrandParentDTO(entity);
            for (dat.Factory.Information.Entity.ParentEntity parentEntity : entity.getParentSet())
            {
                dat.Factory.Information.DTO.ParentDTO parent = toParentDTO(parentEntity);
                grandParent.getParentSet().add(parent);
            }
            return grandParent;
        } catch (RuntimeException ex)
        {
            throw new dat.Factory.Exception.DTOException()
        }
    }
}