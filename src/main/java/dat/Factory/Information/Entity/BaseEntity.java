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
                "parameter (id) can NOT be null. Since the method adds the id to a this.childIdSet (addChild(Serializable))");
        childIdSet.add(id);
        return id;
    }

    public void removeChild(BaseEntity child)
            throws dat.Factory.Exception.EntityException
    {
        for (java.io.Serializable id : this.childIdSet)
        {
            if (child.getId() == id) this.childIdSet.remove(id);
        }
        throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND, "Could not find a data-entry with the same id as child parameter (removeChild(BaseEntity))");
    }

    public void removeChild(java.io.Serializable _id)
        throws dat.Factory.Exception.EntityException
    {
        for (java.io.Serializable id : this.childIdSet)
        {
            if (_id == id) this.childIdSet.remove(id);
        }
        throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND, "Could not find a data-entry with the same id as child parameter (removeChild(Serializable))");
    }

//TODO=======================================[Parent}===================================================================
    public java.io.Serializable addParent(BaseEntity parent)
            throws dat.Factory.Exception.EntityException
    {
        if (parent.getId() == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "Id Field in parameter (BaseEntity parent) can NOT be null. Since the method retrieves BaseEntity.getId() and adds it to this.childIdSet (addParent(BaseEntity))");
        childIdSet.add(parent.getId());
        return parent.getId();
    }

    public java.io.Serializable addParent(java.io.Serializable id)
    {
        if (id == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "parameter (id) can NOT be null. Since the method adds the id to a this.parentIdSet (addParent(Serializable))");
        parentIdSet.add(id);
        return id;
    }

    public void removeParent(BaseEntity parent)
    {
        for (java.io.Serializable id : this.parentIdSet)
        {
            if (parent.getId() == id) this.parentIdSet.remove(id);
        }
        throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                "Could not find a data-entry with the same id as parent parameter (removeParent(BaseEntity))");
    }

    public void removeParent(java.io.Serializable _id)
    {
        {
            for (java.io.Serializable id : this.parentIdSet)
            {
                if (_id == id) this.parentIdSet.remove(id);
            }
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND, "Could not find a data-entry with the same id as child parameter (removeParent(Serializable))");
        }
    }

//TODO=======================================[GrandParent}==============================================================
    public java.io.Serializable addGrandParent(BaseEntity grandParent)
    {
        if (grandParent.getId() == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "Id Field in parameter (BaseEntity parent) can NOT be null. Since the method retrieves BaseEntity.getId() and adds it to this.childIdSet (addGrandParent(BaseEntity))");
        childIdSet.add(grandParent.getId());
        return grandParent.getId();
    }

    public java.io.Serializable addGrandParent(java.io.Serializable id)
    {
        if (id == null) throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.FORBIDDEN,
                "parameter (id) can NOT be null. Since the method adds the id to a this.parentIdSet (addGrandParent(Serializable))");
        grandParentIdSet.add(id);
        return id;
    }

    public void removeGrandParent(BaseEntity grandParent)
    {
        for (java.io.Serializable id : this.grandParentIdSet)
        {
            if (grandParent.getId() == id) this.grandParentIdSet.remove(id);
        }
        throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                "Could not find a data-entry with the same id as parent parameter (removeGrandParent(BaseEntity))");
    }

    public void removeGrandParent(java.io.Serializable _id)
    {
        {
            for (java.io.Serializable id : this.grandParentIdSet)
            {
                if (_id == id) this.grandParentIdSet.remove(id);
            }
            throw new dat.Factory.Exception.EntityException(dat.Factory.Exception.ErrorType.NOT_FOUND,
                    "Could not find a data-entry with the same id as child parameter (removeGrandParent(Serializable))");
        }
    }
}