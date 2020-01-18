package Data.Repository;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.Context.SQLContext.ArticleOrderContextSQL;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Data.Repository.Interfaces.IArticleOrderRepository;
import Data.Repository.Interfaces.IArticleRepository;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;

import java.util.ArrayList;
import java.util.List;

public class ArticleOrderRepository implements IArticleOrderRepository {
    private IArticleOrderContext _articleOrderContext;
    public ArticleOrderRepository(IArticleOrderContext _articleOrderContext){
        this._articleOrderContext = _articleOrderContext;
    }

    public boolean add(IArticleOrder entity, IOrder order) {
        return _articleOrderContext.create( entity, order);
    }
    public boolean add(IArticleOrder entity) {
        return _articleOrderContext.create( entity);
    }
    public boolean edit(IArticleOrder entity) {
        return _articleOrderContext.update(entity);
    }
    public boolean remove(IArticleOrder entity) {
        return _articleOrderContext.delete(entity);
    }



    public IArticleOrder getBy(int id) {
        return _articleOrderContext.read(id);
    }
    public IArticleOrder getBy(IArticleOrder entity) {
        return _articleOrderContext.read(entity);
    }


    public List<IArticleOrder> getAll() {
        return new ArrayList<>(_articleOrderContext.list());
    }
    public List<IArticleOrder> getAll(int orderId) {
        return new ArrayList<>(_articleOrderContext.list(orderId));
    }
}
