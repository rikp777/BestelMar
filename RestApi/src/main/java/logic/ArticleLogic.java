package logic;

import Data.Repository.ArticleRepository;
import Data.Repository.Interfaces.IArticleRepository;
import Data.Repository.UserRepository;
import Interfaces.model.IArticle;
import logic.Interfaces.IArticleLogic;

import java.util.List;

public class ArticleLogic implements IArticleLogic {
    private IArticleRepository _articleRepository;

    public ArticleLogic(IArticleRepository articleRepository) {
        this._articleRepository = articleRepository;
    }


    public boolean add(IArticle entity, String authUser) {
        if(!UserLogic.checkRight(authUser, Right.Admin) || UserLogic.checkRight(authUser, Right.Employee)) return false;


        if(_articleRepository.getBy(entity.getName()) != null) return false;
        if(!validation(entity)) return false;


        return _articleRepository.add(entity);
    }
    public boolean edit(IArticle entity, String authUser) {
        if(!(UserLogic.checkRight(authUser, Right.Admin) || UserLogic.checkRight(authUser, Right.Employee))) return false;

        System.out.println("Raak");
        if(!validation(entity)) return false;


        return _articleRepository.edit(entity);
    }
    public boolean remove(IArticle entity, String authUser) {
        if(!UserLogic.checkRight(authUser, Right.Admin) || UserLogic.checkRight(authUser, Right.Employee)) return false;


        if(_articleRepository.getBy(entity) == null) return false;


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


    public boolean validation(IArticle article){

        if(article.getName() == null) return false;
        if(article.getName().length() > 50) return false;
        if(article.getDescription().length() > 300) return false;

        return true;
    }




    public boolean add(IArticle entity) {
        return false;
    }
    public boolean edit(IArticle entity) {
        return false;
    }
    public boolean remove(IArticle entity) {
        return false;
    }
}
