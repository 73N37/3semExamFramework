package T3N3T.Factory.Information.DTO;

@lombok.Getter
public class ParentDTO extends BaseDTO
{
    private final java.io.Serializable  grandParentId;
    private final java.lang.Object      field1;

    public ParentDTO(T3N3T.Factory.Information.Entity.ParentEntity entity)
            throws T3N3T.Factory.Exception.DTOException
    {
        if (entity == null)
        {
            throw new T3N3T.Factory.Exception.DTOException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
                    "parameter (entity) is NOT allowed to be null");
        }
        this.id             = entity.getId();
        this.field1         = entity.getField1();
        if (entity.getGrandParent() == null)
        {
            throw new T3N3T.Factory.Exception.DTOException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
                    "grandparent Field (in entity) is NOT allowed to be null");
        }
        this.grandParentId  = entity.getGrandParent().getId();
    }

    public ParentDTO(java.io.Serializable   id,
                     java.io.Serializable   grandParentId,
                     java.lang.Object       field1)
    {
        this.id             = id;
        this.grandParentId  = grandParentId;
        this.field1         = field1;
    }



    // Convert to GrandParentDTO by using the provided GrandParentEntity (delegates to GrandParentDTO ctor)
    public GrandParentDTO
    toGrandParentDTO(T3N3T.Factory.Information.Entity.GrandParentEntity grandParentEntity)
            throws T3N3T.Factory.Exception.DTOException
    {
        return new GrandParentDTO( grandParentEntity );
    }

}
