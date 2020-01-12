package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IUserContext;
import Interfaces.model.IUser;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface IUserDao extends IUserContext {
    @Override
    @SqlQuery("SELECT * FROM users WHERE email = ? and password = ? LIMIT 1")
    boolean auth(@Bind("email") String email, @Bind("password") String password);

    @Override
    @SqlQuery("SELECT * FROM users WHERE email = ? LIMIT 1")
    IUser read(@Bind("email") String email);

    @Override
    @SqlQuery("INSERT INTO users (first_name, last_name, email, password)" +
            " VALUES (:firstName, :lastName, :email, :password) ")
    boolean create(@BindBean IUser entity);

    @Override
    @SqlQuery("UPDATE users SET email = :email, first_name = :firstName, last_name = :lastName WHERE id = :id")
    boolean update(@BindBean IUser entity);

    @Override
    @SqlQuery("UPDATE users SET blocked = :blocked WHERE id = :id")
    boolean delete(@BindBean IUser entity);

    @Override
    @SqlQuery("SELECT * FROM users WHERE id = ? LIMIT 1")
    IUser read(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT * FROM users WHERE id = :id LIMIT 1")
    IUser read(IUser entity);

    @Override
    @SqlQuery("SELECT * FROM users")
    List<IUser> list();
}
