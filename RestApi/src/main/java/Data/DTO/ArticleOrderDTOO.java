package Data.DTO;

import java.util.Date;

public class ArticleOrderDTOO {
    private int id;
    private int articleId;
    private int orderId;
    private double price;
    private String comment;
    private int statusId;
    private Date date;

    public ArticleOrderDTOO(int id, int articleId, int orderId, double price, String comment, int statusId, Date date) {
        this.id = id;
        this.articleId = articleId;
        this.orderId = orderId;
        this.price = price;
        this.comment = comment;
        this.statusId = statusId;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public int getArticleId() {
        return articleId;
    }
    public int getOrderId() {
        return orderId;
    }
    public double getPrice() {
        return price;
    }
    public String getComment() {
        return comment;
    }
    public int getStatusId() {
        return statusId;
    }
    public Date getDate() {
        return date;
    }
}
