package Data.Context.MySQLContext;

import Data.Context.Interfaces.IRightContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IRightDao;
import Data.DTO.RightDto;
import Data.DTO.UserDto;
import Interfaces.model.IRight;
import Interfaces.model.IUser;

import java.util.List;

public class JDBIRightContextSQL extends SQLConnector implements IRightContext {
    @Override
    public IRight read(int id) {
        if(id != 0){
            //return jdbi().withExtension(IRightDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IRight read(String name) {
        if(name != null){
            //return jdbi().withExtension(IRightDao.class, dao -> dao.read(name));
        }
        return null;
    }

    @Override
    public List<IRight> list(IUser user) {
        if(user != null){
            //return jdbi().withExtension(IRightDao.class, dao -> dao.list(user));
        }
        return null;
    }

    @Override
    public List<IRight> list() {
        return null; //jdbi().withExtension(IRightDao.class, dao -> dao.list());
    }
}
