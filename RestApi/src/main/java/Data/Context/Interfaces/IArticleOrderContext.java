package Data.Context.Interfaces;

//extends ICrudContext<IArticleOrderHistory>

import Interfaces.model.IArticleOrder;

import java.util.List;

public interface IArticleOrderContext extends ICrudContext<IArticleOrder> {
    List<IArticleOrder> list(int orderId);
};
