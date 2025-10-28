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


//TODO=======================================[Child}====================================================================
    public java.io.Serializable addChild(BaseEntity child)
            throws dat.Factory.Exception.EntityException
    {
        if (child.getId() == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "Id Field in parameter (BaseEntity child) can NOT be null. Since the method retrieves BaseEntity.getId() and adds it to this.childIdSet (addChild(BaseEntity))");
        childIdSet.add(child.getId());
        return child.getId();
    }

    public java.io.Serializable addChild(java.io.Serializable id)
            throws dat.Factory.Exception.EntityException
    {
        if (id == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "parameter (id) can NOT be null. Since the method adds the id to a this.childIdSet");
        childIdSet.add(id);
        return id;
    }

    public void removeChild(BaseEntity child)
            throws dat.Factory.Exception.EntityException
    {
        
    }

    public void removeChild(java.io.Serializable id)
        throws dat.Factory.Exception.EntityException
    {

    }

//TODO=======================================[Parent}===================================================================
    public java.io.Serializable addParent(BaseEntity parent)
            throws dat.Factory.Exception.EntityException
    {

    }

    public java.io.Serializable addParent(java.io.Serializable id)
    {

    }

    public void removeParent(BaseEntity parent)
    {

    }

    public void removeParent(java.io.Serializable id)
    {

    }

//TODO=======================================[GrandParent}==============================================================
    public java.io.Serializable addGrandParent(BaseEntity grandParent)
    {

    }

    public java.io.Serializable addGrandParent(java.io.Serializable id)
    {

    }

    public void removeGrandParent(BaseEntity grandParent)
    {

    }

    public void removeGrandParent(java.io.Serializable id)
    {

    }
}