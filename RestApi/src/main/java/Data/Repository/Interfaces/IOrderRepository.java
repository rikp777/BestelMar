package Data.Repository.Interfaces;

import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

import java.util.List;

public interface IOrderRepository extends ICrudRepository<IOrder> {
    IOrder getLastBy(IUser user);
    IOrder getLastBy(ITable table);
    List<IOrder> getAll(IUser user);
}
