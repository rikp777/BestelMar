package model;
import Data.DTO.ArticleDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ArticleTest {
    private static List<ArticleDto> articles = new ArrayList<>();

    @BeforeAll
    static void setUp(){
        articles.add(new ArticleDto(1, "Fanta", "lekker", 2.10));
        articles.add(new ArticleDto(2, "Cassis", "lekker", 2.10));
        articles.add(new ArticleDto(3, "cola", "lekker", 2.10));
    }

    @Test
    void getName(){
        ArticleDto article = articles.get(0);
        assertEquals("Fanta", article.getName());
    }
    @Test
    void getDescription(){
        ArticleDto article = articles.get(0);
        assertEquals("lekker", article.getDescription());
    }
    @Test
    void getPrice(){
        ArticleDto article = articles.get(0);
        assertEquals(2.10, article.getPrice());
    }



}
