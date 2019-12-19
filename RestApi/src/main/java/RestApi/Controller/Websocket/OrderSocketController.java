package RestApi.Controller.Websocket;

import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Logic.Models.ArticleOrder;
import Logic.Models.Order;
import Logic.OrderLogic;
import RestApi.VOModels.VOArticle;
import RestApi.VOModels.VOOrderCreate;
import models.Article;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderSocketController {
    private OrderLogic orderLogic = new OrderLogic();

    @SendTo("/global/orderweb")
    @MessageMapping("/orderweb")
    public IOrder orderCreate(VOOrderCreate voOrder){
        IOrder order = new Order();
        order.setTable(voOrder.getTable());

        List<IArticleOrder> articleOrders = new ArrayList<>();
        for (VOArticle article: voOrder.getArticles()) {
            IArticleOrder articleOrder = new ArticleOrder();

            articleOrder.setArticle(article);
            articleOrder.setPrice(article.getPrice());
            articleOrder.setComment(article.getComment());

            articleOrders.add(articleOrder);
        }

        order.setDate(new Date());

        order.setArticleOrder(articleOrders);

        System.out.println("tafeltje: "+ order.getTable().getId());

        orderLogic.add(order);

        return order;
    }

    @SendTo("/global/orderwebStatus/table/{id}")
    @MessageMapping("/orderweb/table/{id}")
    public IOrder orderStatus(VOOrderCreate voOrder) {
        IOrder order = new Order();
        order.setTable(voOrder.getTable());

        List<IArticleOrder> articleOrders = new ArrayList<>();
        for (VOArticle article: voOrder.getArticles()) {
            IArticleOrder articleOrder = new ArticleOrder();

            articleOrder.setArticle(article);
            articleOrder.setPrice(article.getPrice());
            articleOrder.setComment(article.getComment());

            articleOrders.add(articleOrder);
        }

        order.setDate(new Date());

        order.setArticleOrder(articleOrders);

        System.out.println("tafeltje: "+ order.getTable().getId());

        //orderLogic.add(order);

        return order;
    }
}
