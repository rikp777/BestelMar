package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IArticleContext;
import Data.DTO.ArticleDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(ArticleDto.class)
public interface IArticleDao{

    @SqlQuery("SELECT * FROM articles WHERE name = ? LIMIT 1")
    ArticleDto read(@Bind("name") String name);


    @SqlQuery("INSERT INTO articles (name, description, price) VALUES (:name, :description, :price) ")
    boolean create(@BindBean ArticleDto entity);


    @SqlQuery("UPDATE articles SET name = :name, description = :description, price = :price WHERE id = :id")
    boolean update(@BindBean ArticleDto entity);


    @SqlQuery("DELETE FROM activity WHERE id = :id")
    boolean delete(@BindBean ArticleDto entity);


    @SqlQuery("SELECT * FROM articles WHERE id = ? LIMIT 1")
    @RegisterBeanMapper(ArticleDto.class)
    ArticleDto read(@Bind("id") int id);


    @SqlQuery("SELECT * FROM articles WHERE id = :id LIMIT 1")
    ArticleDto read(@BindBean ArticleDto entity);


    @SqlQuery("SELECT * FROM articles")
    List<ArticleDto> list();
}
