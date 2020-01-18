package logic;

import Data.Context.MemoryContext.TableContextMemory;
import Data.Repository.ArticleOrderRepository;
import Data.Repository.Interfaces.IArticleOrderRepository;
import Data.Repository.Interfaces.IArticleRepository;
import Data.Repository.Interfaces.IOrderRepository;
import Data.Repository.Interfaces.ITableRepository;
import Data.Repository.OrderRepository;
import Data.Repository.TableRepository;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import logic.Interfaces.IOrderLogic;
import models.ArticleOrder;
import models.Status;

import java.util.List;

public class OrderLogic implements IOrderLogic {
    private IArticleOrderRepository _articleOrderRepository;
    private ITableRepository _tableRepository;
    private IOrderRepository _orderRepository;
    public OrderLogic(IOrderRepository orderRepository, ITableRepository tableRepository, IArticleOrderRepository articleOrderRepository) {
        this._orderRepository = orderRepository;
        this._tableRepository = tableRepository;
        this._articleOrderRepository = articleOrderRepository;
    }
    public boolean pay(IOrder entity){
        return _orderRepository.pay(entity);
    }
    public boolean getPaid(ITable table){
        IOrder lastOrder = this.getLastBy(table);
        if(lastOrder.getStatus() == Status.Paid){
            return true;
        }
        return false;
    }

    public boolean add(IOrder entity) {
        boolean success = false;
        ITable table = _tableRepository.getBy(entity.getTable().getId());
        IOrder order = _orderRepository.getLastBy(table);

        if(order == null || order.getStatus() == Status.Paid){
            System.out.println("First order or table has been paid: " + table.getName());

            success = _orderRepository.add(entity);

            //we dont know the id of the created order so we need to get that in order to create an articleorder
            order = _orderRepository.getLastBy(table);

            for(IArticleOrder articleOrder : entity.getArticleOrder()){
                System.out.println("Adding articleOrder " + articleOrder.getArticle().getName() + " to order");
                success = _articleOrderRepository.add(articleOrder, order);
            }

            return success;

        }else{
            System.out.println("Order not paid yet " + table.getName());
            for(IArticleOrder articleOrder : entity.getArticleOrder()){
                System.out.println("Making adding order " + articleOrder.getArticle().getName());
                success = _articleOrderRepository.add(articleOrder, order);
            }
            return success;
        }
    }
    public boolean editArticleOrder(List<IArticleOrder> articleOrders){
        boolean success = false;
        for (IArticleOrder articleOrder : articleOrders){
            success = _articleOrderRepository.edit(articleOrder);
        }
        return success;
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
    public IOrder getBy(int id){
        return _orderRepository.getBy(id);
    }


    public List<IOrder> getAll() {
        List<IOrder> orders = _orderRepository.getAll();
        for (IOrder order : orders) {
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
        }
        for (IOrder order : orders) {
            if(order.getArticleOrder() != null){
                System.out.println("size " + order.getArticleOrder().size());
            }
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
    public List<IOrder> getAllLast(){
        List<IOrder> orders = _orderRepository.getLast();

        for (IOrder order : orders) {
            //System.out.println("Table " + order.toString());
            order.setTable(_tableRepository.getBy(order.getTable().getId()));
            //System.out.println("get last article Orders from " + order.getId());
            order.setArticleOrder(_articleOrderRepository.getAll(order.getId()));
        }

        return orders;
    }
}
