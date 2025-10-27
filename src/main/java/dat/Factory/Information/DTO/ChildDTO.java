package dat.Factory.Information.DTO;

@lombok.Getter
public class ChildDTO extends BaseDTO
{
    private final Object                field1;
    //private final java.io.Serializable  parentId;

    public ChildDTO(dat.Factory.Information.Entity.ChildEntity entity)
            throws dat.Factory.Exception.DTOException
    {
        super(entity);
        if (entity == null) {
                throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.NOT_ACCEPTABLE,        // Throws Exception if parameter (entity) is null
                        "Entity is NOT allowed to be null");
            }
            this.id         =   entity.getId();                                                 //  Retrieves id directly from ChildEntity
            this.field1     =   entity.getField1();                                             //  Retrieves field1 directly from ChildEntity
            if (entity.getParent() == null){                                                                            //  Checks if the ChildEntity has a parent
                throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.NOT_ACCEPTABLE,        //  Throws Exception if parameter field (entity.getParent()) is null
                        "Parent field (in entity) is not allowed to be null");
            }
            this.parentId   =   entity.getParent().getId();                                   //  Retrieves id from ParentEntity through ChildEntity
    }

    public ChildDTO(java.io.Serializable    id,
                    java.io.Serializable    parentId,
                    Object                  field1)
    {
        super(id, parentId);
        this.field1     = field1;
    }

    // Convert to ParentDTO by using the provided ParentEntity (delegates to ParentDTO ctor)
    public dat.Factory.Information.DTO.ParentDTO
    toParentDTO(dat.Factory.Information.Entity.ParentEntity parentEntity)
            throws dat.Factory.Exception.DTOException
    {
        return new ParentDTO( parentEntity );
    }
}