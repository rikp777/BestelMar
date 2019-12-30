package logic;


import Data.Context.MemoryContext.OrderContextMemory;
import Data.DTO.OrderDto;
import Data.Repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderLogicTest {

    private OrderLogic _orderLogic;
    private String authUser;
    private List<OrderDto> Orders = new ArrayList<>();

    @BeforeEach
    void setUp(){
        _orderLogic = new OrderLogic(new OrderRepository(new OrderContextMemory()));
        authUser = "rikpeeters@hotmail.com";
        Orders.add(new OrderDto(1, new Date()));
        Orders.add(new OrderDto(2, new Date()));
    }
}
