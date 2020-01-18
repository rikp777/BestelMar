package Data.Context.MySQLContext.dao;

import Data.Context.Interfaces.IRightContext;
import Data.DTO.RightDto;
import Data.DTO.UserDto;
import Interfaces.model.IUser;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(RightDto.class)
public interface IRightDao {

    @SqlQuery("SELECT * FROM rights WHERE name = :id")
    RightDto read(@Bind("id") int id);


    @SqlQuery("SELECT * FROM rights WHERE name = :name")
    RightDto read(@Bind("name") String name);


    @SqlQuery("SELECT * FROM right_user JOIN rights on rights.id = right_user.right_id WHERE user_id = :id")
    List<RightDto> list(@BindBean UserDto user);


    @SqlQuery("SELECT * FROM rights")
    List<RightDto> list();
}
