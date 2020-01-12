package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IRightContext;
import Interfaces.model.IRight;
import Interfaces.model.IUser;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface IRightDao extends IRightContext {
    @Override
    @SqlQuery("SELECT * FROM rights WHERE name = :id")
    IRight read(int id);

    @Override
    @SqlQuery("SELECT * FROM rights WHERE name = :name")
    IRight read(String name);

    @Override
    @SqlQuery("SELECT * FROM right_user JOIN rights on rights.id = right_user.right_id WHERE user_id = :id")
    List<IRight> list(IUser user);

    @Override
    @SqlQuery("SELECT * FROM rights")
    List<IRight> list();
}
