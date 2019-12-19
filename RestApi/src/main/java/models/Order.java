package models;

import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements IOrder {
    private int id;
    private Date date;
    private ITable table;
    private Status status;
    private List<IArticleOrder> articleOrderHistory;

    public Order(){

    }
    public Order(int id, Date date, ITable table, List<IArticleOrder> articleOrderHistory){
        this.id = id;
        this.date = date;
        this.table = table;
        this.articleOrderHistory = new ArrayList<>();

        if(articleOrderHistory.size() != 0){
            this.articleOrderHistory = articleOrderHistory;
        }
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
        return this.articleOrderHistory;
    }
    public void setArticleOrder(List<IArticleOrder> articleOrderHistories) {
        this.articleOrderHistory = articleOrderHistories;
    }
}
