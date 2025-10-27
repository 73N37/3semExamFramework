package T3N3T.Factory.Information.DTO;

@lombok.Getter
public class ChildDTO extends BaseDTO
{
    private final Object                field1;
    private final java.io.Serializable  parentId;

    public ChildDTO(T3N3T.Factory.Information.Entity.ChildEntity entity)
            throws T3N3T.Factory.Exception.DTOException
    {
            if (entity == null) {
                throw new T3N3T.Factory.Exception.DTOException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,        // Throws Exception if parameter (entity) is null
                        "Entity is NOT allowed to be null");
            }
            this.id         =   entity.getId();                                                 //  Retrieves id directly from ChildEntity
            this.field1     =   entity.getField1();                                             //  Retrieves field1 directly from ChildEntity
            if (entity.getParent() == null){                                                                            //  Checks if the ChildEntity has a parent
                throw new T3N3T.Factory.Exception.DTOException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,        //  Throws Exception if parameter field (entity.getParent()) is null
                        "Parent field (in entity) is not allowed to be null");
            }
            this.parentId   =   entity.getParent().getId();                                   //  Retrieves id from ParentEntity through ChildEntity
    }

    public ChildDTO(java.io.Serializable    id,
                    java.io.Serializable    parentId,
                    Object                  field1)
    {
        this.id         = id;
        this.parentId   = parentId;
        this.field1     = field1;
    }

    // Convert to ParentDTO by using the provided ParentEntity (delegates to ParentDTO ctor)
    public T3N3T.Factory.Information.DTO.ParentDTO
    toParentDTO(T3N3T.Factory.Information.Entity.ParentEntity parentEntity)
            throws T3N3T.Factory.Exception.DTOException
    {
        return new ParentDTO( parentEntity );
    }
}