package RestApi.VOModels;

import Interfaces.model.IArticleOrder;
import Interfaces.model.ITable;
import models.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VOOrder {
    private int id;
    private Date date;
    private Status status;
    private String articleOrderLink;
    private String tableLink;

    public VOOrder(){

    }
    public VOOrder(int id, Date date, String tableLink, String articleOrderLink){
        this.id = id;
        this.date = date;
        this.tableLink = tableLink;
        this.articleOrderLink = articleOrderLink;
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

    public String getTableLink() {
        return tableLink;
    }
    public void setTableLink(String tableLink) {
        this.tableLink = tableLink;
    }

    public String getArticleOrderLink() {
        return this.articleOrderLink;
    }
    public void setArticleOrderLink(String articleOrderLink) {
        this.articleOrderLink = articleOrderLink;
    }
}
