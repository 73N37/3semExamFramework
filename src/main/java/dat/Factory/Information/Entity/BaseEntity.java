package dat.Factory.Information.Entity;

@lombok.Getter
@lombok.Setter
@jakarta.persistence.MappedSuperclass
public class BaseEntity {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    protected java.io.Serializable id;

    // relation collections -- concrete mapping annotations remain in concrete subclasses
    protected java.util.Set<ChildEntity> childSet = new java.util.HashSet<>();
    protected java.util.Set<ParentEntity> parentSet = new java.util.HashSet<>();

    protected BaseEntity() {
        this.childSet = new java.util.HashSet<>();
        this.parentSet = new java.util.HashSet<>();
    }

    // simple helpers to keep bidirectional graph consistent (no conversion logic)
    public ParentEntity addParent(ParentEntity parent) {
        if (parent == null) return null;
        parent.setGrandParent(this instanceof GrandParentEntity ? (GrandParentEntity) this : parent.getGrandParent());
        this.parentSet.add(parent);
        return parent;
    }

    public ChildEntity addChild(ChildEntity child) {
        if (child == null) return null;
        child.setParent(child.getParent() == null ? null : child.getParent());
        this.childSet.add(child);
        return child;
    }
}