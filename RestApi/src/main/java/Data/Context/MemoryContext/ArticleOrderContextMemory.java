package Data.Context.MemoryContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleDto;
import Data.DTO.ArticleOrderDTOO;
import Data.DTO.ArticleOrderDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;

import java.util.ArrayList;
import java.util.List;

public class ArticleOrderContextMemory implements IArticleOrderContext {
    private static List<ArticleOrderDTOO> articleOrders;

    public ArticleOrderContextMemory(){
        articleOrders = new ArrayList<>();
    }



    public List<IArticleOrder> list(int orderId) {
        return null;
    }


    public boolean create(IArticleOrder entity, IOrder order) {
        ArticleOrderDTOO articleOrder = new ArticleOrderDTOO(
                1,
                entity.getArticle().getId(),
                order.getId(),
                entity.getPrice(),
                entity.getComment(),
                1,
                entity.getDate()
        );
        return false;
    }


    public boolean create(IArticleOrder entity) {
        return false;
    }


    public boolean update(IArticleOrder entity) {
        return false;
    }


    public boolean delete(IArticleOrder entity) {
        return false;
    }


    public IArticleOrder read(int id) {
        return null;
    }


    public IArticleOrder read(IArticleOrder entity) {
        return null;
    }


    public List<IArticleOrder> list() {
        return null;
    }
}
