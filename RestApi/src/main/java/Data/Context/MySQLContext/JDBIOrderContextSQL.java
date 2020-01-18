package Data.Context.MySQLContext;

import Data.Context.Interfaces.IOrderContext;
import Data.Context.MySQLContext.Helpers.SQLConnector;
import Data.Context.MySQLContext.dao.IOrderDao;
import Data.Context.MySQLContext.dao.ITableDao;
import Data.Context.MySQLContext.dao.IUserDao;
import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.Status;
import org.jdbi.v3.core.mapper.RowMapper;

import java.util.List;

public class JDBIOrderContextSQL extends SQLConnector implements IOrderContext {
    @Override
    public boolean pay(IOrder entity) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.pay(entity));
        }
        return false;
    }

    @Override
    public boolean create(IOrder entity, IUser user) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.create(entity, user));
        }
        return false;
    }

    @Override
    public IOrder readLast(IUser user) {
        if(user != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.readLast(user));
        }
        return null;
    }

    @Override
    public IOrder readLast(ITable table) {
        if(table != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.readLast(table));
        }
        return null;
    }

    @Override
    public List<IOrder> list(IUser user) {
        if(user != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.list(user));
        }
        return null;
    }

    @Override
    public List<IOrder> listLast() {

        RowMapper<OrderDto> orderMapper = (rs, ctx) -> {
            System.out.println(rs.getInt("id"));

            OrderDto order =  new OrderDto(
                    rs.getInt("id"),
                    rs.getDate("date")
                    );
            TableDto table = new TableDto();
            table.setId(rs.getInt("table_id"));
            order.setTable(table);
            order.setStatus(Status.valueOf(rs.getString("status_name")));

            return order;
        };
//        List<OrderDto> orders = jdbi().withHandle(handle -> {
//            return handle.createQuery("SELECT ord.id, s.name as status_name, o.date, o.user_id, o.table_id" +
//                    "            FROM orders as o" +
//                    "            JOIN ( " +
//                    "                SELECT MAX(orders.id) as id,  orders.date" +
//                    "                FROM orders" +
//                    "                GROUP BY orders.table_id" +
//                    "            ) as ord on o.id = ord.id" +
//                    "            JOIN status as s on s.id = o.status_id" +
//                    "            ORDER BY o.table_id")
//                    .map(orderMapper)
//                    .list();
//        });

        //System.out.println(orders.get(0).getTable().getId());

        //jdbi().withExtension(IOrderDao.class, dao -> dao.listLast());


//        List<OrderDto> orders = jdbi().withExtension(IOrderDao.class, dao -> dao.listLast());
//        for(OrderDto order : orders){
//            //order.setTable(jdbi().withExtension(ITableDao.class, dao -> dao.()));
//        }
        return null;
    }

    @Override
    public boolean create(IOrder entity) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.create(entity));
        }
        return false;
    }

    @Override
    public boolean update(IOrder entity) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.update(entity));
        }
        return false;
    }

    @Override
    public boolean delete(IOrder entity) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.delete(entity));
        }
        return false;
    }

    @Override
    public IOrder read(int id) {
        if(id != 0){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.read(id));
        }
        return null;
    }

    @Override
    public IOrder read(IOrder entity) {
        if(entity != null){
            //return jdbi().withExtension(IOrderDao.class, dao -> dao.read(entity));
        }
        return null;
    }

    @Override
    public List<IOrder> list() {
        return null; //jdbi().withExtension(IOrderDao.class, dao -> dao.list());
    }
}
