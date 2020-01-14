package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface IArticleOrderDao extends IArticleOrderContext {
    @Override
    @SqlQuery("SELECT ao.*, s.name as status_name " +
            "FROM article_order as ao " +
            "INNER JOIN status as s on s.id = ao.status_id " +
            "WHERE ao.order_id = :orderId " +
            "ORDER BY ao.id DESC ")
    List<ArticleOrderDto> list(@Bind("orderId") int orderId);

    @Override
    @SqlQuery("INSERT INTO article_order (article_id, order_id, price, comment, date)" +
            "VALUES (articleOrder.article.id, order.id, articleOrder.price, articleOrder.comment, NOW()) ")
    boolean create(@BindBean("articleOrder") ArticleOrderDto entity, @BindBean("order") OrderDto order);

    @Override
    boolean create(@BindBean ArticleOrderDto entity);

    @Override
    @SqlQuery("UPDATE article_order SET price = :price, comment = :comment WHERE id = :id")
    boolean update(@BindBean ArticleOrderDto entity);

    @Override
    @SqlQuery("DELETE FROM order_article WHERE id = :id")
    boolean delete(@BindBean ArticleOrderDto entity);

    @Override
    @SqlQuery("SELECT ao.*, s.name as status_name " +
            "FROM article_order as ao " +
            "INNER JOIN status as s on s.id = ao.status_id " +
            "WHERE ao.id = :id " +
            "LIMIT 1")
    ArticleOrderDto read(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT ao.*, s.name as status_name " +
            "FROM article_order as ao " +
            "INNER JOIN status as s on s.id = ao.status_id " +
            "WHERE ao.id = :id " +
            "LIMIT 1")
    ArticleOrderDto read(@BindBean ArticleOrderDto entity);

    @Override
    @SqlQuery("SELECT article_order.id, article_order.price, article_order.comment, article_order.date, article_order.article_id, article_order.status_id, article_order.order_id, status.name as status_name, articles.name as article_name, articles.description as article_description, articles.price as article_price " +
            "FROM article_order " +
            "JOIN status on status.id = article_order.status_id " +
            "JOIN articles on articles.id = article_order.article_id")
    List<ArticleOrderDto> list();
}
