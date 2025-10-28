package dat.Factory.Information.Entity;

@lombok.Getter
@lombok.Setter
@jakarta.persistence.MappedSuperclass
public class BaseEntity {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    protected java.io.Serializable id;

    // relation collections -- concrete mapping annotations remain in concrete subclasses
    protected java.util.Set<java.io.Serializable> childIdSet;
    protected java.util.Set<java.io.Serializable> parentIdSet;
    protected java.util.Set<java.io.Serializable> grandParentIdSet;

    public BaseEntity() {
        this.childIdSet = new java.util.HashSet<>();
        this.parentIdSet = new java.util.HashSet<>();
        this.grandParentIdSet = new java.util.HashSet<>();
    }

    public BaseEntity(java.io.Serializable id)
    {
        this();
        this.id = id;
    }

    public BaseEntity(dat.Factory.Information.DTO.BaseDTO dto)
    {
        this(dto.getId());
        this.childIdSet = dto.getChildIdSet();
        this.parentIdSet = dto.getParentIdSet();
        this.grandParentIdSet = dto.getGrandParentIdSet();
    }

    // simple helpers to keep bidirectional graph consistent (no conversion logic)
    public void addParent(BaseEntity parent)
            throws dat.Factory.Exception.EntityException
    {

    }

    public void addChild(BaseEntity child)
            throws dat.Factory.Exception.EntityException
    {

    }
}