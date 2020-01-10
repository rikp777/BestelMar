package logic;


import Data.Context.MemoryContext.OrderContextMemory;
import Data.DTO.ArticleDto;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.Repository.OrderRepository;
import Factory.Factory;
import Factory.ContextType;
import Interfaces.model.*;
import logic.Interfaces.IOrderLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderLogicTest {

    private IOrderLogic _orderLogic;
    private String authUser;
    private List<OrderDto> orders = new ArrayList<>();
    private List<IArticleOrder> articleOrders = new ArrayList<>();
    private List<IArticle> articles = new ArrayList<>();
    private List<ITable> tables = new ArrayList<>();

    private void initialiseData(){
        tables.add(new TableDto(1, "tafel 1", "Links achter in de hoek bij de kast", true));
        tables.add(new TableDto(2, "tafel 2", "rechts achter", true));

        articles.add(new ArticleDto(1, "Fanta", "lekker", 2.10));
        articles.add(new ArticleDto(2, "Cassis", "lekker", 2.10));

        ArticleOrderDto articleOrderDtoOne = new ArticleOrderDto(1, 1.20, "geen ijs");
        articleOrderDtoOne.setArticle(articles.get(0));

        ArticleOrderDto articleOrderDtoTwo = new ArticleOrderDto(2, 1.50, "geen ijs");
        articleOrderDtoTwo.setArticle(articles.get(0));

        articleOrders.add(articleOrderDtoOne);
        articleOrders.add(articleOrderDtoTwo);

        orders.add(new OrderDto(1, new Date(), tables.get(0), articleOrders));
        orders.add(new OrderDto(2, new Date(), tables.get(1), articleOrders));
    }

    @BeforeEach
    void setUp(){
        _orderLogic = Factory.OrderLogic(ContextType.MEMORY);
        authUser = "rikpeeters@hotmail.com";

        initialiseData();
    }

    @Test
    void add(){
        IOrder order = orders.get(0);

        _orderLogic.add(order);

        int expected = 1;
        int actual = _orderLogic.getAll().size();
        assertEquals(expected, actual);

        IOrder orderD = _orderLogic.getAll().get(0);

        assertEquals(order, orderD);
    }

    @Test
    void edit(){

    }

    @Test
    void remove(){

    }

    @Test
    void pay(){

    }

}
