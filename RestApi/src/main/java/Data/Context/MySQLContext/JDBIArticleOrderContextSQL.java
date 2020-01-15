package Data.Context.MySQLContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IArticleDao;
import Data.Context.MySQLContext.dao.IArticleOrderDao;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Interfaces.model.IArticleOrder;
import models.ArticleOrder;
import models.Status;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;

public class JDBIArticleOrderContextSQL extends SQLConnector implements IArticleOrderContext {
    @Override
    public List<ArticleOrderDto> list(int orderId) {

        RowMapper<ArticleOrderDto> articleOrderMapper = (rs, ctx) -> {
            System.out.println(rs.getInt("id"));

            ArticleOrderDto articleorder =  new ArticleOrderDto(
                    rs.getInt("id"),
                    rs.getDouble("price"),
                    rs.getString("comment")
            );
            articleorder.setArticle(jdbi().withExtension(IArticleDao.class, dao -> dao.read(rs.getInt("article_id"))));
            articleorder.setStatus(Status.valueOf(rs.getString("status_name")));


            return articleorder;
        };
        List<ArticleOrderDto> articleOrders = jdbi().withHandle(handle -> {
            return handle.createQuery("SELECT article_order.*, status.name as status_name, articles.name, articles.description\n" +
                    "FROM article_order  \n" +
                    "INNER JOIN status on status.id = article_order.status_id \n" +
                    "INNER JOIN articles on articles.id = article_order.article_id\n" +
                    "WHERE article_order.order_id = :orderId \n" +
                    "ORDER BY article_order.id DESC")
                    .bind("orderId", orderId)
                    .map(articleOrderMapper)
                    .list();
        });
        return articleOrders;
    }

    @Override
    public boolean create(ArticleOrderDto entity, OrderDto order) {
        if(entity != null && order != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean create(ArticleOrderDto entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(ArticleOrderDto entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(ArticleOrderDto entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public ArticleOrderDto read(int id) {
        if(id != 0){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public ArticleOrderDto read(ArticleOrderDto entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<ArticleOrderDto> list() {
        return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.list());
    }
}
