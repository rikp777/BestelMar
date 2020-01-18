package logic.Interfaces;

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

import java.util.List;

public interface IOrderLogic extends ILogic<IOrder> {
    IOrder getLastBy(IUser user);
    IOrder getLastBy(ITable table);
    List<IOrder> getAll(IUser user);
    List<IOrder> getAllLast();
    boolean editArticleOrder(List<IArticleOrder> articleOrders);


    boolean pay(IOrder entity);
    boolean getPaid(ITable entity);
}
