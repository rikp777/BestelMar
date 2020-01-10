package Data.Context.MySQLContext;

import Data.Context.Interfaces.IArticleContext;
import Interfaces.model.IArticle;

import java.util.List;

public class ArticleContextSQL implements IArticleContext {
    @Override
    public IArticle read(String name) {
        return null;
    }

    @Override
    public boolean create(IArticle entity) {
        return false;
    }

    @Override
    public boolean update(IArticle entity) {
        return false;
    }

    @Override
    public boolean delete(IArticle entity) {
        return false;
    }

    @Override
    public IArticle read(int id) {
        return null;
    }

    @Override
    public IArticle read(IArticle entity) {
        return null;
    }

    @Override
    public List<IArticle> list() {
        return null;
    }
}
