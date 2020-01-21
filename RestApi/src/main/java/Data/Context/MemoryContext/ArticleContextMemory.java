package Data.Context.MemoryContext;

import Data.Context.Interfaces.IArticleContext;
import Data.DTO.ArticleDto;
import Data.DTO.UserDto;
import Interfaces.model.IArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticleContextMemory implements IArticleContext {
    private static List<IArticle> articles;

    public ArticleContextMemory(){
        articles = new ArrayList<>();
    }


    public boolean create(IArticle entity) {
        for(IArticle a : articles){
            if(a.getName().equals(entity.getName())){
                return false;
            }
        }

        ArticleDto article = new ArticleDto(
                generateId(entity),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );

        articles.add(article);
        return articles.contains(article);
    }
    public boolean update(IArticle entity){
        ArticleDto article = new ArticleDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );

        IArticle old;

        for(IArticle a : articles){
            if(a.getId() == entity.getId()){
                old = a;
                articles.set(articles.indexOf(old), article);
            }
        }
        return articles.contains(article);
    }
    public boolean delete(IArticle entity){
        IArticle old;
        for(IArticle a : articles){
            if(a.getId() == entity.getId()){
                old = a;
                articles.remove(old);
                return !articles.contains(old);
            }
        }
        return false;
    }


    public IArticle read(int id) {
        IArticle article = null;
        for(IArticle a : articles){
            if(a.getId() == id){
                article = a;
            }
        }
        return article;
    }
    public IArticle read(String name) {
        IArticle article = null;
        for(IArticle a : articles){
            if(a.getName() == name){
                article = a;
            }
        }
        return article;
    }
    public IArticle read(IArticle entity) {
        IArticle article = null;
        for(IArticle a : articles){
            if(a.getId() == entity.getId()){
                article = a;
            }
        }
        return article;
    }

    public List<IArticle> list() {
        return new ArrayList<>(articles);
    }

    public int generateId(IArticle entity){
        int id;
        if(entity.getId() == 0){
            if(articles.size() == 0){
                id = 1;
            }else{
                id = list().get(list().size() -1).getId() + 1;
            }
        }else{
            id = entity.getId();
        }
        return id;
    }
}
