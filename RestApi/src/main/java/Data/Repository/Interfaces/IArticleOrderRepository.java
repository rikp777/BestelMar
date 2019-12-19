package Data.Repository.Interfaces;

import Interfaces.model.IArticleOrder;

import java.util.List;

public interface IArticleOrderRepository extends ICrudRepository<IArticleOrder>{
    List<IArticleOrder> getAll(int orderId);
}
