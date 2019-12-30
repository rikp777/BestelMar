package model;

import Data.DTO.OrderDto;
import Data.DTO.RightDto;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.IOrder;
import Interfaces.model.IRight;
import Interfaces.model.ITable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private static List<UserDto> users = new ArrayList<>();

    @BeforeAll
    static void setUp(){
        users.add(new UserDto(1, "rikpeeters@hotmail.com", "Rik", "Peeters", true));
        users.add(new UserDto(2, "janpieters@hotmail.com", "Jan", "Pieters", true));
        users.add(new UserDto(3, "pietersjan@hotmail.com", "Pieters", "Jan", false));
    }

    @Test
    void getFirstName(){
        UserDto user = users.get(0);
        assertEquals("Rik", user.getFirstName());
    }
    @Test
    void getLastName(){
        UserDto user = users.get(0);
        assertEquals("Peeters", user.getLastName());
    }
    @Test
    void getEmail(){
        UserDto user = users.get(0);
        assertEquals("rikpeeters@hotmail.com", user.getEmail());
    }
    @Test
    void getBlocked(){
        UserDto user = users.get(0);
        assertEquals(true, user.getBlocked());
    }
    @Test
    void getRights(){
        UserDto user = users.get(0);
        RightDto right = new RightDto(1, "Admin", "God");
        List<IRight> rights = new ArrayList<>();
        rights.add(right);
        user.setRights(rights);

        assertEquals(rights, user.getRights());
    }

    @Test
    void getOrders(){
        UserDto user = users.get(0);
        OrderDto order = new OrderDto(1, new Date());
        List<IOrder> orders = new ArrayList<>();
        orders.add(order);
        user.setOrders(orders);

        assertEquals(orders, user.getOrders());
    }

}
