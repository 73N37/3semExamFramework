package dat.Factory.Information.DTO;

@lombok.Getter
public class BaseDTO {
    protected java.io.Serializable id;

    protected java.util.Set<java.io.Serializable> childIdSet;
    protected java.util.Set<java.io.Serializable> parentIdSet;
    protected java.util.Set<java.io.Serializable> grandParentIdSet;

    // ensure collections are never null
    public BaseDTO() {
        this.childIdSet = new java.util.HashSet<>();
        this.parentIdSet = new java.util.HashSet<>();
        this.grandParentIdSet = new java.util.HashSet<>();
    }

    // ID-only constructor
    public BaseDTO(java.io.Serializable id)
    {
        this();
        this.id = id;
    }

    public BaseDTO(dat.Factory.Information.Entity.BaseEntity entity)
    {
        this(entity.getId());
        this.childIdSet = entity.getChildIdSet();
        this.parentIdSet = entity.getParentIdSet();
        this.grandParentIdSet = entity.getGrandParentIdSet();
    }

}