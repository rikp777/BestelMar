package Data.Context.Interfaces;

//extends ICrudContext<IArticleOrderHistory>

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.IUser;

import java.util.List;

public interface IArticleOrderContext extends ICrudContext<IArticleOrder> {
    List<IArticleOrder> list(int orderId);
    boolean create(IArticleOrder entity, IOrder order);
};
