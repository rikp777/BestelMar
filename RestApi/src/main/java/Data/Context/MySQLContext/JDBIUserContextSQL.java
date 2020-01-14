package Data.Context.MySQLContext;

import Data.Context.Interfaces.IUserContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.ITableDao;
import Data.Context.MySQLContext.dao.IUserDao;
import Interfaces.model.IUser;

import java.util.List;

public class JDBIUserContextSQL extends SQLConnector implements IUserContext {
    @Override
    public boolean auth(String email, String password) {
        if(email != null && password != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.auth(email, password));
        }
        return false;
    }

    @Override
    public IUser read(String email) {
        if(email != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.read(email));
        }
        return null;
    }

    @Override
    public boolean create(IUser entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(IUser entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(IUser entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public IUser read(int id) {
        if(id != 0){
            return jdbi().withExtension(IUserDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IUser read(IUser entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<IUser> list() {
        return jdbi().withExtension(IUserDao.class, dao -> dao.list());
    }
}
