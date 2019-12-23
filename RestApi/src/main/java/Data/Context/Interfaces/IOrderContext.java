package Data.Context.Interfaces;

import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import java.util.List;

public interface IOrderContext extends ICrudContext<IOrder> {
    boolean pay(IOrder entity);
    boolean create(IOrder entity, IUser user);
    IOrder readLast(IUser user);
    IOrder readLast(ITable table);
    List<IOrder> list(IUser user);
    List<IOrder> listLast();
}
