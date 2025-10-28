package dat.Factory.Operation;

public abstract class Operation
{
    @lombok.Getter
    protected static jakarta.persistence.EntityManagerFactory                               emf;

    @lombok.Getter
    protected static dat.Factory.Information.BaseMapper                                     mapper;

    @lombok.Getter
    protected static dat.Factory.Information.Entity.BaseEntity                              entity;

    @lombok.Getter
    protected static java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>   entityClass;

    @lombok.Getter
    protected static dat.Factory.Information.DTO.BaseDTO                                    dto;

    @lombok.Getter
    protected static java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>         dtoClass;

    @lombok.Getter
    protected static java.io.Serializable                                                   id;

    @lombok.Getter
    protected static java.lang.Class<? extends java.io.Serializable>                        idClass;

    // Root-ctor
    protected Operation
    ()
    {
        this.emf = dat.config.HibernateConfig.getEntityManagerFactory();
        this.mapper = dat.Factory.Information.BaseMapper.getInstance();
    }




    // Instance-ctor
    protected Operation
    (
            @org.jetbrains.annotations.NotNull
            dat.Factory.Information.Entity.BaseEntity    entity,

            @org.jetbrains.annotations.NotNull
            dat.Factory.Information.DTO.BaseDTO          dto
    )
    {
        this(); // calls Root-ctor

        this.entity = entity;
        this.entityClass = entity.getClass();

        this.dto = dto;
        this.dtoClass = dto.getClass();

        this.id = entity.getId();
        this.idClass = id.getClass();
    }




    // Entity-instance-ctor
    protected Operation
    (
            @org.jetbrains.annotations.NotNull
            dat.Factory.Information.Entity.BaseEntity   entity
    )
    {
        this
                (
                        entity,                                                         // calls Instance-ctor
                        dat.Factory.Information.BaseMapper.toBaseDTO(entity)
        );
    }





    //DTO-instance-ctor
    protected Operation
    (
            @org.jetbrains.annotations.NotNull
            dat.Factory.Information.DTO.BaseDTO dto
    )
    {
        this
                (
                        dat.Factory.Information.BaseMapper.toBaseEntity(dto),           // calls Instance-ctor
                        dto
                );
    }





    // Class-ctor
    protected Operation
    (
            @org.jetbrains.annotations.NotNull
            java.lang.Class<? extends dat.Factory.Information.Entity.BaseEntity>    entityClass,

            @org.jetbrains.annotations.NotNull
            java.lang.Class<? extends dat.Factory.Information.DTO.BaseDTO>          dtoClass
    )
    {
        this
                (
                        mapper.instantiateEntityClass(entityClass),                     //  calls Instance-ctor
                        mapper.instantiateDtoClass(dtoClass)
                );

    }
}
