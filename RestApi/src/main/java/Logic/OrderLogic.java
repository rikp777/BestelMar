package Logic;

import Data.Repository.ArticleOrderRepository;
import Data.Repository.OrderRepository;
import Data.Repository.TableRepository;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.Status;

import java.util.List;

public class OrderLogic {
    private ArticleOrderRepository _articleOrderRepository;
    private TableRepository _tableRepository;
    private OrderRepository _orderRepository;
    public OrderLogic() {
        this._orderRepository = new OrderRepository();
        this._tableRepository = new TableRepository();
        this._articleOrderRepository = new ArticleOrderRepository();
    }
    public boolean pay(IOrder entity){
        return _orderRepository.pay(entity);
    }

    public boolean add(IOrder entity) {
        boolean success = false;
        IOrder order = _orderRepository.getLastBy(entity.getTable());

        if(order == null || order.getStatus() == Status.Paid){
            System.out.println("First order or table has been paid: " + entity.getTable().getName());

            success = _orderRepository.add(entity);

            //we dont know the id of the created order so we need to get that in order to create an articleorder
            order = _orderRepository.getLastBy(entity.getTable());

            for(IArticleOrder articleOrder : entity.getArticleOrder()){
                System.out.println("Making adding order " + articleOrder.getArticle().getName());
                success = _articleOrderRepository.add(articleOrder, order);
            }

            return success;

        }else{
            System.out.println("Order not paid yet " + entity.getTable().getName());
            for(IArticleOrder articleOrder : entity.getArticleOrder()){
                System.out.println("Making adding order " + articleOrder.getArticle().getName());
                success = _articleOrderRepository.add(articleOrder, order);
            }
            return success;
        }
    }
    public boolean edit(IOrder entity) {
        return _orderRepository.edit(entity);
    }
    public boolean remove(IOrder entity) {
        return _orderRepository.remove(entity);
    }


    public IOrder getLastBy(ITable table) {
        IOrder order = _orderRepository.getLastBy(table);
        if(order != null){
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
            return order;
        }
        return null;
    }
    public IOrder getLastBy(IUser user) {
        IOrder order = _orderRepository.getLastBy(user);
        order.setTable(_tableRepository.getBy(order.getTable().getId()));
        order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));

        return order;
    }
    public IOrder getBy(IOrder order) {
        IOrder orderr = _orderRepository.getBy(order);
        order.setTable(_tableRepository.getBy(order.getTable().getId()));
        order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));

        return orderr;
    }



    public List<IOrder> getAll() {
        List<IOrder> orders = _orderRepository.getAll();
        for (IOrder order : orders) {
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
        }
        for (IOrder order : orders) {
            System.out.println("size " + order.getArticleOrder().size());
        }
        return orders;
    }
    public List<IOrder> getAll(IUser user) {
        List<IOrder> orders = _orderRepository.getAll(user);
        for (IOrder order : orders) {
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
        }
        return orders;
    }
    public List<IOrder> getLast(){
        List<IOrder> orders = _orderRepository.getLast();
        for (IOrder order : orders) {
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            System.out.println("get last article Orders from " + order.getId());
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
        }

        return orders;
    }
}
