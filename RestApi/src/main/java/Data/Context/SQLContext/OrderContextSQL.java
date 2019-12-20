package Data.Context.SQLContext;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.IOrderContext;
import Data.Context.Interfaces.IUserContext;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.Helpers.SQLConnector;
import Interfaces.model.IArticle;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.*;
import models.Article;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderContextSQL extends SQLConnector implements IOrderContext{
    private final static Logger LOGGER = Logger.getLogger(OrderContextSQL.class.getName());

    public boolean create(IOrder entity, IUser user) {
        String query = "INSERT INTO Orders (date, table_id, user_id)" +
                "VALUES (?, ?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setDate(1, (java.sql.Date) entity.getDate());
            stmt.setInt(2, entity.getTable().getId());
            stmt.setInt(3, user.getId());
            LOGGER.log(Level.INFO, "Order Created: " + entity.getDate() + " of user: " + user.getFirstName() + " with table: " + entity.getTable().getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }
    public boolean create(IOrder entity) {
        System.out.println("table = "+ entity.getTable().getName());
        String query = "INSERT INTO Orders (date, table_id)" +
                " VALUES (?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setDate(1, new java.sql.Date(entity.getDate().getTime()));
            stmt.setInt(2, entity.getTable().getId());
            LOGGER.log(Level.INFO, "Order Created: " + entity.getDate());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }
    public boolean update(IOrder entity) {
        String query = "UPDATE Order SET date = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setDate(1, (java.sql.Date) entity.getDate());
            stmt.setInt(2, entity.getId());

            LOGGER.log(Level.INFO, "Order Updated: " + entity.getDate());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
    public boolean delete(IOrder entity) {
        String query = "DELETE FROM orders WHERE id = ? ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getId());

            LOGGER.log(Level.INFO, "Order Deleted: " + entity.getDate());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }


    public IOrder read(int id) {
        int tableId = 0;
        int orderId = 0;
        OrderDto orderDto = null;
        String query = "SELECT * FROM orders WHERE id = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                orderDto.setId(resultSet.getInt("id"));
                orderDto.setDate(resultSet.getDate("date"));
                tableId = resultSet.getInt("table_id");
                orderId = resultSet.getInt("order_id");
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        orderDto.setTable(new TableContextSQL().read(tableId));
        orderDto.setArticleOrder(new ArticleOrderContextSQL().list(orderId));
        System.out.println(orderDto.getArticleOrder().get(0).getId());
        return orderDto;
    }
    public IOrder read(IOrder entity) {
        System.out.println("en hier is die " + entity.getId());
        int tableId = 0;
        int orderId = 0;
        OrderDto orderDto = null;
        String query = "SELECT orders.id, orders.date, orders.table_id, orders.user_id, status.id as status_id, status.name as status_name " +
                "FROM orders " +
                "INNER JOIN status on status.id = orders.status_id " +
                "WHERE orders.id = ? " +
                "LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getId());


            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                orderDto.setId(resultSet.getInt("id"));
                orderDto.setDate(resultSet.getDate("date"));
                orderDto.setStatus(Status.valueOf(resultSet.getString("status_name")));

                System.out.println(resultSet.getInt("table_id"));

                tableId = resultSet.getInt("table_id");
                orderId = resultSet.getInt("order_id");
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        System.out.println(tableId);
        orderDto.setTable(new TableContextSQL().read(tableId));
        orderDto.setArticleOrder(new ArticleOrderContextSQL().list(orderId));
        System.out.println(orderDto.getArticleOrder().get(0).getId());
        return orderDto;
    }
    public IOrder readLast(IUser user) {
        int tableId = 0;
        int orderId = 0;
        System.out.println("user id " + user.getId());
        OrderDto orderDto = null;
        String query = "SELECT * FROM orders WHERE user_id = ? ORDER BY date desc LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, user.getId());

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                orderDto = new OrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDate("date")
                );
                tableId = resultSet.getInt("table_id");
                orderId = resultSet.getInt("order_id");
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        orderDto.setTable(new TableContextSQL().read(tableId));
        orderDto.setArticleOrder(new ArticleOrderContextSQL().list(orderId));
        return orderDto;
    }
    public IOrder readLast(ITable table) {
        OrderDto orderDto = null;
        String query = "SELECT orders.id, orders.date, orders.table_id, orders.user_id, status.id as status_id, status.name as status_name " +
                "FROM orders " +
                "INNER JOIN status on status.id = orders.status_id " +
                "WHERE table_id = ? " +
                "ORDER BY date DESC " +
                "LIMIT 1";
        //String query = "SELECT * FROM orders WHERE table_id = ? ORDER BY date desc LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, table.getId());

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                orderDto = new OrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDate("date")
                );
                System.out.println(resultSet.getString("status_name"));
                orderDto.setStatus(Status.valueOf(resultSet.getString("status_name")));
                orderDto.setTable(new TableContextSQL().read(resultSet.getInt("table_id")));
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return orderDto;
    }

    public List<IOrder> listLast(){
        List<IOrder> orders = new ArrayList<>();
        String query = "SELECT status.name as status_name, n.* FROM orders n INNER JOIN status on status.id = n.status_id INNER JOIN ( SELECT id, MAX(date) AS date, table_id FROM orders GROUP BY table_id ) AS max USING (table_id, date)";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                OrderDto orderDto = new OrderDto(
                        resultSet.getInt("n.id"),
                        resultSet.getDate("n.date")
                );
                orderDto.setStatus(Status.valueOf(resultSet.getString("status_name")));
                System.out.println("nu ben ik hier "+ orderDto.getId() + " asdf "+ resultSet.getInt("table_id"));
                ITable table = new TableDto();
                table.setId(resultSet.getInt("table_id"));
                orderDto.setTable(table);

                orders.add(orderDto);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return orders;
    }
    public List<IOrder> list() {
        List<IOrder> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                OrderDto orderDto = new OrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDate("date")
                );
                ITable table = new TableDto();
                table.setId(resultSet.getInt("table_id"));
                orderDto.setTable(table);

                orders.add(orderDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return orders;
    }
    public List<IOrder> list(IUser user){
        List<IOrder> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, user.getId());
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                OrderDto orderDto = new OrderDto(
                        resultSet.getInt("id"),
                        resultSet.getDate("date")
                );
                ITable table = new TableDto();
                table.setId(resultSet.getInt("table_id"));
                orderDto.setTable(table);

                orders.add(orderDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return orders;
    }
}
