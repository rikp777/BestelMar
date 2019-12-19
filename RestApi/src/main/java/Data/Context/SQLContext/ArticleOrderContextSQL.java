package Data.Context.SQLContext;

import Data.Context.Interfaces.IArticleOrderContext;
import Data.DTO.ArticleOrderDto;
import Data.Helpers.SQLConnector;
import Interfaces.model.IArticle;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import models.Article;
import models.ArticleOrderHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleOrderContextSQL extends SQLConnector implements IArticleOrderContext {
    private final static Logger LOGGER = Logger.getLogger(UserContextSQL.class.getName());


    public boolean create(IArticleOrder entity, IOrder order) {
        String query = "INSERT INTO article_order (article_id, order_id, price, comment)" +
                "VALUES (?, ?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getArticle().getId());
            stmt.setInt(2, order.getId());
            stmt.setDouble(3, entity.getPrice());
            stmt.setString(4, entity.getComment());
            LOGGER.log(Level.INFO, "ArticleOrder Created: " + entity.getArticle().getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }

    @Override
    public boolean create(IArticleOrder entity) {
        return false;
    }

    public boolean update(IArticleOrder entity) {
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
    public boolean delete(IArticleOrder entity) {
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



    public IArticleOrder read(int id) {
        IArticleOrder articleOrder = null;
        String query = "SELECT * FROM article_order WHERE id = ? LIMIT 1";
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
    public IArticleOrder read(IArticleOrder entity) {
        if(entity.getId() != 0){
            return this.read(entity.getId());
        }
        return null;
    }



    public List<IArticleOrder> list() {
        List<IArticleOrder> articleOrderHistories = new ArrayList<>();
        String query = "SELECT * FROM article_order";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                articleOrderHistories.add(new ArticleOrderHistory(
                        resultSet.getInt("id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("comment"),
                        (IArticle) new ArticleOrderContextSQL().read(resultSet.getInt("id"))
                ));
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleOrderHistories;
    }
    public List<IArticleOrder> list(int orderId) {
        System.out.println("read " + orderId);
        List<IArticleOrder> articleOrders = new ArrayList<>();
        String query = "SELECT * FROM article_order WHERE order_id = ? ORDER BY id DESC";

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
                articleOrderDto.setArticle(
                        new ArticleContextSQL().read(resultSet.getInt("article_id"))
                );

                System.out.println(articleOrderDto.getArticle().getName());
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
