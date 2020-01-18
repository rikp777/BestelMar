package Data.Context.Interfaces;

//extends ICrudContext<IArticleOrderHistory>

import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;

import java.util.List;

public interface IArticleOrderContext extends ICrudContext<IArticleOrder> {
    List<IArticleOrder> list(int orderId);
    boolean create(IArticleOrder entity, IOrder order);
};
