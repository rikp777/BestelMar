package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IArticleContext;
import Data.DTO.ArticleDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(ArticleDto.class)
public interface IArticleDao extends IArticleContext {
    @Override
    @SqlQuery("SELECT * FROM articles WHERE name = ? LIMIT 1")
    ArticleDto read(@Bind("name") String name);

    @Override
    @SqlQuery("INSERT INTO articles (name, description, price) VALUES (:name, :description, :price) ")
    boolean create(@BindBean ArticleDto entity);

    @Override
    @SqlQuery("UPDATE articles SET name = :name, description = :description, price = :price WHERE id = :id")
    boolean update(@BindBean ArticleDto entity);

    @Override
    @SqlQuery("DELETE FROM activity WHERE id = :id")
    boolean delete(@BindBean ArticleDto entity);

    @Override
    @SqlQuery("SELECT * FROM articles WHERE id = ? LIMIT 1")
    @RegisterBeanMapper(ArticleDto.class)
    ArticleDto read(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT * FROM articles WHERE id = :id LIMIT 1")
    ArticleDto read(@BindBean ArticleDto entity);

    @Override
    @SqlQuery("SELECT * FROM articles")
    List<ArticleDto> list();
}
