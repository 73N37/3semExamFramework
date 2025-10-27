package dat.Factory.Information.DTO;


public class BaseDTO {
    protected java.io.Serializable id;
    java.io.Serializable getId() {
        return id;
    }

//TODO================================================[Child]===========================================================
    protected dat.Factory.Information.Entity.ChildEntity childEntity;
    protected dat.Factory.Information.Entity.ChildEntity getChildEntity() { return this.childEntity; }

    protected dat.Factory.Information.DTO.ChildDTO childDTO;
    protected dat.Factory.Information.DTO.ChildDTO getChildDTO() { return this.childDTO; }

    protected java.io.Serializable childId;
    java.io.Serializable getChildID() { return this.childId; }

    protected java.util.Set<dat.Factory.Information.DTO.ChildDTO> childSet;

//TODO================================================[Parent]==========================================================
    protected dat.Factory.Information.Entity.ParentEntity parentEntity;
    protected dat.Factory.Information.Entity.ParentEntity getParentEntity() { return this.parentEntity; }

    protected dat.Factory.Information.DTO.ParentDTO parentDTO;
    protected dat.Factory.Information.DTO.ParentDTO getParent() { return this.parentDTO; }

    protected java.io.Serializable parentId;
    java.io.Serializable getParentId() { return this.parentId; }

    protected java.util.Set<dat.Factory.Information.DTO.ParentDTO> parentSetDTO;

//TODO================================================[GrandParent]=====================================================
    protected dat.Factory.Information.Entity.GrandParentEntity grandParentEntity;
    protected dat.Factory.Information.Entity.GrandParentEntity getGrandParentEntity() { return this.grandParentEntity; }

    protected dat.Factory.Information.DTO.GrandParentDTO grandParentDTO;
    protected dat.Factory.Information.DTO.GrandParentDTO getGrandParentDTO() { return this.grandParentDTO; }

    protected java.io.Serializable grandParentId;
    java.io.Serializable getGrandParentId() { return this.grandParentId; }



    // ensure collections are never null
    protected BaseDTO() {
        this.childSet       = new java.util.HashSet<>();
        this.parentSetDTO   = new java.util.HashSet<>();
    }

    // ID-only constructor
    public BaseDTO(java.io.Serializable id) {
        this();
        this.id = id;
    }

    // childId + parentId
    public BaseDTO(java.io.Serializable childId, java.io.Serializable parentId) {
        this();
        this.childId = childId;
        this.parentId = parentId;
    }

    // childId + parentId + grandParentId
    public BaseDTO(java.io.Serializable childId, java.io.Serializable parentId, java.io.Serializable grandParentId) {
        this();
        this.childId = childId;
        this.parentId = parentId;
        this.grandParentId = grandParentId;
    }

    // ChildEntity
    public BaseDTO(dat.Factory.Information.Entity.ChildEntity entity) {
        this();
        if (entity == null) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "parameter (entity) is NOT allowed to be null");
        }
        this.childEntity = entity;
        this.id = entity.getId();
        this.childId = entity.getId();
        if (entity.getParent() != null) {
            this.parentId = entity.getParent().getId();
            if (entity.getParent().getGrandParent() != null) {
                this.grandParentId = entity.getParent().getGrandParent().getId();
            }
        }
    }

    // From ParentEntity - map children safely
    public BaseDTO(dat.Factory.Information.Entity.ParentEntity entity) {
        this();
        if (entity                  == null) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "parameter (entity) is NOT allowed to be null");
        }
        this.parentEntity           = entity;
        this.id                     = entity.getId();
        this.parentId               = entity.getId();
        if (entity.getChildSet()    != null) {
            this.childSet           = entity.getChildSet()
                    .stream()
                    .filter(java.util.Objects::nonNull)
                    .map(dat.Factory.Information.DTO.ChildDTO::new)
                    .collect(java.util.stream.Collectors.toSet());
        }
        if (entity.getGrandParent() != null) {
            this.grandParentId = entity.getGrandParent().getId();
        }
    }

    // From GrandParentEntity - map parents safely
    public BaseDTO(dat.Factory.Information.Entity.GrandParentEntity entity) {
        this();
        if (entity == null) {
            throw new dat.Factory.Exception.DTOException(dat.Factory.Exception.ErrorType.BAD_REQUEST,
                    "parameter (entity) is NOT allowed to be null");
        }
        this.grandParentEntity = entity;
        this.id = entity.getId();
        this.grandParentId = entity.getId();
        if (entity.getParentSet() != null) {
            this.parentSetDTO = entity.getParentSet()
                    .stream()
                    .filter(java.util.Objects::nonNull)
                    .map(dat.Factory.Information.DTO.ParentDTO::new)
                    .collect(java.util.stream.Collectors.toSet());
        }
    }
}