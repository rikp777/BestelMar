package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IOrderContext;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface IOrderDao extends IOrderContext {
    @Override
    @SqlQuery("UPDATE Orders SET status_id = 4 WHERE id = :id")
    boolean pay(@BindBean IOrder entity);

    @Override
    @SqlQuery("INSERT INTO Orders (date, table_id, user_id)" +
            "VALUES (:order.date, :order.table.id, :user.id) ")
    boolean create(@BindBean("order") IOrder entity, @BindBean("user") IUser user);

    @Override
    @SqlQuery("SELECT * FROM orders WHERE user_id = :id ORDER BY date desc LIMIT 1")
    IOrder readLast(@BindBean IUser user);

    @Override
    @SqlQuery("SELECT orders.id, orders.date, orders.table_id, orders.user_id, status.id as status_id, status.name as status_name " +
            "FROM orders " +
            "INNER JOIN status on status.id = orders.status_id " +
            "WHERE table_id = :id " +
            "ORDER BY orders.id DESC " +
            "LIMIT 1")
    IOrder readLast(@BindBean ITable table);

    @Override
    @SqlQuery("SELECT * FROM orders WHERE user_id = ?")
    List<IOrder> list(@BindBean IUser user);

    @Override
    @SqlQuery("SELECT ord.id, s.name as status_name, o.date, o.user_id, o.table_id \n" +
            "FROM orders as o \n" +
            "JOIN ( \n" +
            "    SELECT MAX(orders.id) as id,  orders.date\n" +
            "    FROM orders \n" +
            "    GROUP BY orders.table_id\n" +
            ") as ord on o.id = ord.id\n" +
            "JOIN status as s on s.id = o.status_id \n" +
            "ORDER BY o.table_id")
    List<IOrder> listLast();

    @Override
    @SqlQuery("INSERT INTO Orders (date, table_id)" +
            " VALUES (:date, :table.id) ")
    boolean create(IOrder entity);

    @Override
    @SqlQuery("UPDATE Order SET date = :date WHERE id = :id")
    boolean update(IOrder entity);

    @Override
    @SqlQuery("DELETE FROM orders WHERE id = :id ")
    boolean delete(IOrder entity);

    @Override
    @SqlQuery("SELECT * FROM orders WHERE id = :id LIMIT 1")
    IOrder read(int id);

    @Override
    @SqlQuery("SELECT * FROM orders WHERE id = :id LIMIT 1")
    IOrder read(IOrder entity);

    @Override
    @SqlQuery("SELECT *, status.name as status_name FROM `orders` Inner Join status on status.id = orders.status_id")
    List<IOrder> list();
}
