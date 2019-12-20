package models;

import Interfaces.model.IArticle;
import Interfaces.model.IArticleOrder;
import java.util.Date;

public class ArticleOrder implements IArticleOrder {
    private int id;
    private Double price;
    private String comment;
    private IArticle article;
    private Date date;
    private Status status;

    public ArticleOrder(int id, Double price, String comment, IArticle article){
        this.id = id;
        this.price = price;
        this.comment = comment;
        this.article = article;
    }

    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public IArticle getArticle() {
        return this.article;
    }
    public void setArticle(IArticle article) {
        this.article = article;
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

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice(){
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
