package Data.Repository;

import Data.Context.Interfaces.IOrderContext;
import Data.Context.SQLContext.OrderContextSQL;
import Data.Repository.Interfaces.IOrderRepository;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

import java.util.List;

public class OrderRepository implements IOrderRepository {
    private IOrderContext _orderContext;
    public OrderRepository(IOrderContext _orderContext){
        this._orderContext = _orderContext;
    }

    public boolean pay(IOrder entity) {
        return _orderContext.pay(entity);
    }
    public boolean add(IOrder entity, IUser user) {
        return _orderContext.create(entity, user);
    }
    public boolean add(IOrder entity) {
        return _orderContext.create(entity);
    }
    public boolean edit(IOrder entity) {
        return _orderContext.update(entity);
    }
    public boolean remove(IOrder entity) {
        return _orderContext.delete(entity);
    }



    public IOrder getBy(int id) {
        return _orderContext.read(id);
    }
    public IOrder getBy(IOrder entity) {
        return _orderContext.read(entity);
    }
    public IOrder getLastBy(IUser user) {
        return _orderContext.readLast(user);
    }
    public IOrder getLastBy(ITable table) {
        return _orderContext.readLast(table);
    }


    public List<IOrder> getAll() {
        return _orderContext.list();
    }
    public List<IOrder> getAll(IUser user) {
        return _orderContext.list(user);
    }
    public List<IOrder> getLast() { return _orderContext.listLast(); }
}
