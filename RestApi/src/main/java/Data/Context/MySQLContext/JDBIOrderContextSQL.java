package Data.Context.MySQLContext;

import Data.Context.Interfaces.IOrderContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IOrderDao;
import Data.Context.MySQLContext.dao.ITableDao;
import Data.Context.MySQLContext.dao.IUserDao;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

import java.util.List;

public class JDBIOrderContextSQL extends SQLConnector implements IOrderContext {
    @Override
    public boolean pay(IOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.pay(entity));
        }
        return false;
    }

    @Override
    public boolean create(IOrder entity, IUser user) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.create(entity, user));
        }
        return false;
    }

    @Override
    public IOrder readLast(IUser user) {
        if(user != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.readLast(user));
        }
        return null;
    }

    @Override
    public IOrder readLast(ITable table) {
        if(table != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.readLast(table));
        }
        return null;
    }

    @Override
    public List<IOrder> list(IUser user) {
        if(user != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.list(user));
        }
        return null;
    }

    @Override
    public List<IOrder> listLast() {
        return jdbi().withExtension(IOrderDao.class, dao -> dao.listLast());
    }

    @Override
    public boolean create(IOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(IOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(IOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public IOrder read(int id) {
        if(id != 0){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IOrder read(IOrder entity) {
        if(entity != null){
            return jdbi().withExtension(IOrderDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<IOrder> list() {
        return jdbi().withExtension(IOrderDao.class, dao -> dao.list());
    }
}
