package Interfaces.model;

import models.Status;

import java.util.Date;

public interface IArticleOrder {
    int getId();
    void setId(int id);

    Double getPrice();
    void setPrice(Double price);

    String getComment();
    void setComment(String comment);

    IArticle getArticle();
    void setArticle(IArticle article);

    Date getDate();
    void setDate(Date date);

    Status getStatus();
    void setStatus(Status status);
}
