package Logic;

import Data.Repository.ArticleRepository;
import Data.Repository.UserRepository;
import Interfaces.model.IArticle;
import Interfaces.model.IUser;

import java.util.List;

public class ArticleLogic {
    private ArticleRepository _articleRepository;
    public ArticleLogic() {
        this._articleRepository = new ArticleRepository();
    }


    public boolean add(IArticle entity) {
        return _articleRepository.add(entity);
    }
    public boolean edit(IArticle entity) {
        return _articleRepository.edit(entity);
    }
    public boolean remove(IArticle entity) {
        return _articleRepository.remove(entity);
    }



    public IArticle getBy(int id) {
        return _articleRepository.getBy(id);
    }
    public IArticle getBy(IArticle entity) {
        return _articleRepository.getBy(entity);
    }
    public IArticle getBy(String name) {
        return _articleRepository.getBy(name);
    }



    public List<IArticle> getAll() {
        return _articleRepository.getAll();
    }
}
