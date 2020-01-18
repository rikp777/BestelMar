package RestApi.Controller.RestApi;

import Data.Context.MemoryContext.TableContextMemory;
import Data.Context.MemoryContext.UserContextMemory;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Data.Repository.TableRepository;
import Data.Repository.UserRepository;
import Factory.Factory;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import RestApi.VOModels.*;
import logic.Interfaces.IOrderLogic;
import logic.Interfaces.ITableLogic;
import logic.Interfaces.IUserLogic;
import logic.OrderLogic;
import logic.TableLogic;
import logic.UserLogic;
import models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController { ;
    private IOrderLogic orderLogic = Factory.OrderLogic();
    private IUserLogic userLogic = Factory.UserLogic();
    private ITableLogic tableLogic = Factory.TableLogic();


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
    @PutMapping("/order/{id}")
    public ResponseEntity updateArticleOrder(@RequestBody VOOrder voOrder){
        List<IArticleOrder> articleOrders = new ArrayList<>();
        for (VOArticleOrder voArticleOrder : voOrder.getArticleOrder()){
            IArticleOrder articleOrder = new ArticleOrderDto();
            articleOrder.setId(voArticleOrder.getId());
            articleOrder.setStatus(voArticleOrder.getStatus());
            articleOrder.setDate(voArticleOrder.getDate());
            articleOrder.setComment(voArticleOrder.getComment());
            articleOrder.setStatus(voArticleOrder.getStatus());
            articleOrders.add(articleOrder);
        }

        if(orderLogic.editArticleOrder(articleOrders)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(articleOrders);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ja niet goed he");
        //return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }
    @PostMapping("/order")
    public ResponseEntity create(@RequestBody VOOrderCreate voOrderCreate){
        IOrder order = new OrderDto();
        order.setTable(voOrderCreate.getTable());

        List<IArticleOrder> articleOrders = new ArrayList<>();
        for (VOArticle article: voOrderCreate.getArticles()) {
            IArticleOrder articleOrder = new ArticleOrderDto();

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
        List<IOrder> orders = orderLogic.getAllLast();
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
    @GetMapping("/order/table/{tableId}/paid")
    public ResponseEntity checkPaidOrder(@PathVariable int tableId){
        ITable table = tableLogic.getBy(tableId);
        boolean check = orderLogic.getPaid(table);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                check
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

