package RestApi.VOModels;

import Interfaces.model.IArticleOrder;
import Interfaces.model.ITable;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VOOrder {
    private int id;
    private Date date;
    private Status status;
    @JsonProperty("articleOrder")
    private List<VOArticleOrder> articleOrder;
    @JsonProperty("table")
    private VOTable table;

    public VOOrder(){

    }
    public VOOrder(int id, Date date, List<VOArticleOrder> articleOrder, VOTable table){
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

    public VOTable getTable() {
        return table;
    }
    public void setTable(VOTable table) {
        this.table = table;
    }

    public List<VOArticleOrder> getArticleOrder() {
        return this.articleOrder;
    }
    public void setArticleOrder(List<VOArticleOrder> articleOrder) {
        this.articleOrder = articleOrder;
    }
}
