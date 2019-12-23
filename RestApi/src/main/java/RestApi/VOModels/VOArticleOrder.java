package RestApi.VOModels;

import Interfaces.model.IArticle;
import models.Status;

import java.util.Date;

public class VOArticleOrder {
    private int id;
    private Double price;
    private String comment;
    private VOArticle article;
    private Date date;
    private Status status;

    public VOArticleOrder(){

    }
    public VOArticleOrder(Double price, String comment){
        this.price = price;
        this.comment = comment;
    }
    public VOArticleOrder(int id, Double price, String comment){
        this.id = id;
        this.price = price;
        this.comment = comment;
    }

    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public VOArticle getArticle() {
        return this.article;
    }
    public void setArticle(VOArticle article) {
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
