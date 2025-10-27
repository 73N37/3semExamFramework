package dat.Factory.Information.DTO;

@lombok.Setter
@lombok.Getter
public class GrandParentDTO extends BaseDTO
{
    private final java.lang.Object      field1;

    public GrandParentDTO(dat.Factory.Information.Entity.GrandParentEntity entity)
            throws dat.Factory.Exception.DTOException
    {
        if ( entity == null )
        {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
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
    public ChildDTO toChildDTO(dat.Factory.Information.Entity.ChildEntity childEntity)
            throws dat.Factory.Exception.DTOException
    {
        return new ChildDTO( childEntity );
    }
}
