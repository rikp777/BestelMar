package Data.Context.Interfaces;

//extends ICrudContext<IArticleOrderHistory>

import Data.DTO.ArticleOrderDto;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;

import java.util.List;

public interface IArticleOrderContext extends ICrudContext<ArticleOrderDto> {
    List<ArticleOrderDto> list(int orderId);
    boolean create(ArticleOrderDto entity, OrderDto order);
};
