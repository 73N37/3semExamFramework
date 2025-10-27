package dat.Factory.Information.Entity;


import dat.Factory.Information.UserType;

@lombok.Getter                                                                                                          // Creates get-methods for each Field
@jakarta.persistence.Entity                                                                                             // Marks this class as an JPA entity (persistable)
@jakarta.persistence.Table(name = "grandparent")                                                                        // Assigns table-name: grandparent
public class GrandParentEntity extends BaseEntity
{
    @jakarta.persistence.Column(name        = "grandparent_id",                                                         // Assigns column-name: grandparent_id (for grandparent table)
            nullable    = false,                                                                                        // Enforces that the database entry MUST NEVER be null
            unique      = true)                                                                                         // Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.io.Serializable id;

    @lombok.Setter                                                                                                      // Creates a set-method for this Field
    @jakarta.persistence.Column(name        = "field1",                                                                 // Assigns column-name: field1
            nullable    = false,                                                                                        // Enforces that the database entry MUST NEVER be null
            unique      = true)                                                                                         // Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.lang.Object field1;

    @lombok.Setter                                                                                                      // Creates a set-method for this Field
    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)                                                // Persist the Enum as a String (toString) rather than as an Enum
    @jakarta.persistence.Column(name        = "field3",                                                                 // Assigns column-name: field3
            nullable    = false)                                                                                        // Enforces that the database entry MUST NEVER be null
    private UserType field3;

    @jakarta.persistence.OneToMany( mappedBy= "grandParent",                                                            // Non-owning side of a relation. The owning side is a 'grandParent' Field in ParentEntity
            fetch   =   jakarta.persistence.FetchType.LAZY,                                                             // Fetching data only when the Object (GrandParentEntity) is needed (method-call or variable definition)
            cascade = { jakarta.persistence.CascadeType.MERGE,                                                          // Allows EntityManager operation (merge)
                    jakarta.persistence.CascadeType.PERSIST,                                                            // Allows EntityManager operation (persist)
                    jakarta.persistence.CascadeType.REMOVE})                                                            // Allows EntityManager operation (remove)
    private java.util.Set<ParentEntity> parentSet = new java.util.HashSet<>();

    protected GrandParentEntity(){}                                                                                     // Requirement by JPA
    // I am using ? (wildcard) to enforce that both GrandParentEntity and ParentEntity,
    // use the same datatype for ID id
    public GrandParentEntity(java.util.Set<ParentEntity> childrenToGrandparent){
        this.parentSet = childrenToGrandparent;
    }

    public ChildEntity toChild(ParentEntity parentSet)
            throws dat.Factory.Exception.EntityException
    {
        return new ChildEntity(parentSet);
    }

    /* Link a ParentEntity to this GrandParentEntity (sets the back-reference and adds to collection) */
    public ParentEntity
    addParent(dat.Factory.Information.Entity.ParentEntity entity)
            throws dat.Factory.Exception.EntityException
    {
        if ( entity == null){
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
                    "parameter (ParentEntity) is NOT allowed to be null");
        }
        entity.setGrandParent(this);
        this.parentSet.add(entity);
        return entity;
    }

    /* Convenience: collect all ChildEntity instances reachable through this grandparent's parents */
    public java.util.Set<ChildEntity>
    getAllChildren()
    {
        java.util.Set<ChildEntity> children = new java.util.HashSet<>();
        for (ParentEntity parent : this.parentSet)
        {
            children.addAll(parent.getChildSet());
        }
        return children;
    }
}
