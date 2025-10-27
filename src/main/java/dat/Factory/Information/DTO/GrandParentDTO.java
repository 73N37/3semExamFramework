package T3N3T.Factory.Information.DTO;

@lombok.Getter
public class GrandParentDTO extends BaseDTO
{
    private final java.lang.Object      field1;

    public GrandParentDTO(T3N3T.Factory.Information.Entity.GrandParentEntity entity)
            throws T3N3T.Factory.Exception.DTOException
    {
        if ( entity == null )
        {
            throw new T3N3T.Factory.Exception.DTOException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
                    "entity is NOT allowed to be null");
        }
        this.id     = entity.getId();
        this.field1 = entity.getField1();
    }

    public GrandParentDTO(java.io.Serializable id,
                          java.lang.Object field1)
    {
        this.id     = id;
        this.field1 = field1;
    }

    // Convert to ChildDTO by using the provided ChildEntity (delegates to ChildDTO ctor)
    public ChildDTO toChildDTO(T3N3T.Factory.Information.Entity.ChildEntity childEntity)
            throws T3N3T.Factory.Exception.DTOException
    {
        return new ChildDTO( childEntity );
    }
}
