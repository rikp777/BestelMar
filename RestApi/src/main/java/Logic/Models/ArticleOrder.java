package Logic.Models;

import Interfaces.model.IArticle;
import Interfaces.model.IArticleOrder;
import models.Status;

import java.util.Date;

public class ArticleOrder implements IArticleOrder {
    private int id;
    private Double price;
    private String comment;
    private IArticle article;

    public ArticleOrder(){

    }
    public ArticleOrder(Double price, String comment){
        this.price = price;
        this.comment = comment;
        this.article = article;
    }
    public ArticleOrder(int id, Double price, String comment){
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

    public IArticle getArticle() {
        return this.article;
    }
    public void setArticle(IArticle article) {
        this.article = article;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public void setDate(Date date) {

    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void setStatus(Status status) {

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
