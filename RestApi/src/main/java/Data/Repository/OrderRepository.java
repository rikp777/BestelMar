package Data.Repository;

import Data.Context.Interfaces.IOrderContext;
import Data.Context.SQLContext.OrderContextSQL;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Data.Repository.Interfaces.IOrderRepository;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private IOrderContext _orderContext;
    public OrderRepository(IOrderContext _orderContext){
        this._orderContext = _orderContext;
    }

    public boolean pay(IOrder entity) {
        return _orderContext.pay((OrderDto) entity);
    }
    public boolean add(IOrder entity, IUser user) {
        return _orderContext.create((OrderDto) entity, (UserDto) user);
    }
    public boolean add(IOrder entity) {
        return _orderContext.create((OrderDto) entity);
    }
    public boolean edit(IOrder entity) {
        return _orderContext.update((OrderDto) entity);
    }
    public boolean remove(IOrder entity) {
        return _orderContext.delete((OrderDto) entity);
    }



    public IOrder getBy(int id) {
        return _orderContext.read(id);
    }
    public IOrder getBy(IOrder entity) {
        return _orderContext.read((OrderDto) entity);
    }
    public IOrder getLastBy(IUser user) {
        return _orderContext.readLast((UserDto) user);
    }
    public IOrder getLastBy(ITable table) {
        TableDto tableDto = (TableDto) table;
        return _orderContext.readLast((TableDto) table);
    }


    public List<IOrder> getAll() {
        return new ArrayList<>(_orderContext.list());
    }
    public List<IOrder> getAll(IUser user) {
        return new ArrayList<>(_orderContext.list((UserDto) user));
    }
    public List<IOrder> getLast() {
        return new ArrayList<>(_orderContext.listLast());
    }
}
