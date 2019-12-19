package Interfaces.model;

public interface IArticleOrder {
    int getId();
    void setId(int id);

    Double getPrice();
    void setPrice(Double price);

    String getComment();
    void setComment(String comment);

    IArticle getArticle();
    void setArticle(IArticle article);
}
