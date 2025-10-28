package dat.Factory.Operation.Route;


public abstract class
Abstract extends dat.Factory.Factory
{
    protected Abstract(){super();}

    private final dat.Factory.Operation.Controller.Abstract controller = new dat.Factory.Operation.Controller.Implementation.Controller().getController();

    protected  io.javalin.apibuilder.EndpointGroup getRoutes()
    {
        return () -> {
            io.javalin.apibuilder.ApiBuilder.post("/", controller::put, dat.security.enums.Role.USER);
            io.javalin.apibuilder.ApiBuilder.get("/", controller::getAll);
            io.javalin.apibuilder.ApiBuilder.get("/{id}", controller::get);
            io.javalin.apibuilder.ApiBuilder.put("/",controller::put);
            io.javalin.apibuilder.ApiBuilder.delete("/{id}", controller::delete);
        };
    }

    public class
    Route
    {
        private final dat.Factory.Operation.Route.Abstract routes = dat.Factory.Factory.getRoute();
        public io.javalin.apibuilder.EndpointGroup
        getRoutes()
        {
            String path = "/"+routes.getClass().getSimpleName()+"s";
            return () -> {
                io.javalin.apibuilder.ApiBuilder.path(path, routes.getRoutes());
            };
        }


    }
}
