package dat.Factory.Operation.Controller;


public abstract class
Abstract extends dat.Factory.Factory
{
    protected Abstract() {super();}

    public Abstract
            (
                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.Entity.BaseEntity    entityClass,

                    @org.jetbrains.annotations.NotNull
                    dat.Factory.Information.DTO.BaseDTO          dtoClass
            )
    {
        super(entityClass.getClass(), dtoClass.getClass());
    }

    /*      Uses JSON (from end-point [Route]) to call get-method (read) from DAO*/
    public void
    get
    (
            @org.jetbrains.annotations.NotNull
            io.javalin.http.Context ctx
    )
    {
        java.io.Serializable id = ctx.pathParamAsClass("id", java.io.Serializable.class).check(this::validatePrimaryKey, "Not a valid id").get();
        dat.Factory.Information.DTO.BaseDTO dto = new dat.Factory.Operation.DAO.Implementation.DAO.Access().get(id);
        ctx.res().setStatus(200);
        ctx.json(dto, getDtoClass());
    }

    /*      Uses JSON (from end-point [Route]) to call getAll-method (readAll) from DAO*/
    public void
    getAll
    (
            @org.jetbrains.annotations.NotNull
            io.javalin.http.Context ctx
    )
    {
        java.util.Set<dat.Factory.Information.DTO.BaseDTO> data = new dat.Factory.Operation.DAO.Implementation.DAO.Access().getAllEntities();
        ctx.res().setStatus(200);
        ctx.json(data, getDtoClass());
    }

    /*      Uses JSON (from end-point [Route]) to call put-method (create) from DAO*/
    public void
    put
    (
            @org.jetbrains.annotations.NotNull
            io.javalin.http.Context ctx
    )
    {
        dat.Factory.Information.DTO.BaseDTO jsonRequest = ctx.bodyAsClass(getDtoClass());
        dat.Factory.Information.DTO.BaseDTO dto = new dat.Factory.Operation.DAO.Implementation.DAO.Access().put(mapper.toBaseEntity(jsonRequest));
        ctx.res().setStatus(200);
        ctx.json(dto, getDtoClass());
    }

    /*      Uses JSON (from end-point [Route]) to call patch-method (update) from DAO*/
    protected void
    patch
    (
            @org.jetbrains.annotations.NotNull
            io.javalin.http.Context ctx
    )
    {
        java.io.Serializable id = ctx.pathParamAsClass("id", java.io.Serializable.class).check(this::validatePrimaryKey, "Not a valid id").get();
        dat.Factory.Information.DTO.BaseDTO dto = new dat.Factory.Operation.DAO.Implementation.DAO.Access().patch(validateEntity(ctx), id);
        ctx.res().setStatus(200);
        ctx.json(dto,getDtoClass());
    }

    public void
    delete
            (
                    @org.jetbrains.annotations.NotNull
                    io.javalin.http.Context ctx
            )
    {
        java.io.Serializable id = ctx.pathParamAsClass("id", getIdClass()).check(this::validatePrimaryKey, "Not a valid id").get();
        new dat.Factory.Operation.DAO.Implementation.DAO.Access().deleteId(id);
    }

    /*      Verifies that a primary-key exists in the database*/
    protected boolean
    validatePrimaryKey
    (
            @org.jetbrains.annotations.NotNull
            java.io.Serializable id
    )
    {
        return new dat.Factory.Operation.DAO.Implementation.DAO.Access().validatePrimaryKey(id);
    }

    /*      Uses JSON (from end-point [Route]) to return DTO to back-end (database),
    *       returns DTO if validation was true otherwise returns */
    protected dat.Factory.Information.Entity.BaseEntity
    validateEntity
    (
            @org.jetbrains.annotations.NotNull
            io.javalin.http.Context ctx
    )
    {
        return ctx.bodyValidator(getEntityClass()).get();
    }
}
