package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.ITableContext;
import Data.DTO.TableDto;
import Interfaces.model.IArticle;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(TableDto.class)
public interface ITableDao extends ITableContext {
    @Override
    @SqlQuery("SELECT * FROM tables WHERE name = ? LIMIT 1")
    TableDto read(@Bind("name") String name);

    @Override
    @SqlQuery("INSERT INTO tables (name, description)" +
            "VALUES (:name, :description) ")
    boolean create(@BindBean TableDto entity);

    @Override
    @SqlQuery("UPDATE tables SET name = :name, description = :description, disabled = :disabled WHERE id = :id")
    boolean update(@BindBean TableDto entity);

    @Override
    @SqlQuery("UPDATE tables SET disabled = :disabled WHERE id = :id")
    boolean delete(@BindBean TableDto entity);

    @Override
    @SqlQuery("SELECT * FROM tables WHERE id = ? LIMIT 1")
    TableDto read(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT * FROM tables WHERE id = :id LIMIT 1")
    TableDto read(@BindBean TableDto entity);

    @Override
    @SqlQuery("SELECT * FROM tables")
    List<TableDto> list();
}
