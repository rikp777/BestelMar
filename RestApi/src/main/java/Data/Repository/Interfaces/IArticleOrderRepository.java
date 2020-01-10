package Data.Repository.Interfaces;

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;

import java.util.List;

public interface IArticleOrderRepository extends ICrudRepository<IArticleOrder>{
    boolean add(IArticleOrder entity, IOrder order);
    List<IArticleOrder> getAll(int orderId);
}
