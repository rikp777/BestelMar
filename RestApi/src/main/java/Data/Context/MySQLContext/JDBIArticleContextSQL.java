package Data.Context.MySQLContext;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IArticleDao;
import Data.DTO.ArticleDto;
import Interfaces.model.IArticle;

import java.util.List;

public class JDBIArticleContextSQL extends SQLConnector implements IArticleContext {

    @Override
    public IArticle read(String name) {
        if(name != null){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.read(name));
        }
        return null;
    }

    @Override
    public boolean create(IArticle entity) {
        jdbi().open();
        if(entity != null){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.create((ArticleDto) entity));
        }
        return false;
    }

    @Override
    public boolean update(IArticle entity) {
        if(entity.getId() != 0){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.update((ArticleDto) entity));
        }
        return false;
    }

    @Override
    public boolean delete(IArticle entity) {
        if(entity.getId() != 0){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.create((ArticleDto) entity));
        }
        return false;
    }

    @Override
    public IArticle read(int id) {
        if(id != 0){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IArticle read(IArticle entity) {
        if(entity != null){
            return jdbi().withExtension(IArticleDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<IArticle> list() {
        return jdbi().withExtension(IArticleDao.class, dao -> dao.list());
    }
}
