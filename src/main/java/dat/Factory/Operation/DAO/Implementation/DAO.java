package dat.Factory.Operation.DAO.Implementation;


public class DAO
        extends dat.Factory.Operation.DAO.Abstract
{
    private final static org.slf4j.Logger                                                   log = org.slf4j.LoggerFactory.getLogger(DAO.class);

    public DAO
            (
//                    @org.jetbrains.annotations.NotNull
//                    dat.Factory.Information.Entity.BaseEntity    entityClass,
//
//                    @org.jetbrains.annotations.NotNull
//                    dat.Factory.Information.DTO.BaseDTO          dtoClass
            )
    {
        super(getEntity(), getDto());
        log.debug("Instantiated dat.Factory.Operation.DAO.Implementation.DAO");
    }
}
