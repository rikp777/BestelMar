package Data.Context.SQLContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleDto;
import Data.DTO.ArticleOrderDto;
import Data.Context.SQLContext.Helpers.SQLConnector;
import Data.DTO.OrderDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import models.ArticleOrder;
import models.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleOrderContextSQL extends SQLConnector implements IArticleOrderContext {
    private final static Logger LOGGER = Logger.getLogger(ArticleOrderContextSQL.class.getName());


    public boolean create(ArticleOrderDto entity, OrderDto order) {
        String query = "INSERT INTO article_order (article_id, order_id, price, comment, date)" +
                "VALUES (?, ?, ?, ?, ?) ";
        try {
            this.open();
            LOGGER.log(Level.INFO, "Incommming data: \n" +
                    "article: " + entity.getArticle().getId() + " " + entity.getArticle().getName() + "\n" +
                    "order id: "+ order.getId() + "\n" +
                    "price: "+ entity.getPrice() + "\n" +
                    "comment: "+ entity.getComment() + "\n" +
                    "date: " + new java.sql.Date(new Date().getTime()));

            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getArticle().getId());
            stmt.setInt(2, order.getId());
            stmt.setDouble(3, entity.getPrice());
            stmt.setString(4, entity.getComment());
            stmt.setDate(5, new java.sql.Date(new Date().getTime()));

            LOGGER.log(Level.INFO, "ArticleOrder Created: " + entity.getArticle().getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }

    public boolean create(ArticleOrderDto entity) {
        return false;
    }

    public boolean update(ArticleOrderDto entity) {
        String query = "UPDATE article_order SET price = ?, comment = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setDouble(1, entity.getPrice());
            stmt.setString(2, entity.getComment());
            stmt.setInt(3, entity.getId());

            LOGGER.log(Level.INFO, "OrderArticle Updated: " + entity.getArticle());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
    public boolean delete(ArticleOrderDto entity) {
        String query = "DELETE FROM order_article WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getArticle().getId());
            stmt.setInt(2, entity.getId());

            LOGGER.log(Level.INFO, "OrderArticle Deleted: " + entity.getArticle().getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }



    public ArticleOrderDto read(int id) {
        ArticleOrderDto articleOrder = null;
        String query = "SELECT ao.*, s.name as status_name " +
                "FROM article_order as ao " +
                "INNER JOIN status as s on s.id = ao.status_id " +
                "WHERE ao.id = ? " +
                "LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                articleOrder = new ArticleOrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("comment")
                );
                articleOrder.setStatus(Status.valueOf(resultSet.getString("status_name")));
                articleOrder.setArticle(
                        new ArticleContextSQL().read(resultSet.getInt("article_id"))
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleOrder;
    }
    public ArticleOrderDto read(ArticleOrderDto entity) {
        if(entity.getId() != 0){
            return this.read(entity.getId());
        }
        return null;
    }



    public List<ArticleOrderDto> list() {
        List<ArticleOrderDto> articleOrders = new ArrayList<>();
        String query = "SELECT article_order.id, article_order.price, article_order.comment, article_order.date, article_order.article_id, article_order.status_id, article_order.order_id, status.name as status_name, articles.name as article_name, articles.description as article_description, articles.price as article_price " +
                "FROM article_order " +
                "JOIN status on status.id = article_order.status_id " +
                "JOIN articles on articles.id = article_order.article_id";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                ArticleOrderDto articleOrderDto = new ArticleOrderDto();

                articleOrderDto.setId(resultSet.getInt("id"));
                articleOrderDto.setComment(resultSet.getString("comment"));
                articleOrderDto.setPrice(resultSet.getDouble("price"));
                articleOrderDto.setDate(resultSet.getDate("date"));

                articleOrderDto.setStatus(Status.valueOf(resultSet.getString("status_name")));
                articleOrderDto.setArticle(new ArticleDto(
                        resultSet.getInt("article_id"),
                        resultSet.getString("article_name"),
                        resultSet.getString("article_description"),
                        resultSet.getDouble("article_price")
                ));

                articleOrders.add(articleOrderDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleOrders;
    }
    public List<ArticleOrderDto> list(int orderId) {
        LOGGER.log(Level.INFO, "Trying to get all article orders for " + orderId);
        List<ArticleOrderDto> articleOrders = new ArrayList<>();
        String query = "SELECT ao.*, s.name as status_name " +
                "FROM article_order as ao " +
                "INNER JOIN status as s on s.id = ao.status_id " +
                "WHERE ao.order_id = ? " +
                "ORDER BY ao.id DESC ";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            //System.out.println("order id: " + orderId);
            stmt.setInt(1, orderId);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                ArticleOrderDto articleOrderDto = new ArticleOrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("comment")
                );
                articleOrderDto.setStatus(Status.valueOf(resultSet.getString("status_name")));
                articleOrderDto.setArticle(
                        new ArticleContextSQL().read(resultSet.getInt("article_id"))
                );

                LOGGER.log(Level.INFO, "Found article order: " + articleOrderDto.getArticle().getName() + " for order " + orderId + " status " + articleOrderDto.getStatus());

                articleOrders.add(articleOrderDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleOrders;
    }
}
