package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.ITableContext;
import Interfaces.model.IArticle;
import Interfaces.model.ITable;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface ITableDao extends ITableContext {
    @Override
    @SqlQuery("SELECT * FROM tables WHERE name = ? LIMIT 1")
    ITable read(@Bind("name") String name);

    @Override
    @SqlQuery("INSERT INTO tables (name, description)" +
            "VALUES (:name, :description) ")
    boolean create(@BindBean ITable entity);

    @Override
    @SqlQuery("UPDATE tables SET name = :name, description = :description, disabled = :disabled WHERE id = :id")
    boolean update(ITable entity);

    @Override
    @SqlQuery("UPDATE tables SET disabled = :disabled WHERE id = :id")
    boolean delete(ITable entity);

    @Override
    @SqlQuery("SELECT * FROM tables WHERE id = ? LIMIT 1")
    ITable read(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT * FROM tables WHERE id = :id LIMIT 1")
    ITable read(ITable entity);

    @Override
    @SqlQuery("SELECT * FROM tables")
    List<ITable> list();
}
