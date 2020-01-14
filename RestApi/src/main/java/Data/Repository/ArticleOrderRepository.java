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
        return _articleOrderContext.create((ArticleOrderDto) entity, (OrderDto) order);
    }
    public boolean add(IArticleOrder entity) {
        return _articleOrderContext.create((ArticleOrderDto) entity);
    }
    public boolean edit(IArticleOrder entity) {
        return _articleOrderContext.update((ArticleOrderDto) entity);
    }
    public boolean remove(IArticleOrder entity) {
        return _articleOrderContext.delete((ArticleOrderDto) entity);
    }



    public IArticleOrder getBy(int id) {
        return _articleOrderContext.read(id);
    }
    public IArticleOrder getBy(IArticleOrder entity) {
        return _articleOrderContext.read((ArticleOrderDto) entity);
    }


    public List<IArticleOrder> getAll() {
        return new ArrayList<>(_articleOrderContext.list());
    }
    public List<IArticleOrder> getAll(int orderId) {
        return new ArrayList<>(_articleOrderContext.list(orderId));
    }
}
