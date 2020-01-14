package Data.Context.MySQLContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IArticleOrderDao;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;

import java.util.List;

public class JDBIArticleOrderContextSQL extends SQLConnector implements IArticleOrderContext {
    @Override
    public List<ArticleOrderDto> list(int orderId) {
        if(orderId != 0){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.list(orderId));
        }
        return null;
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
