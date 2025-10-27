package dat.Factory.Information.Entity;

@lombok.Getter
@lombok.Setter
@jakarta.persistence.Entity                                                                                             //  Marks this class as an JPA entity (persistable)
@jakarta.persistence.Table(name = "child")                                                                              //  Assigns table-name: child
public class ChildEntity extends BaseEntity
{
    @jakarta.persistence.Column(name        = "child_id",                                                               //  Assigns a column-name: child_id (for the child table)
                                nullable    = false,                                                                    //  Enforces that the database entry MUST NEVER be null
                                unique      = true)                                                                     //  Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.io.Serializable id;

    @lombok.Setter                                                                                                      //  Creates set-method for this field
    @jakarta.persistence.ManyToOne( fetch   = jakarta.persistence.FetchType.LAZY)                                       //  Owning side: this field stores the FK to ParentEntity; LAZY avoids loading Parent unless accessed
    @jakarta.persistence.JoinColumn(name    = "parent_id",                                                              //  Declares the foreign-key column name in the `child` table
                                    nullable= false)                                                                    //  Enforces that the database entry MUST NEVER be null
    private ParentEntity parent;                                                                                     //  Foreign Key

    @lombok.Setter                                                                                                      // Creates a set-method for this Field
    @jakarta.persistence.Column(name        = "field1",                                                                 // Assigns column-name: field1
                                nullable    = false,                                                                    // Enforces that the database entry MUST NEVER be null
                                unique      = true)                                                                     // Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.lang.Object field1;

    protected ChildEntity(){}                                                                                           //  Required by JPA
    // I am using ? (wildcard) to enforce that both ParentEntity and ChildEntity,
    // use the same datatype for ID id
    public ChildEntity(ParentEntity parent){
        this.parent = parent;
    }

    public ParentEntity
    toParent()
            throws dat.Factory.Exception.DTOException
    {
        java.util.Set<ChildEntity> childToParent = new java.util.HashSet<>();
        childToParent.add(this);
        return new ParentEntity(this.getParent().toGrandParent(), childToParent);
    }
}
