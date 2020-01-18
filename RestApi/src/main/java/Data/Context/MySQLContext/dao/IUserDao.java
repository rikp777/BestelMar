package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IUserContext;
import Data.DTO.ArticleDto;
import Data.DTO.UserDto;
import Interfaces.model.IUser;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(UserDto.class)
public interface IUserDao  {

    @SqlQuery("SELECT * FROM users WHERE email = ? and password = ? LIMIT 1")
    boolean auth(@Bind("email") String email, @Bind("password") String password);


    @SqlQuery("SELECT * FROM users WHERE email = ? LIMIT 1")
    UserDto read(@Bind("email") String email);


    @SqlQuery("INSERT INTO users (first_name, last_name, email, password)" +
            " VALUES (:firstName, :lastName, :email, :password) ")
    boolean create(@BindBean UserDto entity);


    @SqlQuery("UPDATE users SET email = :email, first_name = :firstName, last_name = :lastName WHERE id = :id")
    boolean update(@BindBean UserDto entity);


    @SqlQuery("UPDATE users SET blocked = :blocked WHERE id = :id")
    boolean delete(@BindBean UserDto entity);


    @SqlQuery("SELECT * FROM users WHERE id = ? LIMIT 1")
    UserDto read(@Bind("id") int id);


    @SqlQuery("SELECT * FROM users WHERE id = :id LIMIT 1")
    UserDto read(@BindBean UserDto entity);


    @SqlQuery("SELECT * FROM users")
    List<UserDto> list();
}
