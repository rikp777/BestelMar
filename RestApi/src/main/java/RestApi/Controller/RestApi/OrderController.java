package RestApi.Controller.RestApi;

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import Logic.Models.ArticleOrder;
import Logic.Models.Order;
import Logic.OrderLogic;
import Logic.TableLogic;
import Logic.UserLogic;
import RestApi.VOModels.VOArticle;
import RestApi.VOModels.VOOrder;
import RestApi.VOModels.VOOrderCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController { ;
    private OrderLogic orderLogic = new OrderLogic();
    private UserLogic userLogic = new UserLogic();
    private TableLogic tableLogic = new TableLogic();


//    @GetMapping("/")
//    public ResponseEntity createOrder(@RequestBody VOOrderCreate payload) {
//        System.out.println(payload.getTable().getName());
//        //Gson gson = new Gson();
//        //System.out.println(gson.toJson(payload));
//        //System.out.println(payload.get("table"));
//        //System.out.println(payload.getTable().getName());
//
//        //System.out.println(order.getTable().getName());
//        //orderLogic.add(articleOrder)
////        if(articleLogic.getBy(article.getName()) != null){
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article Already exists");
////        }
////        if(articleLogic.add(article)){
////            return ResponseEntity.status(HttpStatus.CREATED).body(article);
////        }
////        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
//        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
//    }

    //    @PutMapping("/{id}")
//    public ResponseEntity update(@RequestBody VOArticleOrder article) {
////        if(articleLogic.getBy(article.getName()) != null){
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article Already exists");
////        }
////        if(articleLogic.edit(article)){
////            return ResponseEntity.status(HttpStatus.CREATED).body(article);
////        }
////        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
//        return null;
//    }
//
//    @DeleteMapping("/article/{id}")
//    public ResponseEntity delete(@PathVariable int id) {
////        System.out.println(id);
////        System.out.println(articleLogic.getBy(id));
////        if(articleLogic.getBy(id).getName() != null) {
////            articleLogic.remove(articleLogic.getBy(id));
////            return ResponseEntity.status(HttpStatus.OK).body("Article deleted Successfully");
////        }
////        return ResponseEntity.status(HttpStatus.CONFLICT).body("Article not found");
//        return null;
//    }
//

    @PostMapping("/order")
    public ResponseEntity create(@RequestBody VOOrderCreate voOrderCreate){
        IOrder order = new Order();
        order.setTable(voOrderCreate.getTable());

        List<IArticleOrder> articleOrders = new ArrayList<>();
        for (VOArticle article: voOrderCreate.getArticles()) {
            IArticleOrder articleOrder = new ArticleOrder();

            articleOrder.setArticle(article);
            articleOrder.setPrice(article.getPrice());
            articleOrder.setComment(article.getComment());

            articleOrders.add(articleOrder);
        }
        order.setDate(new Date());
        order.setArticleOrder(articleOrders);

        if(orderLogic.add(order)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    orderLogic.getLastBy(voOrderCreate.getTable())
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }


    @PostMapping("/order/table/{tableId}/pay")
    public ResponseEntity payTableOrder(@PathVariable int tableId){
        ITable table = tableLogic.getBy(tableId);
        IOrder order = orderLogic.getLastBy(table);
        System.out.println(order.getId());
        if(orderLogic.pay(order)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    "Order has been payed for table" + table.getName()
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }


    @GetMapping("/order/last")
    public ResponseEntity readLast() {
        List<IOrder> orders = orderLogic.getLast();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                orders
        );
    }
    @GetMapping("/order")
    public ResponseEntity read() {
        List<IOrder> orders = orderLogic.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                orders
        );
    }
    @GetMapping("/order/user/{userID}")
    public ResponseEntity readLastByUser(@PathVariable int userID) {
        IUser user = userLogic.getBy(userID);
        IOrder order = orderLogic.getLastBy(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                order
        );
    }
    @GetMapping("/order/table/{tableId}")
    public ResponseEntity readLastByTable(@PathVariable int tableId) {
        ITable table = tableLogic.getBy(tableId);
        IOrder order = orderLogic.getLastBy(table);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                order
        );
    }

//    public VOOrder setResource(IOrder order, String tableLink, String articleOrderLink){
//        VOOrder voOrder = new VOOrder();
//
//        voOrder.setId(order.getId());
//        voOrder.setDate(order.getDate());
//
//        voOrder.setTableLink(tableLink);
//        voOrder.setArticleOrderLink(articleOrderLink);
//
//        return voOrder;
//    }
}

