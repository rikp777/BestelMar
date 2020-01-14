package Data.Context.MemoryContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleOrderDTOO;
import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;

import java.util.ArrayList;
import java.util.List;

public class ArticleOrderContextMemory implements IArticleOrderContext {
    private static List<ArticleOrderDto> articleOrders;

    public ArticleOrderContextMemory(){
        articleOrders = new ArrayList<>();
    }



    public List<ArticleOrderDto> list(int orderId) {
        return null;
    }


    public boolean create(ArticleOrderDto entity, OrderDto order) {
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


    public boolean create(ArticleOrderDto entity) {
        return false;
    }


    public boolean update(ArticleOrderDto entity) {
        return false;
    }


    public boolean delete(ArticleOrderDto entity) {
        return false;
    }


    public ArticleOrderDto read(int id) {
        return null;
    }


    public ArticleOrderDto read(ArticleOrderDto entity) {
        return null;
    }


    public List<ArticleOrderDto> list() {
        return null;
    }
}
