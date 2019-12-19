package Data.DTO;

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import models.Status;

import java.util.Date;
import java.util.List;

public class OrderDto implements IOrder {
    private int id;
    private Date date;
    private ITable table;
    private Status status;
    private List<IArticleOrder> articleOrder;

    public OrderDto(){

    }
    public OrderDto(int id, Date date){
        this.id = id;
        this.date = date;
    }
    public OrderDto(int id, Date date, ITable table, List<IArticleOrder> articleOrder){
        this.id = id;
        this.date = date;
        this.table = table;
        this.articleOrder = articleOrder;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }


    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public ITable getTable() {
        return table;
    }
    public void setTable(ITable table) {
        this.table = table;
    }

    public List<IArticleOrder> getArticleOrder() {
        return this.articleOrder;
    }
    public void setArticleOrder(List<IArticleOrder> articleOrder) {
        this.articleOrder = articleOrder;
    }
}
