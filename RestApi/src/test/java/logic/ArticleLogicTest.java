package logic;

import Data.Context.MemoryContext.ArticleContextMemory;
import Data.DTO.ArticleDto;
import Data.Repository.ArticleRepository;
import Factory.*;
import Interfaces.model.IArticle;
import logic.Interfaces.IArticleLogic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleLogicTest {

    private IArticleLogic _articleLogic;
    private String authUser;
    private List<ArticleDto> articles = new ArrayList<>();

    @BeforeEach
    void setUp(){
        _articleLogic = Factory.ArticleLogic(ContextType.MYSQL);
        authUser = "rikpeeters@hotmail.com";
        articles.add(new ArticleDto(1, "Fanta", "lekker", 2.10));
        articles.add(new ArticleDto(2, "Cassis", "lekker", 2.10));
    }

    @Test
    void add(){
        IArticle article = articles.get(0);


        _articleLogic.add(article, authUser);

        int expected = 1;
        int actual = _articleLogic.getAll().size();
        assertEquals(expected, actual);

        IArticle articleD = _articleLogic.getAll().get(0);

        assertEquals(articleD.getName(), article.getName());
        assertEquals(articleD.getId(), article.getId());
        assertEquals(articleD.getPrice(), article.getPrice());
        assertEquals(articleD.getDescription(), article.getDescription());
    }
    @Test
    void edit(){
        IArticle articleNew = articles.get(0);
        IArticle articleUpdated = new ArticleDto(1, "sprite", "", 2.10);

        _articleLogic.add(articleNew);
        _articleLogic.edit(articleUpdated);

        int expected = 1;
        int actual = _articleLogic.getAll().size();
        assertEquals(expected, actual);

        IArticle insertedArticle = _articleLogic.getAll().get(0);

        assertEquals(insertedArticle.getName(), articleUpdated.getName());
        assertEquals(insertedArticle.getId(), articleUpdated.getId());
        assertEquals(insertedArticle.getPrice(), articleUpdated.getPrice());
        assertEquals(insertedArticle.getDescription(), articleUpdated.getDescription());
    }
    @Test
    void delete(){
        IArticle articleNew = articles.get(0);

        _articleLogic.add(articleNew);

        int expected = 1;
        int actual = _articleLogic.getAll().size();
        assertEquals(expected, actual);

        _articleLogic.remove(articleNew);

        expected = 0;
        actual = _articleLogic.getAll().size();
        assertEquals(expected, actual);
    }


    @Test
    void getById(){
        IArticle articleNew = articles.get(0);
        //_articleLogic.add(articleNew);

        //IArticle article = _articleLogic.getBy(articleNew.getId());
        IArticle article = _articleLogic.getBy(13);

        assertEquals(article.getId(), articleNew.getId());
        assertEquals(article.getName(), articleNew.getName());
        assertEquals(article.getDescription(), articleNew.getDescription());
        assertEquals(article.getPrice(), articleNew.getPrice());
    }

    @Test
    void getByName(){
        IArticle articleNew = articles.get(0);
        _articleLogic.add(articleNew);

        IArticle article = _articleLogic.getBy(articleNew.getName());

        assertEquals(article.getId(), articleNew.getId());
        assertEquals(article.getName(), articleNew.getName());
        assertEquals(article.getDescription(), articleNew.getDescription());
        assertEquals(article.getPrice(), articleNew.getPrice());
    }


    @Test
    void getAll(){
        _articleLogic.add(articles.get(0));
        _articleLogic.add(articles.get(1));

        int expected = 2;
        int actual = _articleLogic.getAll().size();

        assertEquals(expected, actual);
    }
}
