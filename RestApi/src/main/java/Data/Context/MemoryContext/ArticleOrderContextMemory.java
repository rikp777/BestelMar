package Data.Context.MemoryContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleOrderDTOO;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticle;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import models.Status;

import java.util.ArrayList;
import java.util.List;

public class ArticleOrderContextMemory implements IArticleOrderContext {
    private static List<IArticleOrder> articleOrders;

    public ArticleOrderContextMemory(){
        articleOrders = new ArrayList<>();
    }



    public List<IArticleOrder> list(int orderId) {
        return new ArrayList<>(articleOrders);
    }


    public boolean create(IArticleOrder entity, IOrder order) {
        IArticleOrder articleOrder = new ArticleOrderDto(
                generateId(entity),
                entity.getPrice(),
                entity.getComment(),
                entity.getArticle(),
                entity.getDate(),
                Status.Placed
        );

        articleOrders.add(articleOrder);
        return articleOrders.contains(articleOrder);
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


    public int generateId(IArticleOrder entity){
        int id;
        if(entity.getId() == 0){
            if(articleOrders.size() == 0){
                id = 1;
            }else{
                id = list().get(list().size() -1).getId() + 1;
            }
        }else{
            id = entity.getId();
        }
        return id;
    }
}
