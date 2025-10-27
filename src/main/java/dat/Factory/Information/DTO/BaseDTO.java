package dat.Factory.Information.DTO;

@lombok.Getter
public class BaseDTO {
    protected java.io.Serializable id;

    protected java.io.Serializable childId;
    protected java.io.Serializable parentId;
    protected java.io.Serializable grandParentId;

    protected java.util.Set<ChildDTO> childSet = new java.util.HashSet<>();
    protected java.util.Set<ParentDTO> parentSet = new java.util.HashSet<>();

    // ensure collections are never null
    protected BaseDTO() {
        this.childSet = new java.util.HashSet<>();
        this.parentSet = new java.util.HashSet<>();
    }

    // ID-only constructor
    public BaseDTO(java.io.Serializable id)
    {
        this();
        this.id = id;
    }

    // childId + parentId
    public BaseDTO(java.io.Serializable childId,
                   java.io.Serializable parentId)
    {
        this();
        this.childId = childId;
        this.parentId = parentId;
    }

    // childId + parentId + grandParentId
    public BaseDTO(java.io.Serializable childId,
                   java.io.Serializable parentId,
                   java.io.Serializable grandParentId)
    {
        this();
        this.childId = childId;
        this.parentId = parentId;
        this.grandParentId = grandParentId;
    }
}