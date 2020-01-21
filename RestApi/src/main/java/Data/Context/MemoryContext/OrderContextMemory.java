package Data.Context.MemoryContext;

import Data.Context.Interfaces.IOrderContext;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.Status;

import java.util.ArrayList;
import java.util.List;

public class OrderContextMemory implements IOrderContext {
    private static List<IOrder> orders;

    public OrderContextMemory(){
        orders = new ArrayList<>();
    }
    public boolean create(IOrder entity, IUser user) {
        for(IOrder o : orders){
            if(o.getId() == entity.getId()){
                return false;
            }
        }

        OrderDto order = new OrderDto(
                generateId(entity),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );

        orders.add(order);
        return orders.contains(order);
    }

    public boolean create(IOrder entity) {
        for(IOrder o : orders){
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

        IOrder old;

        for(IOrder o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.set(orders.indexOf(old), order);
            }
        }
        return orders.contains(order);
    }
    public boolean delete(IOrder entity){
        IOrder old;
        for(IOrder o : orders){
            if(o.getId() == entity.getId()){
                old = o;
                orders.remove(old);
                return !orders.contains(old);
            }
        }
        return false;
    }


    public IOrder read(int id) {
        IOrder order = null;
        for(IOrder o : orders){
            if(o.getId() == id){
                order = o;
            }
        }
        return order;
    }

    public IOrder read(IOrder entity) {
        IOrder order = null;
        for(IOrder o : orders){
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
        IOrder order = new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTable(),
                entity.getArticleOrder()
        );
        order.setStatus(Status.Paid);
        //order.setTable(new TableContextMemory().read(entity.getTable()));
        order.setArticleOrder(new ArrayList<>(new ArticleOrderContextMemory().list(entity.getId())));

        IOrder old;

        for(IOrder o : orders){
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


    public List<IOrder> list(IUser user) {
        return null;
    }


    public List<IOrder> listLast() {
        return null;
    }


    public int generateId(IOrder entity){
        int id;
        if(entity.getId() == 0){
            if(orders.size() == 0){
                id = 1;
            }else{
                id = list().get(list().size() -1).getId() + 1;
            }
        }else{
            id = entity.getId();
        }
        return id;
    }
}
