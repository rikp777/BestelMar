package Data.Context.MySQLContext;

import Data.Context.Interfaces.IUserContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IRightDao;
import Data.Context.MySQLContext.dao.ITableDao;
import Data.Context.MySQLContext.dao.IUserDao;
import Data.DTO.RightDto;
import Data.DTO.UserDto;
import Interfaces.model.IRight;
import Interfaces.model.IUser;

import java.util.ArrayList;
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
    public UserDto read(String email) {
        if(email != null){
            UserDto user = jdbi().withExtension(IUserDao.class, dao -> dao.read(email));

            List<RightDto> rights = jdbi().withExtension(IRightDao.class, dao -> dao.list(user));
            user.setRights(new ArrayList<>(rights));
            return user;
        }
        return null;
    }

    @Override
    public boolean create(UserDto entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(UserDto entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(UserDto entity) {
        if(entity != null){
            return jdbi().withExtension(IUserDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public UserDto read(int id) {
        if(id != 0){
            UserDto user =  jdbi().withExtension(IUserDao.class, dao -> dao.read(id));

            List<RightDto> rights = jdbi().withExtension(IRightDao.class, dao -> dao.list(user));
            user.setRights(new ArrayList<>(rights));
            return user;
        }
        return null;
    }

    @Override
    public UserDto read(UserDto entity) {
        if(entity != null){
            UserDto user = jdbi().withExtension(IUserDao.class, dao -> dao.read(entity));

            List<RightDto> rights = jdbi().withExtension(IRightDao.class, dao -> dao.list(user));
            user.setRights(new ArrayList<>(rights));
            return user;
        }
        return null;
    }

    @Override
    public List<UserDto> list() {
        return jdbi().withExtension(IUserDao.class, dao -> dao.list());
    }
}
