package dat.Factory.Controller;


public interface Interface <DTO extends dat.Factory.Information.DTO.BaseDTO,
                            Entity  extends dat.Factory.Information.Entity.BaseEntity,
                            ID  extends java.io.Serializable>
{
    /*      Uses JSON (from end-point [Route]) to call get-method (read) from DAO*/
    void    get(io.javalin.http.Context ctx);

    /*      Uses JSON (from end-point [Route]) to call getAll-method (readAll) from DAO*/
    void    getAll(io.javalin.http.Context ctx);

    /*      Uses JSON (from end-point [Route]) to call put-method (create) from DAO*/
    void    put(io.javalin.http.Context ctx);

    /*      Uses JSON (from end-point [Route]) to call patch-method (update) from DAO*/
    void    patch(io.javalin.http.Context ctx);

    /*      Verifies that a primary-key exists in the database*/
    boolean validatePrimaryKey(ID id);                                                                                  //

    /*      Uses JSON (from end-point [Route]) to return DTO to back-end (database),
    *       returns DTO if validation was true otherwise returns */
    DTO  validateEntity(io.javalin.http.Context ctx);//  Can return any entity that extends from BaseEntity.
}
