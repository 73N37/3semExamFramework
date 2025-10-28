package dat.Factory;

@lombok.Getter
public abstract class Operation
{
    protected static jakarta.persistence.EntityManagerFactory                               emf;
    protected static dat.Factory.Information.Entity.BaseEntity                              entity;
    protected static java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>   entityClass;
    protected static dat.Factory.Information.DTO.BaseDTO                                    dto;
    protected static java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>         dtoClass;
    protected static java.io.Serializable                                                   id;
    protected static java.lang.Class<? extends java.io.Serializable>                        idClass;

    protected Operation()
    {
        this.emf = dat.config.HibernateConfig.getEntityManagerFactory();
    }

    protected Operation(dat.Factory.Information.Entity.BaseEntity    entity,
                        dat.Factory.Information.DTO.BaseDTO          dto,
                        java.io.Serializable                         id)
    {
        this.emf = dat.config.HibernateConfig.getEntityManagerFactory();

        this.entity = entity;
        this.entityClass = entity.getClass();

        this.dto = dto;
        this.dtoClass = dto.getClass();

        this.id = id;
        this.idClass = id.getClass();
    }

    protected Operation(dat.Factory.Information.Entity.BaseEntity    entity)
    {
        //this.emf = dat.config.HibernateConfig.getEntityManagerFactory();
        this(entity, dat.Factory.Information.BaseMapper.toBaseDTO(entity), id);
    }

    protected Operation(dat.Factory.Information.DTO.BaseDTO         dto)
    {
        this.emf = dat.config.HibernateConfig.getEntityManagerFactory();
        this.id = dto.getId();
        this.entity = dat.Factory.Information.BaseMapper.toBaseEntity(dto);
    }


    protected Operation(java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>    entityClass,
                        java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>          dtoClass,
                        java.lang.Class<? extends java.io.Serializable>                         idClass)
    {
        this.emf = dat.config.HibernateConfig.getEntityManagerFactory();
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.idClass = idClass;
    }

}
