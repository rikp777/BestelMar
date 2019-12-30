package RestApi.Controller.Websocket;


import Data.Context.MemoryContext.TableContextMemory;
import Data.Repository.TableRepository;
import Factory.Factory;
import Interfaces.model.IOrder;
import logic.Interfaces.IOrderLogic;
import logic.OrderLogic;
import logic.TableLogic;
import RestApi.VOModels.VOOrder;
import RestApi.VOModels.VOTable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class OrderSocketController {
    private IOrderLogic orderLogic = Factory.OrderLogic();
    private TableLogic tableLogic = new TableLogic(new TableRepository(new TableContextMemory()));

    @SendTo("/global/orderweb")
    @MessageMapping("/orderweb")
    public IOrder order(VOTable voTable){
        System.out.println("Websocket table received " + voTable.getName());
        return orderLogic.getLastBy(voTable);
    }

    @SendTo("/global/orderweb/table/{id}")
    @MessageMapping("/orderweb/table/{id}")
    public VOOrder orderTable(VOOrder order) {
        System.out.println("Table update: " + order.getTable().getName());
        return order;

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
    }
}
