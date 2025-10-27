package T3N3T.Factory.Information.Entity;

@lombok.Getter                                                                                                          //  Creates get-methods for each Field
@jakarta.persistence.Entity                                                                                             //  Marks this class as an JPA entity (persistable)
@jakarta.persistence.Table(name = "parent")                                                                             //  Assigns table-name: parent
public class ParentEntity extends BaseEntity
{
    @jakarta.persistence.Id                                                                                             //  Marks this field as the primary key for JPA entity (em.find)
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)                         //  Uses database for (increment) primary key generation
    @jakarta.persistence.Column(name    = "parent_id",                                                                  //  Assigns column-name: parent_id (for parent table)
                                nullable= false,                                                                        //  Enforces that the database entry MUST NEVER be null
                                unique  = true)                                                                         //  Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.io.Serializable id;

    @lombok.Setter
    @jakarta.persistence.ManyToOne(fetch    = jakarta.persistence.FetchType.LAZY)                                       //  Owning-side: this entity holds the foreign key to GrandParentEntity. LAZY to avoid automatic loading
    @jakarta.persistence.JoinColumn(name    = "grandparent_id",                                                         //  Ties this Field to a column named grandparent_id
                                    nullable= false)                                                                    //  Enforces that the database entry MUST NEVER be null
    private GrandParentEntity grandParent;                                                                           //  Foreign Key

    @jakarta.persistence.OneToMany( mappedBy= "parent",                                                                 //  Non-owning-side: mappedBy refers to the *field name* 'parent' in ChildEntity (owning side)
                                    fetch   =   jakarta.persistence.FetchType.LAZY,                                     //  Lazy: children are loaded only when childSet is accessed
                                    cascade = { jakarta.persistence.CascadeType.MERGE,                                  //  Allows EntityManager operation merge from Parent -> Child
                                                jakarta.persistence.CascadeType.PERSIST,                                //  Allows EntityManager operation persist from Parent -> Child
                                                jakarta.persistence.CascadeType.REMOVE})                                //  Allows EntityManager operation remove from Parent -> Child
    private java.util.Set<ChildEntity> childSet = new java.util.HashSet<>();                                         // Collection of ChildEntity rows that reference this Parent

    @lombok.Setter                                                                                                      // Creates a set-method for this Field
    @jakarta.persistence.Column(name        = "field1",                                                                 // Assigns column-name: field1
            nullable    = false,                                                                                        // Enforces that the database entry MUST NEVER be null
            unique      = true)                                                                                         // Enforces that the database entry MUST NEVER be identical to an existing entry
    private java.lang.Object field1;

    protected ParentEntity(){}                                                                                          //  Requirement from JPA
    // I am using ? (wildcard) to enforce that GrandparentEntity, ParentEntity and ChildEntity,
    // use the same datatype for ID id
    public ParentEntity(GrandParentEntity parentToParent,
                        java.util.Set<ChildEntity>    childrenToParent)
    {
        this.grandParent = parentToParent;
        this.childSet    = childrenToParent;
    }

    public T3N3T.Factory.Information.Entity.ChildEntity
    addChild(T3N3T.Factory.Information.Entity.ChildEntity entity)
            throws T3N3T.Factory.Exception.EntityException
    {
        if ( entity == null ){
            throw new T3N3T.Factory.Exception.EntityException(T3N3T.Factory.Exception.ErrorType.NOT_ACCEPTABLE,
                    "parameter (ChildEntity) is NOT allowed to be null");
        }
        entity.setParent(this);
        this.childSet.add(entity);
        return entity;
    }

    public GrandParentEntity
    toGrandParent()
            throws T3N3T.Factory.Exception.DTOException
    {
        java.util.Set<ParentEntity> childToGrandParent = new java.util.HashSet<>();
        childToGrandParent.add(this);
        return new GrandParentEntity(childToGrandParent);
    }
}
