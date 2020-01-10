package Data.Context.MemoryContext;

import Data.Context.Interfaces.IOrderContext;
import Data.DTO.OrderDto;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.Status;

import java.util.ArrayList;
import java.util.List;

public class OrderContextMemory implements IOrderContext {
    private static List<OrderDto> orders;

    public OrderContextMemory(){
        orders = new ArrayList<>();
    }
    public boolean create(IOrder entity, IUser user) {
        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                return false;
            }
        }

        OrderDto order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );

        orders.add(order);
        return orders.contains(order);
    }

    public boolean create(IOrder entity) {
        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                return false;
            }
        }

        OrderDto order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );

        orders.add(order);
        return orders.contains(order);
    }
    public boolean update(IOrder entity){
        OrderDto order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );

        OrderDto old;

        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.set(orders.indexOf(old), order);
            }
        }
        return orders.contains(order);
    }
    public boolean delete(IOrder entity){
        OrderDto old;
        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.remove(old);
                return !orders.contains(old);
            }
        }
        return false;
    }


    public IOrder read(int id) {
        OrderDto order = null;
        for(OrderDto o : orders){
            if(o.getId() == id){
                order = o;
            }
        }
        return order;
    }

    public IOrder read(IOrder entity) {
        OrderDto order = null;
        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                order = o;
            }
        }
        return order;
    }

    public List<IOrder> list() {
        return new ArrayList<>(orders);
    }

    public boolean pay(IOrder entity) {
        OrderDto order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );
        order.setStatus(Status.Paid);
        order.setTable(new TableContextMemory().read(entity.getTable()));
        order.setArticleOrder(new ArticleOrderContextMemory().list(entity.getId()));

        OrderDto old;

        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.set(orders.indexOf(old), order);
            }
        }
        return orders.contains(order);
    }

    public IOrder readLast(IUser user) {
        return null;
    }


    public IOrder readLast(ITable table) {
        return null;
    }

    @Override
    public List<IOrder> list(IUser user) {
        return null;
    }

    @Override
    public List<IOrder> listLast() {
        return null;
    }
}
