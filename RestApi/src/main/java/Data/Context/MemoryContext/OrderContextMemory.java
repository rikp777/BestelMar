package Data.Context.MemoryContext;

import Data.Context.Interfaces.IOrderContext;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
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
    public boolean create(OrderDto entity, UserDto user) {
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

    public boolean create(OrderDto entity) {
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
    public boolean update(OrderDto entity){
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
    public boolean delete(OrderDto entity){
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


    public OrderDto read(int id) {
        OrderDto order = null;
        for(OrderDto o : orders){
            if(o.getId() == id){
                order = o;
            }
        }
        return order;
    }

    public OrderDto read(OrderDto entity) {
        OrderDto order = null;
        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                order = o;
            }
        }
        return order;
    }

    public List<OrderDto> list() {
        return new ArrayList<>(orders);
    }

    public boolean pay(OrderDto entity) {
        OrderDto order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );
        order.setStatus(Status.Paid);
        //order.setTable(new TableContextMemory().read(entity.getTable()));
        order.setArticleOrder(new ArrayList<>(new ArticleOrderContextMemory().list(entity.getId())));

        OrderDto old;

        for(OrderDto o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.set(orders.indexOf(old), order);
            }
        }
        return orders.contains(order);
    }

    public OrderDto readLast(UserDto user) {
        return null;
    }


    public OrderDto readLast(TableDto table) {
        return null;
    }

    @Override
    public List<OrderDto> list(UserDto user) {
        return null;
    }

    @Override
    public List<OrderDto> listLast() {
        return null;
    }
}
