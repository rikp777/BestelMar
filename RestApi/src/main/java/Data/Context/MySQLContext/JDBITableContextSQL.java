package Data.Context.MySQLContext;

import Data.Context.Interfaces.ITableContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IArticleDao;
import Data.Context.MySQLContext.dao.ITableDao;
import Data.DTO.ArticleDto;
import Data.DTO.TableDto;
import Interfaces.model.ITable;

import java.util.List;

public class JDBITableContextSQL extends SQLConnector implements ITableContext {
    @Override
    public TableDto read(String name) {
        if(name != null){
            return jdbi().withExtension(ITableDao.class, dao -> dao.read(name));
        }
        return null;
    }

    @Override
    public boolean create(TableDto entity) {
        if(entity != null){
            return jdbi().withExtension(ITableDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(TableDto entity) {
        if(entity.getId() != 0){
            return jdbi().withExtension(ITableDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(TableDto entity) {
        if(entity.getId() != 0){
            return jdbi().withExtension(ITableDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public TableDto read(int id) {
        if(id != 0){
            return jdbi().withExtension(ITableDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public TableDto read(TableDto entity) {
        if(entity != null){
            return jdbi().withExtension(ITableDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<TableDto> list() {
        return jdbi().withExtension(ITableDao.class, dao -> dao.list());
    }
}
