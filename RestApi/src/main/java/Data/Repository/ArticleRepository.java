package Data.Repository;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.IUserContext;
import Data.Context.SQLContext.ArticleContextSQL;
import Data.Context.SQLContext.UserContextSQL;
import Data.DTO.ArticleDto;
import Data.DTO.ArticleOrderDto;
import Data.Repository.Interfaces.IArticleRepository;
import Interfaces.model.IArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository implements IArticleRepository {
    private IArticleContext _articleContext;
    public ArticleRepository(IArticleContext _articleContext){
        this._articleContext = _articleContext;
    }


    public boolean add(IArticle entity) {
        return _articleContext.create(entity);
    }
    public boolean edit(IArticle entity) {
        return _articleContext.update(entity);
    }
    public boolean remove(IArticle entity) {
        return _articleContext.delete(entity);
    }



    public IArticle getBy(int id) {
        return _articleContext.read(id);
    }
    public IArticle getBy(IArticle entity) {
        return _articleContext.read(entity);
    }
    public IArticle getBy(String name) {
        return _articleContext.read(name);
    }


    public List<IArticle> getAll() {
        return new ArrayList<>(_articleContext.list());
    }
}
