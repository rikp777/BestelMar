package Data.Context.MySQLContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IArticleDao;
import Data.Context.MySQLContext.dao.IArticleOrderDao;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;

import java.util.List;

public class JDBIArticleOrderContextSQL extends SQLConnector implements IArticleOrderContext {
    @Override
    public List<IArticleOrder> list(int orderId) {
        if(orderId != 0){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.list(orderId));
        }
        return null;
    }

    @Override
    public boolean create(IArticleOrder entity, IOrder order) {
        if(entity != null && order != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean create(IArticleOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(IArticleOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(IArticleOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public IArticleOrder read(int id) {
        if(id != 0){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IArticleOrder read(IArticleOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<IArticleOrder> list() {
        return jdbi().withExtension(IArticleOrderDao.class, dao -> dao.list());
    }
}
