package RestApi.Controller.Websocket;


import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Logic.Models.ArticleOrder;
import Logic.Models.Order;
import Logic.OrderLogic;
import RestApi.VOModels.VOArticle;
import RestApi.VOModels.VOOrderCreate;
import RestApi.VOModels.VOTable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderSocketController {
    private OrderLogic orderLogic = new OrderLogic();

    @SendTo("/global/orderweb")
    @MessageMapping("/orderweb")
    public IOrder orderCreate(VOTable voTable){
        System.out.println("Websocket table received " + voTable.getName());
        return orderLogic.getLastBy(voTable);
    }

//    @SendTo("/global/orderweb/table/{id}")
//    @MessageMapping("/orderweb/table/{id}")
//    public IOrder order(VOOrderCreate voOrder) {
//        IOrder order = new Order();
//        order.setTable(voOrder.getTable());
//
//        List<IArticleOrder> articleOrders = new ArrayList<>();
//        for (VOArticle article: voOrder.getArticles()) {
//            IArticleOrder articleOrder = new ArticleOrder();
//
//            articleOrder.setArticle(article);
//            articleOrder.setPrice(article.getPrice());
//            articleOrder.setComment(article.getComment());
//
//            articleOrders.add(articleOrder);
//        }
//
//        order.setDate(new Date());
//        order.setArticleOrder(articleOrders);
//        orderLogic.add(order);
//
//        System.out.println("tafeltje: "+ order.getTable().getId());
//
//        return orderLogic.getLastBy(voOrder.getTable());
//    }
}
