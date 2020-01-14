package Data.Context.Interfaces;

import Data.DTO.OrderDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.IOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import java.util.List;

public interface IOrderContext extends ICrudContext<OrderDto> {
    boolean pay(OrderDto entity);
    boolean create(OrderDto entity, UserDto user);
    OrderDto readLast(UserDto user);
    OrderDto readLast(TableDto table);
    List<OrderDto> list(UserDto user);
    List<OrderDto> listLast();
}
