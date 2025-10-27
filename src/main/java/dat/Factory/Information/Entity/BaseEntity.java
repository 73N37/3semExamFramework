package dat.Factory.Information.Entity;

public class BaseEntity
{
    protected java.io.Serializable id;
    java.io.Serializable getId(){
        return id;
    }

    //TODO================================================[Child]===========================================================
    protected dat.Factory.Information.Entity.ChildEntity childEntity;
    protected dat.Factory.Information.Entity.ChildEntity getChildEntity() { return this.childEntity; }

    protected dat.Factory.Information.DTO.ChildDTO childDTO;
    protected dat.Factory.Information.DTO.ChildDTO getChildDTO() { return this.childDTO; }

    protected java.io.Serializable childId;
    java.io.Serializable getChildID() { return this.childId; }

    protected java.util.Set<dat.Factory.Information.Entity.ChildEntity> childSet;





    protected java.io.Serializable parentId;
    java.io.Serializable getParentId(){return this.parentId;}

    protected java.io.Serializable grandParentId;
    java.io.Serializable getGrandParentId(){return this.grandParentId;}
}
