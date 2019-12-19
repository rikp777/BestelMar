package Interfaces.model;

import models.Status;

import java.util.Date;
import java.util.List;

public interface IOrder {
    int getId();
    void setId(int id);

    Date getDate();
    void setDate(Date date);

    Status getStatus();
    void setStatus(Status status);

    ITable getTable();
    void setTable(ITable table);

    List<IArticleOrder> getArticleOrder();
    void setArticleOrder(List<IArticleOrder> articleOrderHistories);
}
